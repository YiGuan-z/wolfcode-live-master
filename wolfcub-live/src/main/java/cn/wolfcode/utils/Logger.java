//package cn.wolfcode.utils;
//
//import org.aopalliance.aop.Advice;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.util.ArrayList;
//
//@Component
//@Aspect
//public class Logger implements Advice {
//	private static final Log logger = LogFactory.getLog(Logger.class);
//
//	@Pointcut("execution(* cn.wolfcode.service.*Service.*(..))")
//	public void pointCut() {
//	}
//
//	@Around("pointCut()")
//	public Object log(ProceedingJoinPoint pjp) throws Throwable {
//		final var params = new ArrayList<Object>();
//		final var requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		if (requestAttributes == null) {
//			return pjp.proceed();
//		}
//		final var request = requestAttributes.getRequest();
//		final var args = pjp.getArgs();
//		logger.info(request);
//		logger.info(args);
//		pjp.proceed();
//		return null;
//	}
//}
