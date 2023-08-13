package com.cdanmontoya.base.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.cdanmontoya.common.DTO;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.cdanmontoya.base")
public class DtoRules {

  @ArchTest
  public static final ArchRule placement = classes()
      .that().areAnnotatedWith(DTO.class)
      .should().resideInAPackage("..acl.dto..");

  @ArchTest
  public static final ArchRule naming = classes()
      .that().resideInAPackage("..acl.dto..")
      .and().areNotEnums()
      .should().haveSimpleNameEndingWith("Dto");

  @ArchTest
  public static final ArchRule annotation = classes()
      .that().resideInAPackage("..acl.dto..")
      .and().areNotEnums()
      .should().beAnnotatedWith(DTO.class);

}
