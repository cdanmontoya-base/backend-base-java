package com.cdanmontoya.base.infrastructure.adapters.input.controllers;

import com.cdanmontoya.base.application.commands.DeleteAccount;
import com.cdanmontoya.base.application.services.DeleteAccountService;
import com.cdanmontoya.base.application.services.InsertAccountService;
import com.cdanmontoya.base.application.services.QueryAccountsService;
import com.cdanmontoya.base.domain.events.AccountDeleted;
import com.cdanmontoya.base.domain.events.AccountInserted;
import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.base.domain.model.AccountId;
import com.cdanmontoya.base.infrastructure.acl.dto.InsertAccountRequestDto;
import com.cdanmontoya.base.infrastructure.acl.translators.registeraccount.RegisterAccountRequestDtoTranslator;
import com.cdanmontoya.ddd.Message;
import jakarta.validation.Valid;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final QueryAccountsService queryAccountsService;
  private final InsertAccountService insertAccountService;
  private final DeleteAccountService deleteAccountService;


  @Autowired
  public AccountController(
      InsertAccountService insertAccountService,
      QueryAccountsService queryAccountsService,
      DeleteAccountService deleteAccountService) {
    this.insertAccountService = insertAccountService;
    this.queryAccountsService = queryAccountsService;
    this.deleteAccountService = deleteAccountService;
  }

  @GetMapping
  public Flux<Account> getAll() {
    return queryAccountsService.findAll();
  }

  @GetMapping("/{accountId}")
  public Mono<Optional<Account>> get(@PathVariable UUID accountId) {
    logger.atInfo().setMessage("GET request for user {}").addArgument(accountId).log();

    return queryAccountsService.findById(new AccountId(accountId));
  }

  @PostMapping
  public Mono<ResponseEntity<Message>> insert(@Valid @RequestBody InsertAccountRequestDto dto) {
    logger.atInfo().log("Executing insert request with payload {}", dto);

    return insertAccountService.register(RegisterAccountRequestDtoTranslator.of(dto))
        .map(AccountController::getResponseEntity);
  }

  @DeleteMapping("/{accountId}")
  public Mono<Message> delete(@PathVariable UUID accountId) {
    logger.atInfo().log("Deleting account {}", accountId);
    DeleteAccount deleteAccount = new DeleteAccount(new AccountId(accountId));

    return deleteAccountService.delete(deleteAccount);
  }

  // TODO: refactor (feels too hacky)
  private static ResponseEntity<Message> getResponseEntity(Message message) {
    return (message instanceof AccountInserted) || (message instanceof AccountDeleted)
        ? ResponseEntity.status(HttpStatus.OK).body(message)
        : ResponseEntity.internalServerError().body(message);
  }
}
