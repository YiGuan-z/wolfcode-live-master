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


@Aspect
@Component
@Slf4j
public class Logger {
	private final LoggerService service;
	private static final List<String> whiteList = new ArrayList<>();
	
	static {
		whiteList.add("login");
	}
	
	public Logger(LoggerService service) {
		this.service = service;
	}
	
	@Pointcut("@annotation(cn.wolfcode.utils.Log))")
	public void pointCut() {
	}
	
	@Around("pointCut()")
	public Object logSave(ProceedingJoinPoint pjp) throws Throwable {
		final var methodName = pjp.getSignature().getName();
		final var attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		final var request = attributes.getRequest();
		final var args = pjp.getArgs();
		String name = null;
		Log annotation = getLogAnnotation(pjp);
		final var value = annotation.value();
		final var level = annotation.level();
		log.info("============================ req ===========================");
		log.info("url:	{}", request.getRequestURL().toString());
		log.info("request: 	{}", JSON.toJSONString(args));
		if (!value.equals("")) {
			name = value;
		} else {
			name = methodName;
		}
		log.info("{}方法启动,参数是{}", name, Arrays.toString(args));
		final var target = pjp.proceed(); // 真正执行业务逻辑
		log.info("{}方法执行成功，返回值为{}", name, target);
		log.info("============================ end ===========================");
		if (whiteList.contains(methodName)) {
			if (target instanceof JsonResult) {
				final var token = (String) ((JsonResult<?>) target).getData();
				final var info = TokenManager.getInfo(token);
				service.save(LoggerModule.of(level.name(),
						String.format("%s用户已在%s登陆", JSON.toJSONString(args[0]),request.getRequestURL())
						, info.getId()));
			}
		}
		return target;
	}
	
	/**
	 * 因为CGLIB代理对象的特殊性 必须使用Spring提供的AnnotaionUtils.findAnnotation()方法才能拿到注解对象
	 *
	 * @param pjp
	 * @return
	 * @throws NoSuchMethodException
	 */
	private Log getLogAnnotation(ProceedingJoinPoint pjp) throws NoSuchMethodException {
		final var mothodName = pjp.getSignature().getName();
		final var args = pjp.getArgs();
		final var types = Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);
		final Class<?> clazz = pjp.getThis().getClass();
		final var method = clazz.getDeclaredMethod(mothodName, types);
		return AnnotationUtils.findAnnotation(method, Log.class);
	}
}
