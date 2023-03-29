package com.cdanmontoya.ddd;

import java.util.Optional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface Repository<T extends AggregateRoot<K>, K extends Identifier> {

  Mono<Optional<T>> findById(K id);

  Flux<T> findAll();

  Mono<Optional<T>> insert(T t);

  Mono<Optional<T>> update(K id, T data);

  Mono<Optional<T>> delete(K id);
}
