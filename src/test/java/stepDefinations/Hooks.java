package stepDefinations;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import cucumber.TestContext;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Hooks {

    TestContext testContext;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        Reporter.assignAuthor("Sobhit Sharma");
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            //This takes a screenshot from the driver at save it to the specified location
            File sourcePath = ((TakesScreenshot) testContext.getWebDriverManager().getDriver()).getScreenshotAs(OutputType.FILE);

            //Building up the destination path for the screenshot to save
            //Also make sure to create a folder 'screenshots' with in the cucumber-report folder
            File destinationPath = new File("./target/cucumber-reports/"+screenshotName + ".png");

            //Copy taken screenshot from source location to destination location
            Files.copy(sourcePath, destinationPath);

            //This attach the specified screenshot to the test
            Reporter.addScreenCaptureFromPath(screenshotName+".png");
            System.out.println("Cant take screenshot in grid.");
        }
    }

    @After(order = 0)
    public void AfterSteps() {
        testContext.getWebDriverManager().closeDriver();
    }
}