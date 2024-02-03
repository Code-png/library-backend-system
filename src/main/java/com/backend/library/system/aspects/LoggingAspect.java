package com.backend.library.system.aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    @Around("execution(* com.backend.library.system..*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), joinPoint.getArgs());

        try {
            Object result = joinPoint.proceed();
            log.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", joinPoint.getArgs(),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e; //This will be caught by the GlobalExceptionHandler and return the adequate message and httpstatus to the user
        }
    }

    @Around("execution(* com.backend.library.system.controllers.BookController.addBook(..)) || " +
            "execution(* com.backend.library.system.controllers.BookController.updateBook(..)) || " +
            "execution(* com.backend.library.system.controllers.PatronController.addPatron(..)) || " +
            "execution(* com.backend.library.system.controllers.PatronController.updatePatron(..))")
    public Object logEndpointMetrics(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info("Execution time of {} is {} ms", joinPoint.getSignature(), stopWatch.getTotalTimeMillis());
        }
    }

    @AfterThrowing(pointcut = "execution(* com.backend.library.system..*(..))", throwing = "e")
    public void logException(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getMessage());
    }
}
