package com.labcorp.hooks;

import com.labcorp.utils.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHooks {

    private static final Logger logger = LoggerFactory.getLogger(TestHooks.class);
    private TestContext testContext;

    public TestHooks() {
        this.testContext = TestContext.getInstance();
    }

    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: {}", scenario.getName());
        testContext.reset();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Scenario failed: {}", scenario.getName());
            // Add screenshot or additional logging if needed
        } else {
            logger.info("Scenario passed: {}", scenario.getName());
        }

        testContext.reset();
    }
}