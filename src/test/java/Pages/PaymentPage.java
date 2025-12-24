package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BaseTest {

    public PaymentPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[data-qa='name-on-card']")
    public WebElement cardNameField;

    @FindBy(css = "input[data-qa='card-number']")
    public WebElement cardNumberField;

    @FindBy(css = "input[data-qa='cvc']")
    public WebElement cardCvcField;

    @FindBy(css = "input[data-qa='expiry-month']")
    public WebElement cardExpiryMonthField;

    @FindBy(css = "input[data-qa='expiry-year']")
    public WebElement cardExpiryYearField;

    @FindBy(id= "submit")
    public WebElement submitPayOrderButton;

    @FindBy(id = "success_message")
    public WebElement successMessage;

    // ------------------------------------

    public void submitOrder(String name, String cardNumber, String cvc, String month, String year) {
        cardNameFieldInput(name);
        cardNumberFieldInput(cardNumber);
        cardCvcFieldInput(cvc);
        cardExpiryMonthFieldInput(month);
        cardExpiryYearFieldInput(year);

        scrollToELement(cardNameField);
        clickOnSubmitPayOrderButton();
    }

    public void cardNameFieldInput(String name){
        cardNameField.clear();
        cardNameField.sendKeys(name);
    }

    public void cardNumberFieldInput(String cardNumber){
        cardNumberField.clear();
        cardNumberField.sendKeys(cardNumber);
    }

    public void cardCvcFieldInput(String cvc){
        cardCvcField.clear();
        cardCvcField.sendKeys(cvc);
    }

    public void cardExpiryMonthFieldInput(String month){
        cardExpiryMonthField.clear();
        cardExpiryMonthField.sendKeys(month);
    }

    public void cardExpiryYearFieldInput(String year){
        cardExpiryYearField.clear();
        cardExpiryYearField.sendKeys(year);
    }

    public void clickOnSubmitPayOrderButton(){
        submitPayOrderButton.click();
    }

}
