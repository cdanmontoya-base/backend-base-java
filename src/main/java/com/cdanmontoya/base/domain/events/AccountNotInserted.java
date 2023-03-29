package com.cdanmontoya.base.domain.events;

import com.cdanmontoya.base.domain.events.AccountNotInserted.Data;
import com.cdanmontoya.ddd.AbstractEvent;

public class AccountNotInserted extends AbstractEvent<Data> {

  private final Data data;

  public AccountNotInserted(String message) {
    super("base", "1.0.0");
    this.data = new Data(message);
  }

  protected record Data(String message) {

  }

  @Override
  public Data getData() {
    return data;
  }

}
