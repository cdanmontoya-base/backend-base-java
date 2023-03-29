package com.cdanmontoya.base.domain.events;

import com.cdanmontoya.base.domain.events.AccountInserted.Data;
import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.ddd.AbstractEvent;

public class AccountInserted extends AbstractEvent<Data> {

  private final Data data;

  public AccountInserted(Account account) {
    super("base", "1.0.0");
    this.data = new Data(account);
  }

  protected record Data(Account account) {

  }

  @Override
  public Data getData() {
    return data;
  }
}
