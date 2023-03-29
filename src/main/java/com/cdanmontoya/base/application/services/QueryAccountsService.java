package com.cdanmontoya.base.application.services;

import com.cdanmontoya.base.application.ports.output.repositories.AccountRepository;
import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.base.domain.model.AccountId;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QueryAccountsService {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final AccountRepository accountRepository;

  @Autowired
  public QueryAccountsService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Flux<Account> findAll() {
    logger.atInfo().log("Finding all accounts");
    return accountRepository.findAll();
  }

  public Mono<Optional<Account>> findById(AccountId id) {
    logger.atInfo().log("Finding account by id {}", id);
    return accountRepository.findById(id);
  }
}
