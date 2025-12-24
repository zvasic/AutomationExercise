package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignupPage extends BaseTest {

    public SignupPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.login-form > h2.title")
    public WebElement signupPageTitle;

    @FindBy(css = "label[class='top']")
    public List<WebElement> titleList;

    @FindBy(id = "name")
    public WebElement nameField;

    @FindBy(id = "email")
    public WebElement emailField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "days")
    public WebElement daysSelectDropdown;

    @FindBy(id = "months")
    public WebElement monthsSelectDropdown;

    @FindBy(id = "years")
    public WebElement yearsSelectDropdown;

    @FindBy(css = "label[for='newsletter']")
    public WebElement newsletterCheckbox;

    @FindBy(css = "label[for='optin']")
    public WebElement optinCheckbox;

    @FindBy(id = "first_name")
    public WebElement firstnameField;

    @FindBy(id = "last_name")
    public WebElement lastnameField;

    @FindBy(id = "company")
    public WebElement companyField;

    @FindBy(id = "address1")
    public WebElement address1Field;

    @FindBy(id = "address2")
    public WebElement address2Field;

    @FindBy(id = "country")
    public WebElement countryDropdown;

    @FindBy(id = "state")
    public WebElement stateField;

    @FindBy(id = "city")
    public WebElement cityField;

    @FindBy(id = "zipcode")
    public WebElement zipCodeField;

    @FindBy(id = "mobile_number")
    public WebElement mobileNumberField;

    @FindBy(css = "button[data-qa='create-account']")
    public WebElement submitButton;

    // ----------------------

    public void clickOnTitle(String title) {
        for (WebElement e : titleList) {
            if (e.getText().trim().equals(title)) {
                e.click();
                break;
            }
        }
    }

    public void passwordFieldInput(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void setBirthday(String day, String month, String year) {
        daysSelectDropdown.sendKeys(day);
        monthsSelectDropdown.sendKeys(month);
        yearsSelectDropdown.sendKeys(year);
    }

    public void clickOnNewsletterCheckbox() {
        newsletterCheckbox.click();
    }

    public void clickOnOptinCheckbox() {
        optinCheckbox.click();
    }

    public void firstnameFieldInput(String firstname) {
        firstnameField.clear();
        firstnameField.sendKeys(firstname);
    }

    public void lastnameFieldInput(String lastname) {
        lastnameField.clear();
        lastnameField.sendKeys(lastname);
    }

    public void companyFieldInput(String company) {
        companyField.clear();
        companyField.sendKeys(company);
    }

    public void address1FieldInput(String address1) {
        address1Field.clear();
        address1Field.sendKeys(address1);
    }

    public void address2FieldInput(String address2) {
        address2Field.clear();
        address2Field.sendKeys(address2);
    }

    public void setCountry(String country) {
        countryDropdown.sendKeys(country);
    }

    public void stateFieldInput(String state){
        stateField.clear();
        stateField.sendKeys(state);
    }

    public void cityFieldInput(String city){
        cityField.clear();
        cityField.sendKeys(city);
    }

    public void zipCodeFieldInput(String zipcode){
        zipCodeField.clear();
        zipCodeField.sendKeys(zipcode);
    }

    public void mobileNumberFiledInput(String mobile){
        mobileNumberField.clear();
        mobileNumberField.sendKeys(mobile);
    }

    public void clickOnSubmitButton() {
        submitButton.click();
    }
}
