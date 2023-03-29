package com.cdanmontoya.base.domain.events;

import com.cdanmontoya.base.domain.events.AccountDeleted.Data;
import com.cdanmontoya.base.domain.model.AccountId;
import com.cdanmontoya.ddd.AbstractEvent;

public class AccountDeleted extends AbstractEvent<Data> {
  private final Data data;

  public AccountDeleted(AccountId account) {
    super("base", "1.0.0");
    data = new Data(account);
  }

  protected record Data(AccountId account) {

  }

  @Override
  public Data getData() {
    return data;
  }
}
