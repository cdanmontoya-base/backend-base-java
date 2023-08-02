package com.cdanmontoya.base.factories.domain;

import com.cdanmontoya.base.application.commands.InsertAccount;
import io.vavr.collection.List;
import net.datafaker.Faker;

public class InsertAccountFactory {
  private String email;
  private List<String> cellphones;

  public InsertAccountFactory() {
    Faker faker = new Faker();

    email = faker.internet().emailAddress();
    cellphones = List.of(
        faker.phoneNumber().cellPhone(),
        faker.phoneNumber().cellPhone(),
        faker.phoneNumber().cellPhone()
    );
  }

  public InsertAccountFactory setEmail(String email) {
    this.email = email;
    return this;
  }

  public InsertAccountFactory setCellphones(List<String> cellphones) {
    this.cellphones = cellphones;
    return this;
  }

  public InsertAccount get() {
    return new InsertAccount(email, cellphones);
  }

}
