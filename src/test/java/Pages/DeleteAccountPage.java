package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteAccountPage extends BaseTest {

    public DeleteAccountPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h2[data-qa='account-deleted']")
    public WebElement deleteAccountPageTitle;

    @FindBy(css = "a[data-qa='continue-button']")
    public WebElement deleteAccountPageContinueButton;

    // -----------------------------------------

    public void clickOnDeleteAccountPageContinueButton() {
        deleteAccountPageContinueButton.click();
    }
}
