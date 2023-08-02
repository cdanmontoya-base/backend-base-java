package com.cdanmontoya.base.application.services;

import com.cdanmontoya.base.domain.events.AccountInserted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @EventListener
  public void handle(AccountInserted accountInserted) {
    logger.atInfo().log("Sending notification to user {}", accountInserted.getData().account().getId().id());
  }

}
