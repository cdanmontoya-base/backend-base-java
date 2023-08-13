package com.cdanmontoya.ddd;

public interface MessagePublisher {

  void publish(Message message);
}
