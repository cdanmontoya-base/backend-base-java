package com.cdanmontoya.base.application.ports.output.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.base.factories.domain.AccountFactory;
import com.cdanmontoya.base.infrastructure.configuration.database.ClearDatabase;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@ComponentScan(basePackages = "com.cdanmontoya")
@AutoConfigureMockMvc
@ClearDatabase
public class AccountRepositoryIntegrationTest {
  @Autowired
  private AccountRepository accountRepository;

  @Test
  void givenARandomAccount_whenInsertingAndThenQuerying_shouldHaveTheSameId() {
    Account account = new AccountFactory().get();

    Account inserted = accountRepository.insert(account).block().get();
    Account queried = accountRepository.findById(inserted.getId()).block().get();

    assertThat(queried.getId())
        .usingRecursiveComparison()
        .isEqualTo(account.getId());
  }

  @Test
  void givenNoAccount_whenFindingAll_shouldReturnEmptyList() {
    Account account = new AccountFactory().get();

    List<Account> list = accountRepository.findAll().toStream().toList();

    assertThat(list).isEmpty();
  }

  @Test
  void given3RandomAccounts_whenInsertingAndThenQuerying_shouldHaveLenght3() {
    Account account1 = new AccountFactory().get();
    Account account2 = new AccountFactory().get();
    Account account3 = new AccountFactory().get();

    accountRepository.insert(account1).block().get();
    accountRepository.insert(account2).block().get();
    accountRepository.insert(account3).block().get();

    List<Account> list = accountRepository.findAll().toStream().toList();

    assertThat(list)
        .hasSize(3);
  }

  @Test
  void given3RandomAccounts_whenDeletingOne_shouldHaveLenght2() {
    Account account1 = new AccountFactory().get();
    Account account2 = new AccountFactory().get();
    Account account3 = new AccountFactory().get();

    accountRepository.insert(account1).block().get();
    accountRepository.insert(account2).block().get();
    accountRepository.insert(account3).block().get();

    accountRepository.delete(account2.getId()).block();

    List<Account> list = accountRepository.findAll().toStream().toList();

    assertThat(list)
        .hasSize(2);
  }

}
