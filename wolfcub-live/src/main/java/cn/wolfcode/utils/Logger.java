package cn.wolfcode.utils;

import cn.wolfcode.domain.LoggerModule;
import cn.wolfcode.service.LoggerService;
import cn.wolfcode.vo.JsonResult;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Aspect
@Component
@Slf4j
public class Logger {
	private final LoggerService service;
	private static final List<String> WHITE_LIST = new ArrayList<>();
	
	static {
		WHITE_LIST.add("login");
	}
	
	public Logger(LoggerService service) {
		this.service = service;
	}
	
	@Pointcut("@annotation(cn.wolfcode.utils.Log))")
	public void pointCut() {
	}
	
	@Around("pointCut()")
	public Object logSave(ProceedingJoinPoint pjp) throws Throwable {
		Object target = pjp.proceed();
		try {
			final var methodName = pjp.getSignature().getName();
			final var attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			final var request = attributes.getRequest();
			final var args = pjp.getArgs();
			String name = null;
			String value = null;
			Log.Level level = null;
			try {
				Log annotation = getLogAnnotation(pjp);
				if (Objects.nonNull(annotation)) {
					value = annotation.value();
					level = annotation.level();
				}
			} catch (Exception e) {
				handleErr(e);
			}
			StringBuilder builder = new StringBuilder();
			
			log.info("============================ req ===========================");
			
			if (Objects.isNull(value) || !value.equals("")) {
				name = value;
			} else {
				name = methodName;
			}
			builder
					
					.append('\n').append('\t').append("url:\t").append(request.getRequestURL()).append('\n')
					.append('\t').append("request:\t").append(JSON.toJSONString(args)).append('\n')
					.append('\t').append(name).append("方法执行参数是").append(Arrays.toString(args)).append('\n')
					.append('\t').append(name).append("执行成功，返回值为").append(JSON.toJSONString(target));
			final var loggerModule = LoggerModule.of(Log.Level.info.name(), builder);
			service.save(loggerModule);
			log.info(builder.toString());
			log.info("============================ end ===========================");
			if (WHITE_LIST.contains(methodName)) {
				if (target instanceof JsonResult) {
					if (methodName.equals("login")) {
						final var token = (String) ((JsonResult<?>) target).getData();
						final var info = TokenManager.getInfo(token);
						final var host = request.getRemoteHost();
						service.save(LoggerModule.of(level.name(),
								String.format("%s用户已在%s登陆主机为%s", JSON.toJSONString(args[0]), request.getRequestURL(), host)
								, info.getId()));
					}
				}
			}
		} catch (Throwable throwable) {
			handleErr(throwable);
		}
		return target;
	}
	
	private void handleErr(Throwable throwable) {
		StringBuffer buffer = new StringBuffer();
		final var trace = throwable.getStackTrace();
		for (StackTraceElement element : trace) {
			buffer.append(element.toString());
		}
		final var loggerModule = LoggerModule.of(Log.Level.error.name(), buffer.toString());
		log.info("{}", buffer);
		service.save(loggerModule);
	}
	
	/**
	 * 因为CGLIB代理对象的特殊性 必须使用Spring提供的AnnotaionUtils.findAnnotation()方法才能拿到注解对象
	 *
	 * @param pjp
	 * @return
	 * @throws NoSuchMethodException
	 */
	private Log getLogAnnotation(ProceedingJoinPoint pjp) {
		try {
			final var mothodName = pjp.getSignature().getName();
			final var args = pjp.getArgs();
			final var types = Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);
			final Class<?> clazz = pjp.getThis().getClass();
			final var method = clazz.getDeclaredMethod(mothodName, types);
			return AnnotationUtils.findAnnotation(method, Log.class);
		} catch (NoSuchMethodException e) {
			handleErr(e);
			return null;
		}
		
	}
	
}
