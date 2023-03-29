package com.cdanmontoya.base;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaPackage;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.metrics.ArchitectureMetrics;
import com.tngtech.archunit.library.metrics.LakosMetrics;
import com.tngtech.archunit.library.metrics.MetricsComponents;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseApplicationTests {

  @Test
  void contextLoads() {
  }

  @Test
  void metrics() {
    JavaClasses classes = new ClassFileImporter().importPackages("com.cdanmontoya");

    Set<JavaPackage> packages = classes.getPackage("com.cdanmontoya").getSubpackages();
    MetricsComponents<JavaClass> components = MetricsComponents.fromPackages(packages);

    LakosMetrics lakosMetrics = ArchitectureMetrics.lakosMetrics(components);

    System.out.println("Cumulative Component Dependency (CCD): "
        + lakosMetrics.getCumulativeComponentDependency());
    System.out.println(
        "Average Component Dependency (ACD): " + lakosMetrics.getAverageComponentDependency());
    System.out.println("Relative Average Component Dependency (RACD): "
        + lakosMetrics.getRelativeAverageComponentDependency());
    System.out.println("Normalized Cumulative Component Dependency (NCCD): "
        + lakosMetrics.getNormalizedCumulativeComponentDependency());

  }

}
