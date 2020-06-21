package stepDefinations;

import cucumber.TestContext;
import cucumber.api.java.en.*;

public class tasks {

    TestContext testContext;
    pageObject.tasks mo;
    public tasks(TestContext context) {
        testContext = context;
        mo = testContext.getPageObjectManager().getMoistutizerPage();
    }

    @Given("^user navigates to application$")
    public void user_navigates_to_application()  {
        mo.openURL();
    }

    @Then("^verify the task to check temperature$")
    public void verifyTheTaskToCheckTemperature() {
        mo.checktemp();
    }

    @And("^verify the task$")
    public void verify_the_task()  {
        mo.Checkout();
    }

    @Then("^Pay With Card$")
    public void payWithCard() {
        mo.pay();
    }

    @And("^Enter the zipcode \"([^\"]*)\"$")
    public void enterTheZipcode(String arg0)  {
        mo.zip(arg0);
    }

    @Then("^Enter email \"([^\"]*)\"$")
    public void enterEmail(String arg0)  {
        mo.enterEmail(arg0);
    }

    @And("^Enter your Card Number \"([^\"]*)\"$")
    public void enterYourCardNumber(String arg0)  {
        mo.cardDetails(arg0);
    }

    @And("^Enter Expire Date and Year \"([^\"]*)\"$")
    public void enterExpireDateAndYear(String arg0)  {
       mo.date(arg0);
    }

    @And("^Enter card cvv \"([^\"]*)\"$")
    public void enterCardCvv(String arg0)  {
       mo.cvvNum(arg0);
    }

    @Then("^pay the bill$")
    public void payTheBill() {
        mo.paybill();
    }

    @And("^verify payment is successfully done$")
    public void verifyPaymentIsSuccessfullyDone() {
        mo.done();
    }
}
