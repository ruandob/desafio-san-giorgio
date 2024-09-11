package com.ruandob.challenge.archtests;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.ruandob.challenge", importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class})
public class CleanArchitectureTest {

    @ArchTest
    static ArchRule layers_should_respect_clean_architecture_patterns =
            layeredArchitecture()
                    .consideringAllDependencies()
                    .layer("api").definedBy("..api..")
                    .layer("domain").definedBy("..domain..")
                    .layer("infrastructure").definedBy("..infrastructure..")
                    .whereLayer("api").mayNotBeAccessedByAnyLayer()
                    .whereLayer("domain").mayOnlyBeAccessedByLayers("api", "infrastructure")
                    .whereLayer("infrastructure").mayOnlyBeAccessedByLayers("api", "domain");

}
