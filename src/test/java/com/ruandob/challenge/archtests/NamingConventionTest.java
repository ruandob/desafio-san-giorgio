package com.ruandob.challenge.archtests;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.ruandob.challenge", importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class})
public class NamingConventionTest {

    @ArchTest
    ArchRule controllers_should_be_suffixed =
            classes()
                    .that()
                    .areNotAnonymousClasses()
                    .and()
                    .resideInAPackage("..api..controllers")
                    .should()
                    .haveSimpleNameContaining("Controller");

    @ArchTest
    ArchRule middlewares_should_be_suffixed =
            classes()
                    .that()
                    .resideInAPackage("..api.middlewares..")
                    .should()
                    .haveSimpleNameContaining("Middleware");

    @ArchTest
    ArchRule mappers_should_be_suffixed =
            classes()
                    .that()
                    .resideInAPackage("..api..mappers")
                    .should()
                    .haveSimpleNameEndingWith("Mapper")
                    .orShould()
                    .haveSimpleNameEndingWith("MapperImpl")
                    .allowEmptyShould(true);

    @ArchTest
    ArchRule usecases_should_be_suffixed =
            classes()
                    .that()
                    .resideInAPackage("..domain..usecases")
                    .should()
                    .haveSimpleNameEndingWith("UseCase")
                    .orShould()
                    .haveSimpleNameEndingWith("UseCaseImpl");

    @ArchTest
    ArchRule gateways_should_be_suffixed =
            classes()
                    .that()
                    .resideInAPackage("..domain..gateways")
                    .should()
                    .haveSimpleNameEndingWith("Gateway");

    @ArchTest
    ArchRule strategies_should_be_suffixed =
            classes()
                    .that()
                    .resideInAPackage("..domain..strategies")
                    .should()
                    .haveSimpleNameEndingWith("Strategy")
                    .orShould()
                    .haveSimpleNameEndingWith("StrategyImpl");

    @ArchTest
    ArchRule dataproviders_should_be_suffixed =
            classes()
                    .that()
                    .resideInAPackage("..infrastructure..dataproviders..providers")
                    .should()
                    .haveSimpleNameEndingWith("Provider");

    @ArchTest
    ArchRule services_should_be_suffixed =
            classes()
                    .that()
                    .resideInAPackage("..infrastructure..dataproviders..services")
                    .should()
                    .haveSimpleNameEndingWith("Service");

    @ArchTest
    ArchRule repositories_should_be_suffixed =
            classes()
                    .that()
                    .resideInAPackage("..infrastructure..dataproviders..repositories")
                    .should()
                    .haveSimpleNameEndingWith("Repository");

    @ArchTest
    ArchRule entities_should_be_suffixed =
            classes()
                    .that()
                    .resideInAPackage("..infrastructure..dataproviders..entities")
                    .should()
                    .haveSimpleNameEndingWith("Entity");
}
