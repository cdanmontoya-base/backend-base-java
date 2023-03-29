package com.cdanmontoya.base.application.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.cdanmontoya.base.application.commands.InsertAccount;
import com.cdanmontoya.base.domain.events.AccountInserted;
import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.base.factories.domain.InsertAccountFactory;
import com.cdanmontoya.base.infrastructure.configuration.database.ClearDatabase;
import com.cdanmontoya.ddd.Message;
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
public class InsertAccountServiceIntegrationTest {

  @Autowired
  private InsertAccountService insertAccountService;

  @Autowired
  private QueryAccountsService queryAccountsService;

  @Test
  void givenAValidInsertCommand_whenInserting_shouldBeOneAccount() {
    InsertAccount insertAccount = new InsertAccountFactory().get();

    Message block = insertAccountService.insert(insertAccount).block();

    List<Account> accounts = queryAccountsService.findAll().toStream().toList();

    assertThat(block).isInstanceOf(AccountInserted.class);
    assertThat(accounts).hasSize(1);

  }

}
