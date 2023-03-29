package com.cdanmontoya.base.application.services;

import com.cdanmontoya.base.application.commands.DeleteAccount;
import com.cdanmontoya.base.application.ports.output.repositories.AccountRepository;
import com.cdanmontoya.base.domain.events.AccountDeleted;
import com.cdanmontoya.base.domain.events.AccountNotDeleted;
import com.cdanmontoya.base.domain.events.AccountNotInserted;
import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.ddd.Message;
import com.cdanmontoya.ddd.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteAccountService {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final AccountRepository accountRepository;
  private final MessagePublisher messagePublisher;

  @Autowired
  public DeleteAccountService(AccountRepository accountRepository,
      MessagePublisher messagePublisher) {
    this.accountRepository = accountRepository;
    this.messagePublisher = messagePublisher;
  }

  // TODO: la operación de obtener los eventos y publicar es repetitiva. ¿Se puede definir en una función?
  public Mono<Message> delete(DeleteAccount deleteAccount) {
    return this.accountRepository
        .delete(deleteAccount.id())
        .map(deletedAccount -> getSuccessfulEvent(deletedAccount.orElseThrow()))
        .onErrorResume(throwable -> getErrorEvent(throwable.getMessage()))
        .flatMap(messagePublisher::publish);
  }

  private Message getSuccessfulEvent(Account account) {
    return new AccountDeleted(account);
  }

  private Mono<Message> getErrorEvent(String message) {
    return Mono.just(new AccountNotDeleted(message));
  }


}
