package com.cdanmontoya.base.application.ports.output.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.base.factories.AccountFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.cdanmontoya")
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

}
