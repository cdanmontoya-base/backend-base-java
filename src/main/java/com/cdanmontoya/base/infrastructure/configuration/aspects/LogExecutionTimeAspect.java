package com.cdanmontoya.base.infrastructure.configuration.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExecutionTimeAspect {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Around("@annotation(LogExecutionTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    final long start = System.currentTimeMillis();
    final Object proceed = joinPoint.proceed();
    final long executionTime = System.currentTimeMillis() - start;

    logger.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);

    return proceed;
  }

}
