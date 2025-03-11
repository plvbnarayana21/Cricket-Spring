package com.example.cricket.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.cricket.service.*.*(..))")
    public void serviceMethods() {}

    @Pointcut("execution(* com.example.cricket.controller.*.*(..))")
    public void controllerMethods() {}

    @Around("serviceMethods()")
    public Object logServiceMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("logging done");
        log.info("Service Method [{}] executed in {} ms",
                joinPoint.getSignature().toShortString(),
                (endTime - startTime));
        return result;
    }

    @Before("controllerMethods()")
    public void logControllerMethodEntry(JoinPoint joinPoint) {
        log.info("Entering Controller Method: {}", joinPoint.getSignature().toShortString());
    }

    @After("controllerMethods()")
    public void logControllerMethodExit(JoinPoint joinPoint) {
        log.info("Exiting Controller Method: {}", joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logServiceExceptions(JoinPoint joinPoint, Exception ex) {
        log.error("Exception in Service Method [{}]: {}",
                joinPoint.getSignature().toShortString(),
                ex.getMessage());
    }
}