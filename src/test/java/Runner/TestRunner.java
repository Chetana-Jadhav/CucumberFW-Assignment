package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features",
        glue = "StepDefs",
        tags = "@Billpay",
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html"
        },
        dryRun = false
                )

    public class TestRunner extends AbstractTestNGCucumberTests {
}

