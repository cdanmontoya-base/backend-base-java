package com.cdanmontoya.base.infrastructure.acl.translators.account;

import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.base.domain.model.AccountId;
import com.cdanmontoya.base.domain.model.ContactInformation;
import com.cdanmontoya.base.infrastructure.adapters.output.repositories.account.account.AccountRecord;

public class AccountDaoTranslator {

  public static Account of(AccountRecord accountRecord) {
    return new Account(
        new AccountId(accountRecord.id()),
        new ContactInformation(accountRecord.email(), accountRecord.cellphones())
    );
  }

  public static AccountRecord of(Account account) {
    return new AccountRecord(
        account.getId().id(),
        account.getContactInformation().email(),
        account.getContactInformation().cellphones()
    );
  }

}
