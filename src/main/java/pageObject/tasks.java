package pageObject;

import managers.FileReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class tasks {
    WebDriver driver;
    Logger log = (Logger) Logger.getLogger(String.valueOf(tasks.class));

    public tasks(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="temperature")
    @CacheLookup
    WebElement temp;

    @FindBy(linkText = "Buy moisturizers")
    @CacheLookup
    WebElement moisProduct;

    @FindBy(linkText = "Buy sunscreens")
    @CacheLookup
    WebElement sunscreenProduct;

    @FindBy(id="cart")
    @CacheLookup
    WebElement cart;

    @FindBy(xpath="//button[@class='btn btn-primary']")
    @CacheLookup
    List<WebElement> itemsToAdd;

    @FindBy(xpath="//button[@type='submit']")
    @CacheLookup
    WebElement payWithCard;

    @FindBy(xpath="//input[@type='email']")
    @CacheLookup
    WebElement email;

    @FindBy(xpath="//input[@type='tel'][@placeholder='Card number']")
    @CacheLookup
    WebElement cardNumber;

    @FindBy(xpath="//input[@type='tel'][@placeholder='MM / YY']")
    @CacheLookup
    WebElement expireDate;

    @FindBy(xpath="//input[@type='tel'][@placeholder='CVC']")
    @CacheLookup
    WebElement cvv;

    @FindBy(xpath="//input[@type='tel'][@placeholder='ZIP Code']")
    @CacheLookup
    WebElement ZIPCode;

    @FindBy(xpath="//button[@class='Button-animationWrapper-child--primary Button']")
    @CacheLookup
    WebElement PayAmount;


    public void openURL() {
        log.info(FileReader.getInstance().getConfigReader().getApplicationUrl());
        driver.navigate().to(FileReader.getInstance().getConfigReader().getApplicationUrl());
    }
        public void checktemp() {
            if (temp.isDisplayed()) {
                log.info("temperatue is" + temp.getText());
                String tempVal = temp.getText().trim().replaceAll("[^0-9]", "");
                log.info(tempVal);
                if (Integer.parseInt(tempVal) > 34) {
                    log.info("Shop for suncreens if the weather is above 34 degrees.");
                    shopping("Sunscreens");
                } else if (Integer.parseInt(tempVal) < 19) {
                    log.info("Shop for moisturizers if the weather is below 19 degrees.");
                    shopping("Moisturizers");
                }
            } else {
                log.info("Element is not visiable");
            }
        }

    public void shopping(String item){
        if (item.equals("Sunscreens"))
        {
            log.info("Buying sunscreens");
            sunscreens();
        }
        else
        {
            log.info("Buying moisturizers");
            moisturizers();
        }
    }

    public void moisturizers(){
        moisProduct.click();
        if (cart.getText().contentEquals("Empty")){
            log.info("Cart is empty, adding item");
            ListIterator<WebElement> theListOfProfessors = itemsToAdd.listIterator();
            while(theListOfProfessors.hasNext()) {
                WebElement elem = theListOfProfessors.next();
                elem.click();
            }
        }
        else {
            log.info("Cart is not empty");
        }
    }

    public void sunscreens() {
        sunscreenProduct.click();
        if (cart.getText().contentEquals("Empty")) {
            ListIterator<WebElement> theListOfProfessors = itemsToAdd.listIterator();
            while (theListOfProfessors.hasNext()) {
                WebElement elem = theListOfProfessors.next();
                elem.click();
            }
        }
    }

    public void Checkout(){
        cart.click();
    }

    public void pay(){
        payWithCard.click();
    }

    public void enterEmail(String EML){
        // find all your iframes
        List<WebElement> iframes = driver.findElements(By.xpath("//iframe"));
        // print your number of frames
        log.info(iframes.size());

        // you can reach each frame on your site
        for (WebElement iframe : iframes) {

            // switch to every frame
            driver.switchTo().frame(iframe);
        }
        email.sendKeys(EML);
    }

    public void cardDetails(String num){
        cardNumber.sendKeys(num);
    }

    public void date(String year){
        expireDate.sendKeys(year);
    }

    public void cvvNum(String CVVnum){
        cvv.sendKeys(CVVnum);
    }

    public void zip(String ZipCode){
        ZIPCode.sendKeys(ZipCode);
    }

    public void paybill(){
        PayAmount.click();
    }

    public void done(){
        driver.manage().timeouts().implicitlyWait(FileReader.getInstance().getConfigReader().getImplicitlyWait(),TimeUnit.MILLISECONDS);
        String payment = driver.getCurrentUrl();
        if (payment.contentEquals("confirmation"))
        {
            log.info("Payment has been done.");
        }
    }
}