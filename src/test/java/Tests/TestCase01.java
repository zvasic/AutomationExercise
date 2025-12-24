package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase01 extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        topMenuPage = new TopMenuPage();
        loginSignupPage = new LoginSignupPage();
        signupPage = new SignupPage();
        accountPage = new AccountPage();
        deleteAccountPage = new DeleteAccountPage();
    }

    @Test
    public void RegisterUser() {
        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // (Step 4)
        topMenuPage.clickOnMenuItem("Signup / Login");

        // (Step 5)
        Assert.assertTrue(loginSignupPage.signupFormTitle.isDisplayed());
        Assert.assertEquals(loginSignupPage.signupFormTitle.getText(), "New User Signup!");

        // (Step 6)
        String validName = "ExampleUser";
        String validEmail = "exampleUser+2@example.com";
        loginSignupPage.signupNameFieldInput(validName);
        loginSignupPage.signupEmailFieldInput(validEmail);

        // (Step 7)
        loginSignupPage.clickOnSignupButton();

        // (Step 8)
        Assert.assertTrue(signupPage.signupPageTitle.isDisplayed());
        Assert.assertEquals(signupPage.signupPageTitle.getText().trim().toLowerCase(),
                "Enter Account Information".toLowerCase());
        Assert.assertEquals(signupPage.nameField.getAttribute("value"), validName);
        Assert.assertEquals(signupPage.emailField.getAttribute("value"), validEmail);

        // (Step 9 - 12)
        String title = "Mr.";
        String password = "somePassword";
        String birthday = "1975-04-01";
        String firstName = "John";
        String lastName = "Doe";
        String company = "Acme Co.";
        String address1 = "Main Str. 1";
        String address2 = "Main Str. 2";
        String country = "Canada";
        String state = "Quebeck";
        String city = "Ontario";
        String zipcode = "10000";
        String mobile = "0501234567";

        signupPage.clickOnTitle(title);
        signupPage.passwordFieldInput(password);
        String[] bday = birthday.split("-");
        String day = bday[2];
        String month = bday[1];
        String year = bday[0];
        signupPage.setBirthday(day, month, year);

        scrollToELement(signupPage.newsletterCheckbox);

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
        scrollToELement(signupPage.submitButton);
        signupPage.clickOnSubmitButton();

        // (Step 14)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/account_created");
        Assert.assertTrue(accountPage.accountCreateMsg.isDisplayed());
        Assert.assertTrue(accountPage.continueButton.isDisplayed());

        // (Step 15)
        accountPage.clickOnContinueButton();

        // (Step 16)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        Assert.assertTrue(topMenuPage.loggedInMsg.isDisplayed());
        Assert.assertEquals(topMenuPage.loggedInMsg.getText().trim(), "Logged in as " + validName);

        // (Step 17)
        topMenuPage.clickOnMenuItem("Delete Account");

        // (Step 18)
        Assert.assertTrue(deleteAccountPage.deleteAccountPageTitle.isDisplayed());
        Assert.assertEquals(deleteAccountPage.deleteAccountPageTitle.getText().toLowerCase(), "Account Deleted!".toLowerCase());
        deleteAccountPage.clickOnDeleteAccountPageContinueButton();
    }

    @Test
    public void RegisterUserExcel() throws IOException {
        excelReader = new ExcelReader("data\\LoginData.xlsx");

        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // (Step 4)
        topMenuPage.clickOnMenuItem("Signup / Login");

        // (Step 5)
        Assert.assertTrue(loginSignupPage.signupFormTitle.isDisplayed());
        Assert.assertEquals(loginSignupPage.signupFormTitle.getText(), "New User Signup!");

        // (Step 6)
        String validName = excelReader.getStringData("AddNew", 2, 0);
        String validEmail = excelReader.getStringData("AddNew", 2, 1);
        loginSignupPage.signupNameFieldInput(validName);
        loginSignupPage.signupEmailFieldInput(validEmail);

        // (Step 7)
        loginSignupPage.clickOnSignupButton();

        // (Step 8)
        Assert.assertTrue(signupPage.signupPageTitle.isDisplayed());
        Assert.assertEquals(signupPage.signupPageTitle.getText().trim().toLowerCase(),
                "Enter Account Information".toLowerCase());
        Assert.assertEquals(signupPage.nameField.getAttribute("value"), validName);
        Assert.assertEquals(signupPage.emailField.getAttribute("value"), validEmail);

        // (Step 9 - 12)
        String title = excelReader.getStringData("AddNew", 1, 2);
        String password = excelReader.getStringData("AddNew", 1, 3);
        String birthday = excelReader.getStringData("AddNew", 1, 4);
        String firstName = excelReader.getStringData("AddNew", 1, 5);
        String lastName = excelReader.getStringData("AddNew", 1, 6);
        String company = excelReader.getStringData("AddNew", 1, 7);
        String address1 = excelReader.getStringData("AddNew", 1, 8);
        String address2 = excelReader.getStringData("AddNew", 1, 9);
        String country = excelReader.getStringData("AddNew", 1, 10);
        String state = excelReader.getStringData("AddNew", 1, 11);
        String city = excelReader.getStringData("AddNew", 1, 12);
        String zipcode = String.valueOf(excelReader.getIntegerData("AddNew", 1, 13));
        String mobile = excelReader.getStringData("AddNew", 1, 14);

        signupPage.clickOnTitle(title);
        signupPage.passwordFieldInput(password);
        String[] bday = birthday.split("-");
        String day = bday[2];
        String month = bday[1];
        String year = bday[0];
        signupPage.setBirthday(day, month, year);

        scrollToELement(signupPage.newsletterCheckbox);

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
        scrollToELement(signupPage.submitButton);

        signupPage.clickOnSubmitButton();

        // (Step 14)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/account_created");
        Assert.assertTrue(accountPage.accountCreateMsg.isDisplayed());
        Assert.assertTrue(accountPage.continueButton.isDisplayed());

        // (Step 15)
        accountPage.clickOnContinueButton();

        // (Step 16)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        Assert.assertTrue(topMenuPage.loggedInMsg.isDisplayed());
        Assert.assertEquals(topMenuPage.loggedInMsg.getText().trim(), "Logged in as " + validName);

        // (Step 17)
        topMenuPage.clickOnMenuItem("Delete Account");

        // (Step 18)
        Assert.assertTrue(deleteAccountPage.deleteAccountPageTitle.isDisplayed());
        Assert.assertEquals(deleteAccountPage.deleteAccountPageTitle.getText().toLowerCase(), "Account Deleted!".toLowerCase());
        deleteAccountPage.clickOnDeleteAccountPageContinueButton();
    }
}