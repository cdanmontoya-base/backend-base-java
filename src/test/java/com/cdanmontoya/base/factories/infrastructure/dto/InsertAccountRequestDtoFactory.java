package com.cdanmontoya.base.factories.infrastructure.dto;

import com.cdanmontoya.base.infrastructure.acl.dto.InsertAccountRequestDto;
import io.vavr.collection.List;
import net.datafaker.Faker;

public class InsertAccountRequestDtoFactory {

  private String email;
  private List<String> cellphones;

  public InsertAccountRequestDtoFactory() {
    Faker faker = new Faker();

    email = faker.internet().emailAddress();
    cellphones = List.of(
        faker.phoneNumber().cellPhone(),
        faker.phoneNumber().cellPhone(),
        faker.phoneNumber().cellPhone()
    );
  }

  public InsertAccountRequestDtoFactory setEmail(String email) {
    this.email = email;
    return this;
  }

  public InsertAccountRequestDtoFactory setCellphones(List<String> cellphones) {
    this.cellphones = cellphones;
    return this;
  }

  public InsertAccountRequestDto get() {
    return new InsertAccountRequestDto(
        email,
        cellphones
    );
  }
}
