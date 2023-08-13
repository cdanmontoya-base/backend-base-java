package com.cdanmontoya.base.infrastructure.configuration.messagepublisher;

import com.cdanmontoya.ddd.Message;
import com.cdanmontoya.ddd.MessagePublisher;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SpringMessagePublisher implements MessagePublisher {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final ApplicationEventPublisher publisher;

  @Autowired
  public SpringMessagePublisher(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @Override
  public void publish(Message message) {
    Try.of(() -> {
          logger.atInfo().log("Publishing message {}", message);
          publisher.publishEvent(message);
          logger.atInfo().log("Published");
          return message;
        })
        .toEither()
        .fold(
            Mono::error,
            Mono::just
        );
  }
}
