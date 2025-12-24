package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends BaseTest {

    public AccountPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h2[data-qa='account-created']")
    public WebElement accountCreateMsg;

    @FindBy(css = "a[data-qa='continue-button']")
    public WebElement continueButton;

    // ------------------------

    public void clickOnContinueButton(){
        continueButton.click();
    }
}