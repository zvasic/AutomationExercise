package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentDonePage extends BaseTest {

    public PaymentDonePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h2[data-qa='order-placed']")
    public WebElement infoTitle;

    @FindBy(css = "div.col-sm-9 > p")
    public WebElement infoMessage;

    @FindBy(css = "a.check_out")
    public WebElement downloadInvoiceButton;

    @FindBy(css = "a[data-qa='continue-button']")
    public WebElement continueButton;

    // ------------------------------

    public void clickOnContinueButton(){
        continueButton.click();
    }

}
