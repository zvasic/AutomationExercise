package Pages;

import Base.BaseTest;
import Base.ExcelReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

public class LoginSignupPage extends BaseTest {

    public LoginSignupPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.signup-form > h2")
    public WebElement signupFormTitle;

    @FindBy(css = "div.login-form > h2")
    public WebElement loginFormTitle;

    @FindBy(css = "input[data-qa='signup-name']")
    public WebElement signupNameField;

    @FindBy(css = "input[data-qa='signup-email']")
    public WebElement signupEmailField;

    @FindBy(css = "button[data-qa='signup-button']")
    public WebElement signupButton;

    @FindBy(css = "input[data-qa='login-email']")
    public WebElement loginEmailField;

    @FindBy(css = "input[data-qa='login-password']")
    public WebElement loginPasswordField;

    @FindBy(css = "button[data-qa='login-button']")
    public WebElement loginButton;

    @FindBy(css = "p[style='color: red;']")
    public WebElement loginSignupMessage;

    // ----------------------------

    public void signupNameFieldInput(String name){
        signupNameField.clear();
        signupNameField.sendKeys(name);
    }

    public void signupEmailFieldInput(String email){
        signupEmailField.clear();
        signupEmailField.sendKeys(email);
    }

    public void clickOnSignupButton(){
        signupButton.click();
    }

    public void loginEmailFieldInput(String email){
        loginEmailField.clear();
        loginEmailField.sendKeys(email);
    }

    public void loginPasswordFieldInput(String password){
        loginPasswordField.clear();
        loginPasswordField.sendKeys(password);
    }

    public void clickOnLoginButton(){
        loginButton.click();
    }

    public void loginUser(String validEmail, String validPassword){
        loginEmailFieldInput(validEmail);
        loginPasswordFieldInput(validPassword);
        clickOnLoginButton();
    }

    public void registerUser() throws IOException {
        excelReader = new ExcelReader("data\\LoginData.xlsx");
        String validName = excelReader.getStringData("AddNew", 1, 0);
        String validEmail = excelReader.getStringData("AddNew", 1, 1);
        signupNameFieldInput(validName);
        signupEmailFieldInput(validEmail);

        // (Step 7)
        loginSignupPage.clickOnSignupButton();

        // (Step 8)
        Assert.assertTrue(signupPage.signupPageTitle.isDisplayed());
        Assert.assertEquals(signupPage.signupPageTitle.getText().trim().toLowerCase(),
                "Enter Account Information".toLowerCase());
        Assert.assertEquals(signupPage.nameField.getAttribute("value"), validName);
        Assert.assertEquals(signupPage.emailField.getAttribute("value"), validEmail);
        String title = excelReader.getStringData("AddNew", 1, 2);
        String password = excelReader.getStringData("AddNew", 1, 3);
        String firstName = excelReader.getStringData("AddNew", 1, 4);
        String lastName = excelReader.getStringData("AddNew", 1, 5);
        String company = excelReader.getStringData("AddNew", 1, 6);
        String address1 = excelReader.getStringData("AddNew", 1, 7);
        String address2 = excelReader.getStringData("AddNew", 1, 8);
        String country = excelReader.getStringData("AddNew", 1, 9);
        String state = excelReader.getStringData("AddNew", 1, 10);
        String city = excelReader.getStringData("AddNew", 1, 11);
        String zipcode = String.valueOf(excelReader.getIntegerData("AddNew", 1, 12));
        String mobile = excelReader.getStringData("AddNew", 1, 13);

        signupPage.clickOnTitle(title);
        signupPage.passwordFieldInput(password);
        signupPage.setBirthday("1", "April", "1975");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", signupPage.newsletterCheckbox);
        signupPage.clickOnNewsletterCheckbox();
        signupPage.clickOnOptinCheckbox();
        signupPage.firstnameFieldInput(firstName);
        signupPage.lastnameFieldInput(lastName);
        signupPage.companyFieldInput(company);
        signupPage.address1FieldInput(address1);
        signupPage.address2FieldInput(address2);
        signupPage.setCountry(country);
        signupPage.stateFieldInput(state);
        signupPage.cityFieldInput(city);
        signupPage.zipCodeFieldInput(zipcode);
        signupPage.mobileNumberFiledInput(mobile);

        // (Step 13)
        js.executeScript("arguments[0].scrollIntoView(true);", signupPage.submitButton);
        signupPage.clickOnSubmitButton();
    }
}
