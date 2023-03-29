package com.cdanmontoya.base.infrastructure.adapters.output.repositories.account.cellphone;

import java.util.UUID;

public record CellphoneRecord(
    UUID accountId,
    String number
) {

}
