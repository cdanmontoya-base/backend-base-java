package com.cdanmontoya.base.domain.model;

import com.cdanmontoya.ddd.AggregateRoot;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Account implements AggregateRoot<AccountId> {

  private final AccountId id;
  private final ContactInformation contactInformation;

  public Account(AccountId id, ContactInformation contactInformation) {
    this.id = id;
    this.contactInformation = contactInformation;
  }


  public ContactInformation getContactInformation() {
    return contactInformation;
  }

  @Override
  public AccountId getId() {
    return id;
  }
}
