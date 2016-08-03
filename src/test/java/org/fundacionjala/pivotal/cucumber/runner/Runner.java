package org.fundacionjala.pivotal.cucumber.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

/**
 * This class store settings for the test execution
 *
 * @author Henrry Salinas.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        format = {"pretty",
                "html:target/test-report",
                "json:target/test-report.json",
                "junit:target/test-report.xml"},
        features = {"src/test/resources/"},
        glue = {"org.fundacionjala.pivotal.cucumber"},
        snippets = SnippetType.CAMELCASE
)
public class Runner {

}

