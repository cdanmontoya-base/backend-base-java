package com.cdanmontoya.base.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.cdanmontoya.base")
public class PortsAndAdaptersRules {
//  @ArchTest
//  public static ArchRule portsShouldReturnDomainEntities = methods()
//      .that().areDeclaredInClassesThat().resideInAnyPackage("application.ports..")
//      .or().areDeclaredInClassesThat().resideInAnyPackage("infrastructure.adapters.output..")
//      .and().arePublic()
//      .should().

}
