package com.labcorp.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.labcorp.steps", "com.labcorp.hooks"},
        plugin = {
                "pretty",
        },
        tags = "@get-request or @post-request",
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}