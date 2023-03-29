package com.cdanmontoya.base.factories;

import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.base.domain.model.AccountId;
import com.cdanmontoya.base.domain.model.ContactInformation;

public class AccountFactory {
  private AccountId accountId;
  private ContactInformation contactInformation;

  public AccountFactory() {
    accountId = new AccountIdFactory().get();
    contactInformation = new ContactInformationFactory().get();
  }

  public AccountFactory setAccountId(AccountId accountId) {
    this.accountId = accountId;
    return this;
  }

  public AccountFactory setContactInformation(
      ContactInformation contactInformation) {
    this.contactInformation = contactInformation;
    return this;
  }

  public Account get() {
    return new Account(
        accountId,
        contactInformation
    );
  }

}
