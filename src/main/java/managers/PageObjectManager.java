package managers;

import org.openqa.selenium.WebDriver;
import pageObject.tasks;

public class PageObjectManager {

    private WebDriver driver;
    private tasks MoistutizerPage;


    public PageObjectManager(WebDriver driver) {

        this.driver = driver;

    }
    public tasks getMoistutizerPage(){ return (MoistutizerPage == null) ? MoistutizerPage = new tasks(driver) : MoistutizerPage;
    }
}



