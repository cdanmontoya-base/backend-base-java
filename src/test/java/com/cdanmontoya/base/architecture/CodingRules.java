package com.cdanmontoya.base.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.slf4j.Logger;

@AnalyzeClasses(packages = "com.cdanmontoya.base")
public class CodingRules {

  @ArchTest
  private final ArchRule noClassesShouldUseJavaUtilLogging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING
      .because("SL4J is our implementation");

  @ArchTest
  private final ArchRule loggersShouldBePrivateStaticFinal =
      fields().that().haveRawType(Logger.class)
          .should().bePrivate()
          .andShould().beStatic()
          .andShould().beFinal()
          .because("we agreed on this convention");

  @ArchTest
  private final ArchRule noFieldInjection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;


}
