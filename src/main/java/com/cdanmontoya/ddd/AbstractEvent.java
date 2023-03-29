package com.cdanmontoya.ddd;

import java.time.ZonedDateTime;
import java.util.UUID;

public class AbstractEvent<T> implements Event {

  private final UUID id;
  private final ZonedDateTime occurredOn;
  private final String source;
  private final String name;
  private final String version;
  private T data;

  protected AbstractEvent(String source, String version) {
    this.id = UUID.randomUUID();
    this.occurredOn = ZonedDateTime.now();
    this.source = source;
    this.name = this.getClass().getSimpleName();
    this.version = version;
  }

  public UUID getId() {
    return id;
  }

  public ZonedDateTime getOccurredOn() {
    return occurredOn;
  }

  public String getSource() {
    return source;
  }

  public String getName() {
    return name;
  }

  public String getVersion() {
    return version;
  }

  public T getData() {
    return data;
  }

}
