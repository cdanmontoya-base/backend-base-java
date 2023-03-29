package com.cdanmontoya.ddd;

import reactor.core.publisher.Mono;

public interface MessagePublisher {

  Mono<Message> publish(Message event);
}
