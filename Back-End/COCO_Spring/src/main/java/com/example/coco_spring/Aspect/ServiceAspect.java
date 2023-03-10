package com.example.coco_spring.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ServiceAspect {
    private static final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    @Before("execution(* com.example.coco_spring.Service.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Calling method: " + methodName);
    }

    @AfterReturning(pointcut = "execution(* com.example.coco_spring.Service.*.*(..))", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method " + methodName + " returned: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.coco_spring.Service.*.*(..))", throwing = "error")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable error) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("Exception thrown in method " + methodName + ". Exception message: " + error.getMessage());
    }

    @After("execution(* com.example.coco_spring.Service.*.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Finished executing method: " + methodName);
    }

    @Around("execution(* com.example.coco_spring.Service.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Executing method: " + methodName);

        Object result ;
        try {
            result = joinPoint.proceed();
            logger.info("Method " + methodName + " returned: " + result);
        } catch (Exception e) {
            logger.error("Exception thrown in method " + methodName + ". Exception message: " + e.getMessage());
            throw e;
        }

        logger.info("Finished executing method: " + methodName);
        return result;
    }

}
