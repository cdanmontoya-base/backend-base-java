package com.cdanmontoya.base.factories;

import com.cdanmontoya.base.domain.model.AccountId;
import java.util.UUID;

public class AccountIdFactory {
  private UUID id;

  public AccountIdFactory() {
    id = UUID.randomUUID();
  }

  public AccountIdFactory setId(UUID id) {
    this.id = id;
    return this;
  }

  public AccountId get() {
    return new AccountId(id);
  }
}
