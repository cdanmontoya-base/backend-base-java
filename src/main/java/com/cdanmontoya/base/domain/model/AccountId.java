package com.cdanmontoya.base.domain.model;

import com.cdanmontoya.ddd.Identifier;
import java.util.UUID;

public record AccountId(UUID id) implements Identifier {

}
