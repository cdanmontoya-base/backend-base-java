package com.cdanmontoya.ddd;

public interface Identifiable<K extends Identifier> {

  K getId();
}
