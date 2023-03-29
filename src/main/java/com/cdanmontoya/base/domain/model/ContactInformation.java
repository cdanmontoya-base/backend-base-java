package com.cdanmontoya.base.domain.model;

import com.cdanmontoya.ddd.ValueObject;
import java.util.List;

public record ContactInformation(
    String email,
    List<String> cellphones

) implements ValueObject {

}
