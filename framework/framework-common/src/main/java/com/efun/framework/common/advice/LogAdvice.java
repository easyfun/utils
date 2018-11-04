package com.efun.framework.common.advice;

//@Aspect
//@Configuration
//public class LogAdvice {
	/**
	 * Logger for this class
	 */
//	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	

//	@Around(value = "execution(* com.sky.*.dubbo.*Service.*(..))")
//	public Object dubboProcess(ProceedingJoinPoint point) throws ValidationException, Throwable {
//		String method = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
//		Object[] params = point.getArgs();
//		logger.info("[{}]请求参数:{}", method, JSON.toJSONString(params));
//		for (Object obj : params) {
//			ValidateUtil.validate(obj);
//		}
//		Object returnValue = point.proceed(params);
//		logger.info("[{}]返回结果:{}", method, JSON.toJSONString(returnValue));
//		return returnValue;
//	}
	
//	private Object process(ProceedingJoinPoint point) throws ValidationException, Throwable {
//		String method = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
//		Object[] params = point.getArgs();
//		logger.info("[{}]请求参数:{}", method, JSON.toJSONString(params));
//		Object returnValue = point.proceed(params);
//		logger.info("[{}]返回结果:{}", method, JSON.toJSONString(returnValue));
//		return returnValue;
//	}
//}
