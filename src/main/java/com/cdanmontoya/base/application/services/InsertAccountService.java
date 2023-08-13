package com.cdanmontoya.base.application.services;

import com.cdanmontoya.base.application.commands.InsertAccount;
import com.cdanmontoya.base.application.ports.output.repositories.AccountRepository;
import com.cdanmontoya.base.domain.events.AccountInserted;
import com.cdanmontoya.base.domain.events.AccountNotInserted;
import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.base.domain.model.AccountId;
import com.cdanmontoya.base.domain.model.ContactInformation;
import com.cdanmontoya.base.infrastructure.configuration.messagepublisher.EventPublisher;
import com.cdanmontoya.ddd.Message;
import com.cdanmontoya.ddd.MessagePublisher;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class InsertAccountService {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final AccountRepository accountRepository;

  @Autowired
  public InsertAccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @EventPublisher
  public Mono<Message> insert(InsertAccount insertAccount) {
    logger.atInfo().log("Registering account {}", insertAccount);

    Account account = new Account(
        new AccountId(UUID.randomUUID()),
        new ContactInformation(
            insertAccount.email(),
            insertAccount.cellphones()
        )
    );

    return accountRepository.insert(account)
        .map(insertedAccount -> getSuccessfulEvent(insertedAccount.orElseThrow()))
        .onErrorResume(throwable -> getErrorEvent(throwable.getMessage()));
  }

  private Message getSuccessfulEvent(Account account) {
    return new AccountInserted(account);
  }

  private Mono<Message> getErrorEvent(String message) {
    return Mono.just(new AccountNotInserted(message));
  }

}
