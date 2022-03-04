package pt.switchprogram.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestTimeLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestTimeLogger.class);

    @Around("execution(* pt.switchprogram.controllers.StudentsController.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long before = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long lasted = System.currentTimeMillis() - before;
            LOGGER.info("method={} lasted={} millis", joinPoint.getSignature().getName(), lasted);
        }
    }
}
