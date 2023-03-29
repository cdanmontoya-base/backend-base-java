package com.cdanmontoya.base.infrastructure.adapters.input.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cdanmontoya.base.domain.model.Account;
import com.cdanmontoya.base.factories.domain.AccountFactory;
import com.cdanmontoya.base.factories.domain.ContactInformationFactory;
import com.cdanmontoya.base.factories.infrastructure.dto.InsertAccountRequestDtoFactory;
import com.cdanmontoya.base.infrastructure.acl.dto.InsertAccountRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.cdanmontoya")
public class AccountControllerIntegrationTest {

  @Autowired
  private MockMvc mvc;

  @Test
  void givenNoAccounts_whenGetAccounts_thenStatus200() throws Exception {
    mvc.perform(get("/accounts"))
        .andExpect(status().isOk());
  }

  @Test
  void givenNoAccounts_whenGetAccount_thenStatus200() throws Exception {
    mvc.perform(get("/accounts/" + UUID.randomUUID()))
        .andExpect(status().isOk());
  }

  @Test
  public void givenAnInvalidRequest_whenInsertingAccount_thenStatus400() throws Exception {
    Account invalid = new AccountFactory()
        .setContactInformation(new ContactInformationFactory().setEmail("invalid").get())
        .get();

    mvc.perform(post("/accounts", invalid))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void givenAValidRequest_whenInsertingAccount_thenStatus200() throws Exception {
    InsertAccountRequestDto dto = new InsertAccountRequestDtoFactory().get();

    mvc.perform(
            post("/accounts")
                .content(new ObjectMapper().writer().writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
  }

  @Test
  public void givenAValidUUID_whenDeletingAccount_thenStatus200() throws Exception {
    InsertAccountRequestDto dto = new InsertAccountRequestDtoFactory().get();

    mvc.perform(delete("/accounts/" + UUID.randomUUID()))
        .andExpect(status().isOk());
  }


}
