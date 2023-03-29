package com.cdanmontoya.base.factories.domain;

import com.cdanmontoya.base.domain.model.ContactInformation;
import java.util.List;
import net.datafaker.Faker;

public class ContactInformationFactory {

  private String email;
  private List<String> cellphones;

  public ContactInformationFactory() {
    Faker faker = new Faker();
    email = faker.internet().emailAddress();
    cellphones = List.of(
        faker.phoneNumber().cellPhone(),
        faker.phoneNumber().cellPhone(),
        faker.phoneNumber().cellPhone()
    );
  }

  public ContactInformationFactory setEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactInformationFactory setCellphones(List<String> cellphones) {
    this.cellphones = cellphones;
    return this;
  }

  public ContactInformation get() {
    return new ContactInformation(
        email,
        cellphones
    );
  }
}
