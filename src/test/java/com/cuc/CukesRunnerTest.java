package com.cuc;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
/**
 * Created by Simon on 16/11/2016.
 */

@CucumberOptions (features =
        "src/test/java/features/")
@RunWith(Cucumber.class)
public class CukesRunnerTest {
}