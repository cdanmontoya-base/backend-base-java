package com.cdanmontoya.base.architecture;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = {"com.cdanmontoya.base.application", "com.cdanmontoya.base.domain", "com.cdanmontoya.base.infrastructure"})
public class LayeredArchitectureRules {

  @ArchTest
  public static final ArchRule portsAndAdapters = layeredArchitecture()
      .consideringAllDependencies()
      .withOptionalLayers(Boolean.TRUE)
      .layer("Controllers").definedBy("infrastructure.adapters.input.controllers..")
      .layer("Queries").definedBy("application.queries..")
      .layer("Commands").definedBy("application.commands..")
      .layer("Ports").definedBy("application.ports..")
      .layer("Repositories").definedBy("application.ports.output.repositories..")
      .layer("ApplicationServices").definedBy("application.services..")
      .layer("Model").definedBy("domain.model..")
      .layer("DomainServices").definedBy("domain.services..")
      .layer("Events").definedBy("domain.events..")
      .layer("Adapters").definedBy("infrastructure.adapters..")

      .whereLayer("Model").mayNotAccessAnyLayer()
      .whereLayer("Controllers").mayNotAccessAnyLayer();

}
