package com.cdanmontoya.base.application.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.cdanmontoya.base.application.commands.DeleteAccount;
import com.cdanmontoya.base.application.ports.output.repositories.AccountRepository;
import com.cdanmontoya.base.domain.events.AccountDeleted;
import com.cdanmontoya.base.domain.events.AccountNotDeleted;
import com.cdanmontoya.base.domain.model.AccountId;
import com.cdanmontoya.base.factories.domain.AccountIdFactory;
import com.cdanmontoya.ddd.Message;
import com.cdanmontoya.ddd.MessagePublisher;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

@TestInstance(Lifecycle.PER_CLASS)
class DeleteAccountServiceTest {
  private AccountRepository accountRepository;
  private MessagePublisher messagePublisher;
  private DeleteAccountService deleteAccountService;

  @BeforeAll
  public void setup() {
    accountRepository = mock(AccountRepository.class);
    messagePublisher = mock(MessagePublisher.class);

    deleteAccountService = new DeleteAccountService(accountRepository);
  }

  @AfterEach
  public void reset_mocks() {
    Mockito.reset(accountRepository);
    Mockito.reset(messagePublisher);
  }

  @Test
  void givenAValidDeleteAccountCommand_whenDeletingAccount_shouldReturnAccountDeleted() {
    when(accountRepository.delete(any()))
        .thenAnswer(invocation -> Mono.just(Optional.of(((AccountId) invocation.getArguments()[0]))));

    // when(messagePublisher.publish(any())).thenAnswer(invocation -> Mono.just(invocation.getArguments()[0]));

    DeleteAccount deleteAccount = new DeleteAccount(new AccountIdFactory().get());

    Message block = deleteAccountService.delete(deleteAccount).block();


    assertThat(block).isInstanceOf(AccountDeleted.class);
  }

  @Test
  void givenAnError_whenDeletingAccount_shouldReturnAccountNotDeleted() {
    when(accountRepository.delete(any()))
        .thenReturn(Mono.just(Optional.empty()));

    // when(messagePublisher.publish(any())).thenAnswer(invocation -> Mono.just(invocation.getArguments()[0]));

    DeleteAccount deleteAccount = new DeleteAccount(new AccountIdFactory().get());

    Message block = deleteAccountService.delete(deleteAccount).block();

    assertThat(block).isInstanceOf(AccountNotDeleted.class);

  }

}
