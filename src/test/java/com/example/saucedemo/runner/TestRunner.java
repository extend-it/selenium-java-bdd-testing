package com.example.saucedemo.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * The JUnit 5 Suite entry point (@IncludeEngines("cucumber")).
 *
 * Known limitation: @Suite runs its included engines (cucumber here)
 * through its own embedded Launcher session, separate from the outer
 * Launcher session Maven Surefire listens to. Every scenario/step genuinely
 * executes, but those execution events never reach Surefire's
 * TestExecutionListener - so target/surefire-reports/*.xml (and the HTML
 * report generated from it) will always show "Tests run: 0" for
 * TestRunner, no matter how many scenarios actually ran. That's not a
 * misconfiguration; it's confirmed straight from Surefire's own console
 * summary. Surefire's report is still useful as a build-level pass/fail
 * signal (a scenario failure still fails the build and the run), just not
 * as a source of per-scenario detail.
 *
 * For real per-scenario/step detail, see the "html" plugin below:
 * target/cucumber-reports/report.html is generated straight from
 * Cucumber's own event bus, independently of Surefire/JUnit Platform
 * listener plumbing, so it always reflects what actually ran. The "junit"
 * plugin writes the same events as JUnit XML (one testcase per scenario)
 * to target/cucumber-reports/cucumber.xml, for CI test-result views.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.saucedemo.stepdefinitions")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "pretty, summary, html:target/cucumber-reports/report.html, "
                + "junit:target/cucumber-reports/cucumber.xml")
public class TestRunner {
}
