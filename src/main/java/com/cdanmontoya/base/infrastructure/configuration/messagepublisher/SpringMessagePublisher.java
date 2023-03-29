package com.cdanmontoya.base.infrastructure.configuration.messagepublisher;

import com.cdanmontoya.ddd.Message;
import com.cdanmontoya.ddd.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SpringMessagePublisher implements MessagePublisher {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final ApplicationEventPublisher publisher;

  @Autowired
  public SpringMessagePublisher(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @Override
  public Mono<Message> publish(Message event) {
    logger.atInfo().log("Publishing event {}", event);
    publisher.publishEvent(event);
    logger.atInfo().log("Published");

    return Mono.just(event);
  }
}
