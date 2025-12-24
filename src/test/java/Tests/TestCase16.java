package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;


public class TestCase16 extends BaseTest {

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
    public void PlaceOrderLoginBeforeCheckout() throws IOException {
        excelReader = new ExcelReader("data\\LoginData.xlsx");

        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // (Step 4)
        topMenuPage.clickOnMenuItem("Signup / Login");
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login");

        // (Step 5)
        String validUsername = excelReader.getStringData("LoginData", 1, 0);
        String validEmail = excelReader.getStringData("LoginData", 1, 1);
        String validPassword = excelReader.getStringData("LoginData", 1, 2);

        loginSignupPage.loginUser(validEmail, validPassword);
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        Assert.assertTrue(topMenuPage.loggedInMsg.isDisplayed());
        Assert.assertEquals(topMenuPage.loggedInMsg.getText().trim(), "Logged in as " + validUsername);

        // (Step 7 - 8)
        String[] products = {"1", "2"};
        homePage.addMultipleProductToCart(products);

        // (Step 9)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart");
        // Verify cart is empty (if empty = true)
        Assert.assertFalse(cartPage.cartIsEmpty());
        Assert.assertTrue(cartPage.checkoutButton.isDisplayed());

        // (Step 10)
        cartPage.clickOnCheckoutButton();

        // (Step 11)
        checkoutPage.checkPersonalInfo();

        // (Step 12)
        checkoutPage.orderCommentFieldInput("Some comment");
        checkoutPage.clickOnPlaceOrderButton();

        // (Step 13 - 14)

        paymentPage.submitOrder("Ryan Mitchell", "530438968533688011", "010", "12", "2030");

        // (Step 15)
        Assert.assertTrue(paymentDonePage.infoMessage.isDisplayed());
        Assert.assertEquals(paymentDonePage.infoMessage.getText().trim(),
                "Congratulations! Your order has been confirmed!"
        );
        Assert.assertTrue(paymentDonePage.continueButton.isDisplayed());

        paymentDonePage.clickOnContinueButton();

        // (Step 16 - 17 IGNORED)
    }
}
