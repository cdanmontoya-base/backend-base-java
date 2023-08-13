package com.cdanmontoya.base.infrastructure.configuration.messagepublisher;

import com.cdanmontoya.ddd.Message;
import com.cdanmontoya.ddd.MessagePublisher;
import io.vavr.control.Try;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Aspect
@Component
public class EventPublisherAspect {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final MessagePublisher messagePublisher;

  @Autowired
  public EventPublisherAspect(MessagePublisher messagePublisher) {
    this.messagePublisher = messagePublisher;
  }

  @Pointcut("@annotation(EventPublisher)")
  public void eventPublisherPointcut() {
  }

  @AfterReturning(pointcut = "eventPublisherPointcut()", returning = "returnValue")
  public void publishEvent(JoinPoint joinPoint, Object returnValue) {
    Try.of(() -> (Mono<Message>) returnValue)
        .onSuccess(messageMono -> messageMono.doOnNext(messagePublisher::publish).subscribe())
        .onFailure(throwable -> logger.error("Error on publisher: {}", throwable.getMessage()));
  }

}
