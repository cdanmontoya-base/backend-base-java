package com.cdanmontoya.base.domain.events;

import com.cdanmontoya.base.domain.events.AccountDeleted.Data;
import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.ddd.AbstractEvent;

public class AccountDeleted extends AbstractEvent<Data> {
  private final Data data;

  public AccountDeleted(Account account) {
    super("base", "1.0.0");
    data = new Data(account);
  }

  protected record Data(Account account) {

  }

  @Override
  public Data getData() {
    return data;
  }
}
