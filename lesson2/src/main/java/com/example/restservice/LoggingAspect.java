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

    @Around("@annotation(Loggable)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        logger.info("[Around старт] " + joinPoint.getSignature().getName() + " parameters =" + CollectionUtils.arrayToList(joinPoint.getArgs()));
        Object proceed = joinPoint.proceed();
        logger.info("[Around финиш] " + joinPoint.getSignature().getName() + " result= " + proceed + " время выполнения (ms) " + (System.currentTimeMillis() - start));
        return proceed;
    }

    @Before("annotateLoggableMethod()")
    public void logMethodNameAndParameters(JoinPoint jp) {
        tempTimer = System.currentTimeMillis();
        logger.info("[Before] Вызов метода " + jp.getSignature().getName() + " с параметрами = " + CollectionUtils.arrayToList(jp.getArgs()));
    }

    @After("annotateLoggableMethod()")
    public void logMethodName(JoinPoint jp) {
        logger.info("[After] Метод завершен " + jp.getSignature().getName() + " время выполнения (ms) " + (System.currentTimeMillis() - tempTimer));
    }

    @AfterThrowing(pointcut = "execution(public Greeting com.example.restservice.GreetingController.*(..))", throwing = "result")
    public void logAfterReturning(JoinPoint joinPoint, Throwable result) {
        logger.info("[AfterThrowing] ошибка " + joinPoint.getSignature().getName() + " thrown value: " + result);
    }
    @AfterReturning(pointcut = "execution(public Greeting com.example.restservice.GreetingController.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("[AfterReturning] Успешное завершение " + joinPoint.getSignature().getName() + " return value: " + result.toString());
    }
}
