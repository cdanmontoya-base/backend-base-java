package com.cdanmontoya.base.application.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.cdanmontoya.base.domain.model.Account;
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
public class QueryAccountServiceIntegrationTest {

  @Autowired
  private QueryAccountsService queryAccountsService;

  @Test
  void givenNoAccounts_whenFindingAll_shouldBeEmpty() {
    List<Account> list = queryAccountsService.findAll().toStream().toList();

    assertThat(list).isEmpty();

  }

}
