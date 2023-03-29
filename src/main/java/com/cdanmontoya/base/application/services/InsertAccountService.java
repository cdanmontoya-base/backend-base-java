package com.cdanmontoya.base.application.services;

import com.cdanmontoya.base.application.commands.RegisterAccount;
import com.cdanmontoya.base.application.ports.output.repositories.AccountRepository;
import com.cdanmontoya.base.domain.events.AccountNotInserted;
import com.cdanmontoya.base.domain.events.AccountInserted;
import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.base.domain.model.AccountId;
import com.cdanmontoya.base.domain.model.ContactInformation;
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

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final AccountRepository accountRepository;
  private final MessagePublisher messagePublisher;

  @Autowired
  public InsertAccountService(AccountRepository accountRepository,
      MessagePublisher messagePublisher) {
    this.accountRepository = accountRepository;
    this.messagePublisher = messagePublisher;
  }

  public Mono<Message> register(RegisterAccount registerAccount) {
    logger.atInfo().log("Registering account {}", registerAccount);

    Account account = new Account(
        new AccountId(UUID.randomUUID()),
        new ContactInformation(
            registerAccount.email(),
            registerAccount.cellphones()
        )
    );

    return accountRepository.insert(account)
        .map(insertedAccount -> getSuccessfulEvent(insertedAccount.orElseThrow()))
        .onErrorResume(throwable -> getErrorEvent(throwable.getMessage()))
        .flatMap(messagePublisher::publish);
  }

  private Message getSuccessfulEvent(Account account) {
    return new AccountInserted(account);
  }

  private Mono<Message> getErrorEvent(String message) {
    return Mono.just(new AccountNotInserted(message));
  }

}
