package com.cdanmontoya.base.infrastructure.adapters.output.repositories.account;

import com.cdanmontoya.base.application.ports.output.repositories.AccountRepository;
import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.base.domain.model.AccountId;
import com.cdanmontoya.base.infrastructure.acl.translators.account.AccountDaoTranslator;
import com.cdanmontoya.base.infrastructure.adapters.output.repositories.account.account.AccountDao;
import io.vavr.control.Try;
import java.util.Optional;
import org.apache.commons.lang3.NotImplementedException;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class AccountRepositoryJdbi implements AccountRepository {

  private final Jdbi database;

  @Autowired
  public AccountRepositoryJdbi(Jdbi database) {
    this.database = database;
  }

  @Override
  public Mono<Optional<Account>> findById(AccountId id) {
    return Mono.just(Optional.ofNullable(database.onDemand(AccountDao.class).findById(id)))
        .map(accountRecord -> accountRecord.map(AccountDaoTranslator::of));
  }

  @Override
  public Flux<Account> findAll() {
    return Flux.fromIterable(database.onDemand(AccountDao.class).findAll())
        .map(AccountDaoTranslator::of);
  }

  @Override
  public Mono<Optional<Account>> insert(Account account) {
    return database
        .onDemand(AccountDao.class)
        .insert(AccountDaoTranslator.of(account))
        .map(accountRecord -> Optional.of(AccountDaoTranslator.of(accountRecord)));
  }

  @Override
  public Mono<Optional<Account>> update(AccountId id, Account data) {
    throw new NotImplementedException();
  }

  @Override
  public Mono<Optional<AccountId>> delete(AccountId id) {
    return Try.of(() -> {
          database.onDemand(AccountDao.class).delete(id);
          return Optional.of(id);
        })
        .toEither()
        .fold(
            Mono::error,
            Mono::just
        );
  }

}
