package com.example.restservice;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import org.springframework.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


@Component
@Aspect
public class LoggingAspect {
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   private long tempTimer;

    @Pointcut("@annotation(Loggable)")
    public void annotateLoggableMethod() {
    }
    @Before("annotateLoggableMethod()")
    public void logMethodNameAndParameters(JoinPoint jp) {
        tempTimer = System.currentTimeMillis();
        String methodName = jp.getSignature().getName();
        logger.info("[Before] Вызов метода " + methodName + " с параметрами = " + CollectionUtils.arrayToList(jp.getArgs()));
    }

    @After("annotateLoggableMethod()")
    public void logMethodName(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.info("[After] Метод завершен " + methodName + " время выполнения (ms) " + (System.currentTimeMillis() - tempTimer));
    }

    @AfterThrowing(pointcut = "execution(public Greeting com.example.restservice.GreetingController.*(..))", throwing = "result")
    public void logAfterReturning(JoinPoint joinPoint, Throwable result) {
        logger.info("[AfterThrowing] ошибка " + joinPoint.getSignature().getName() + " thrown value: " + result);
    }
    @AfterReturning(pointcut = "execution(public Greeting com.example.restservice.GreetingController.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("[AfterReturning] Успешное завершение " + joinPoint.getSignature().getName() + " return value: " + result.toString());
    }

    @Around("@annotation(Loggable)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        logger.info("[Around старт] " + joinPoint.getSignature().getName() + " parameters =" + CollectionUtils.arrayToList(joinPoint.getArgs()));
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info("[Around финиш] " + joinPoint.getSignature().getName() + " result= " + proceed + " время выполнения (ms) " + executionTime);
        return proceed;
    }
}
