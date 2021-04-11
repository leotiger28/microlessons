package ru.leo.springDemo.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());
    private ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("@annotation(Loggable)")
    public void getMethodProcessing() {
    }

    @Before("getMethodProcessing()")
    public void logInput(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        MethodSignature ms = (MethodSignature) jp.getSignature();
        Object[] args = jp.getArgs();
        Parameter[] params = ms.getMethod().getParameters();
        Map<String, Object> methodParams = new HashMap<>();
        int i = 0;
        for (Parameter p : params) {
            methodParams.put(p.getName(), args[i++]);
        }
        logger.log(Level.INFO, "Вызван метод " + methodName + " параметры: " + methodParams);
    }

    @AfterReturning(value = "@annotation(Loggable)", returning = "result")
    public void logOutput(JoinPoint jp, Object result) throws Exception {
        logger.log(Level.INFO, "Результат: " + objectMapper.writeValueAsString(result));
    }

}
