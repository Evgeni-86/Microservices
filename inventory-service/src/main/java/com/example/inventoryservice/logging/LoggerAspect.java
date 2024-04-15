package com.example.inventoryservice.logging;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;


@Component
@Aspect
public class LoggerAspect {
    private static final Logger LOG = LoggerFactory.getLogger(LoggerAspect.class);

    @Around("execution(* com.example.inventoryservice.repository.*.*(..))")
    public Object repositoryMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return getObject(proceedingJoinPoint);
    }

    @Around("execution(* com.example.inventoryservice.service.*.*(..))")
    public Object serviceMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return getObject(proceedingJoinPoint);
    }

    @Around("execution(* com.example.inventoryservice.web.controller.*.*(..))")
    public Object controllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return getObject(proceedingJoinPoint);
    }

    private Object getObject(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object resultMethod = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();

        LOG.info("target class: {}; method signature: {}; arguments: {}; time: {} ms",
                proceedingJoinPoint.getTarget().toString(),
                proceedingJoinPoint.getSignature(),
                Arrays.stream(proceedingJoinPoint.getArgs())
                        .map(Object::toString).collect(Collectors.toList()), endTime - startTime);

        return resultMethod;
    }
}
