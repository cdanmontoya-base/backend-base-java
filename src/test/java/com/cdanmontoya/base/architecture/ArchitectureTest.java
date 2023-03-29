package com.cdanmontoya.base.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.cdanmontoya.ddd.Event;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.cdanmontoya.base")
public class ArchitectureTest {

  @ArchTest
  public static final ArchRule eventPlacement = classes()
      .that().implement(Event.class)
      .should().resideInAPackage("..domain.events..");

}
