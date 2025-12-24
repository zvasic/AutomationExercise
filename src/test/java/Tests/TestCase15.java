package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;


public class TestCase15 extends BaseTest {

    @BeforeMethod
    public void setUpPage() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        topMenuPage = new TopMenuPage();
        homePage = new HomePage();
        cartPage = new CartPage();
        loginSignupPage = new LoginSignupPage();
        signupPage = new SignupPage();
        accountPage = new AccountPage();
        checkoutPage = new CheckoutPage();
        paymentPage = new PaymentPage();
        paymentDonePage = new PaymentDonePage();
        deleteAccountPage = new DeleteAccountPage();
    }

    @Test
    public void PlaceOrderRegisterBeforeCheckout() throws IOException {
        excelReader = new ExcelReader("data\\LoginData.xlsx");

        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // (Step 4)
        topMenuPage.clickOnMenuItem("Signup / Login");
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login");
        Assert.assertTrue(loginSignupPage.signupFormTitle.isDisplayed());
        Assert.assertEquals(loginSignupPage.signupFormTitle.getText(), "New User Signup!");

        // (Step 5)
        String validName = excelReader.getStringData("AddNew", 1, 0);
        String validEmail = excelReader.getStringData("AddNew", 1, 1);
        loginSignupPage.signupNameFieldInput(validName);
        loginSignupPage.signupEmailFieldInput(validEmail);
        loginSignupPage.clickOnSignupButton();

        Assert.assertTrue(signupPage.signupPageTitle.isDisplayed());
        Assert.assertEquals(signupPage.signupPageTitle.getText().trim().toLowerCase(),
                "Enter Account Information".toLowerCase());
        Assert.assertEquals(signupPage.nameField.getAttribute("value"), validName);
        Assert.assertEquals(signupPage.emailField.getAttribute("value"), validEmail);

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

        scrollToELement(signupPage.submitButton);

        signupPage.clickOnSubmitButton();

        // (Step 6)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/account_created");
        Assert.assertTrue(accountPage.accountCreateMsg.isDisplayed());
        Assert.assertTrue(accountPage.continueButton.isDisplayed());

        accountPage.clickOnContinueButton();

        // (Step 7)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        Assert.assertTrue(topMenuPage.loggedInMsg.isDisplayed());
        Assert.assertEquals(topMenuPage.loggedInMsg.getText().trim(), "Logged in as " + validName);

        // (Step 8 - 9)
        String[] products = {"1", "2"};
        homePage.addMultipleProductToCart(products);

        // (Step 10)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart");
        // Verify cart is empty (if empty = true)
        Assert.assertFalse(cartPage.cartIsEmpty());
        Assert.assertTrue(cartPage.checkoutButton.isDisplayed());

        // (Step 11)
        cartPage.clickOnCheckoutButton();

        // (Step 12)
        checkoutPage.checkPersonalInfo();

        // (Step 13)
        checkoutPage.orderCommentFieldInput("Some comment");
        checkoutPage.clickOnPlaceOrderButton();

        // (Step 14 - 15)
        paymentPage.submitOrder("Ryan Mitchell", "530438968533688011", "010", "12", "2030");

        // (Step 16)
        Assert.assertTrue(paymentDonePage.infoMessage.isDisplayed());
        Assert.assertEquals(paymentDonePage.infoMessage.getText().trim(),
                "Congratulations! Your order has been confirmed!"
        );
        Assert.assertTrue(paymentDonePage.continueButton.isDisplayed());

        paymentDonePage.clickOnContinueButton();

        // (Step 17)
        topMenuPage.clickOnMenuItem("Delete Account");

        // (Step 18)
        Assert.assertTrue(deleteAccountPage.deleteAccountPageTitle.isDisplayed());
        Assert.assertEquals(deleteAccountPage.deleteAccountPageTitle.getText().toLowerCase(), "Account Deleted!".toLowerCase());
        deleteAccountPage.clickOnDeleteAccountPageContinueButton();
    }
}