package stepDefinations;

import cucumber.TestContext;
import pageObject.Sunscreen;

public class Sunscreens {

    TestContext testContext;
    Sunscreen Sun;
    public Sunscreens(TestContext context) {
        testContext = context;
        Sun = testContext.getPageObjectManager().getSunscreenPage();
    }

}
