# selenium-java-bdd-testing

BDD test suite for [Swag Labs](https://www.saucedemo.com/), Sauce Labs' free
demo e-commerce app used for Selenium/Cucumber practice: login, product
listing, cart, and checkout.

Standard Maven + Selenium WebDriver + Cucumber-JVM + JUnit 5 project layout,
with the Page Object Model.

## Running

```
mvn test
mvn test -Dcucumber.filter.tags="@smoke"
mvn test -Dcucumber.filter.tags="@regression and not @negative"
```

Chrome is driven headless by default is **off** - set `headless=true` in
`src/test/resources/config.properties` (or `-Dheadless=true`) for CI.
`WebDriverManager` downloads/caches the matching chromedriver automatically,
no manual driver setup required.

## Layout

```
pom.xml
src/test/java/com/example/saucedemo/
  runner/            TestRunner.java - the JUnit 5 Suite entry point (@IncludeEngines("cucumber"))
  pages/             Page Object Model: BasePage + one class per screen
  stepdefinitions/   Cucumber step definitions + Hooks (driver lifecycle, failure screenshots)
  util/              DriverFactory, ConfigReader, SortOptionMapper - plain helper classes
src/test/resources/
  features/          *.feature files (Gherkin)
  config.properties  base.url, browser, headless, wait timeout
  log4j2.xml         logging config
  junit-platform.properties   Cucumber plugin/glue + parallel-execution config
```

## Coverage

| Feature file | Area |
|---|---|
| login | presentation - authentication |
| inventory | product listing |
| add_to_cart | cart |
| cart_management | cart |
| sort_products | product listing |
| checkout | checkout happy path |
| checkout_validation | checkout - required-field errors |

## Tags

`@smoke` fast subset, `@regression` full functional coverage, `@negative`
error-path scenarios.

## Notes on the target application

Swag Labs (saucedemo.com) is a public sandbox built for Selenium practice.
It ships several accounts with intentionally broken behavior
(`locked_out_user`, `problem_user`, `performance_glitch_user`,
`error_user`, `visual_user`) alongside the well-behaved `standard_user` this
suite uses; the shared password for all of them is `secret_sauce`.

Scenarios run in parallel across feature files (`junit-platform.properties`)
- `DriverFactory` hands each test thread its own `WebDriver` via
`ThreadLocal` for exactly that reason.
