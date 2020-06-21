package managers;

import org.openqa.selenium.WebDriver;
import pageObject.Moistutizer;
import pageObject.Sunscreen;

public class PageObjectManager {

    private WebDriver driver;
    private Moistutizer MoistutizerPage;
    private Sunscreen SunscreenPage;

    public PageObjectManager(WebDriver driver) {

        this.driver = driver;

    }

    public Moistutizer getMoistutizerPage(){ return (MoistutizerPage == null) ? MoistutizerPage = new Moistutizer(driver) : MoistutizerPage;
    }

    public Sunscreen getSunscreenPage(){ return (SunscreenPage == null) ? SunscreenPage = new Sunscreen(driver) : SunscreenPage;
    }
}



