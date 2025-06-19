package com.leeonscoding.personal_apps.advices;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
@Log4j2
public class LoggerAspect {

    @Pointcut("execution(* com.leeonscoding.personal_apps.services..*(..))")
    public void allServiceMethods() {}

    @Before("allServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Started method: {} at: {}", joinPoint.getSignature().getName(), Instant.now());
    }

    @After("allServiceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Ended method: {} at: {}", joinPoint.getSignature().getName(), Instant.now());
    }

    @AfterReturning(pointcut = "allServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Method: {} returned: {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(pointcut = "allServiceMethods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("Method: {} threw exception: {}", joinPoint.getSignature().getName(), error.getMessage());
    }

    @Around("allServiceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Executing service method: {}", joinPoint.getSignature().getName());
        return joinPoint.proceed();
    }
}
