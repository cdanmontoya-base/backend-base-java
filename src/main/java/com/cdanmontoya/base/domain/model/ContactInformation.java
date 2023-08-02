package com.cdanmontoya.base.domain.model;

import com.cdanmontoya.ddd.ValueObject;
import io.vavr.collection.List;

public record ContactInformation(
    String email,
    List<String> cellphones

) implements ValueObject {

}
