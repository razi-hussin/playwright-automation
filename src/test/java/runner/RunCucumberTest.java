package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import utils.ConfigLoader;

import java.util.Collections;

@CucumberOptions(
        features = "src/Test/resources/features",
        glue = {"step_definitions"},
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {

        public static void main (String[]args){
            XmlSuite suite = new XmlSuite();
            suite.setName("Cucumber Suite");
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

}
