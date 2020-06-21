package runner;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import managers.FileReader;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = ".//src//test//java//featurefile",glue = "stepDefinations",
        plugin = { "com.cucumber.listener.ExtentCucumberFormatter:",
                "junit:target/cucumber-results.xml"},
        tags="@Smoke",
        monochrome = true
)
public class Runner {

    @BeforeClass
    public static void setup() {
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("target/cucumber-reports//report.html");
        PropertyConfigurator.configure(FileReader.getInstance().getConfigReader().getlog4jpath());
    }

    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(FileReader.getInstance().getConfigReader().getReportConfigPath());
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        Reporter.setSystemInfo("Environment",FileReader.getInstance().getConfigReader().getEnvironmentReps());
        Reporter.setSystemInfo("Browser", String.valueOf(FileReader.getInstance().getConfigReader().getBrowser()));
    }
}