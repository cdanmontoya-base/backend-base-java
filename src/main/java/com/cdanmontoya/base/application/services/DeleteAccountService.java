package com.cdanmontoya.base.application.services;

import com.cdanmontoya.base.application.commands.DeleteAccount;
import com.cdanmontoya.base.application.ports.output.repositories.AccountRepository;
import com.cdanmontoya.base.domain.events.AccountDeleted;
import com.cdanmontoya.base.domain.events.AccountNotDeleted;
import com.cdanmontoya.base.domain.model.AccountId;
import com.cdanmontoya.base.infrastructure.configuration.messagepublisher.EventPublisher;
import com.cdanmontoya.ddd.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteAccountService {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final AccountRepository accountRepository;

  @Autowired
  public DeleteAccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @EventPublisher
  public Mono<Message> delete(DeleteAccount deleteAccount) {
    logger.atInfo().log("Deleting account {}", deleteAccount);
    return this.accountRepository
        .delete(deleteAccount.id())
        .map(deletedAccount -> getSuccessfulEvent(deletedAccount.orElseThrow()))
        .onErrorResume(throwable -> getErrorEvent(throwable.getMessage()));
  }

  private Message getSuccessfulEvent(AccountId account) {
    return new AccountDeleted(account);
  }

  private Mono<Message> getErrorEvent(String message) {
    return Mono.just(new AccountNotDeleted(message));
  }

}
