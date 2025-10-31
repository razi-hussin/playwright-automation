package com.gbg.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/Test/resources/features",
        glue = {"com/gbg/step_definitions"},
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@instinct"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
/*
        public static void main (String[]args){
            XmlSuite suite = new XmlSuite();
            suite.setName("Cucumber Suite");

            suite.setParallel(XmlSuite.ParallelMode.NONE);
            suite.setDataProviderThreadCount(ConfigLoader.getInt("thread.count", 1));

            XmlTest test = new XmlTest(suite);
            test.setName("Cucumber Tests");
            test.setXmlClasses(Collections.singletonList(new XmlClass(RunCucumberTest.class)));

            TestNG testng = new TestNG();
            testng.setXmlSuites(Collections.singletonList(suite));
            testng.run();
        }

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios () {
            return super.scenarios();
        }
*/
}
