package com.cdanmontoya.base.application.ports.output.repositories;

import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.base.domain.model.AccountId;
import com.cdanmontoya.ddd.Repository;

public interface AccountRepository extends Repository<Account, AccountId> {

}
