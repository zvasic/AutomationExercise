package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class TestCase20_SearchProductsandVerifyCartAfterLogin extends BaseTest {

    @BeforeMethod
    public void setUpPage() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        homePage = new HomePage();
        topMenuPage = new TopMenuPage();
        productsPage = new ProductsPage();
        cartPage = new CartPage();
        loginSignupPage = new LoginSignupPage();
    }

    @Test
    public void SearchProductsandVerifyCartAfterLogin() throws IOException {
        excelReader = new ExcelReader("data\\LoginData.xlsx");

        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        topMenuPage.clickOnMenuItem("Products");

        // (Step 4)
        Assert.assertEquals(productsPage.pageTitle.getText().trim().toLowerCase(), "ALL PRODUCTS".toLowerCase());
        Assert.assertFalse(productsPage.listOfProducts.isEmpty());

        // (Step 5)
        productsPage.searchFieldInput("shirt");
        productsPage.clickOnSearchButton();

        // (Step 6)
        Assert.assertEquals(productsPage.pageTitle.getText().trim().toLowerCase(), "Searched Products".toLowerCase());

        // (Step 7)
        Assert.assertFalse(productsPage.listOfProducts.isEmpty());
        Assert.assertTrue(productsPage.listOfProducts.get(0).isDisplayed());

        // (Step 8)
        productsPage.addAllProductToCart();

        // (Step 9)
        Assert.assertFalse(cartPage.cartIsEmpty());

        // (Step 10)
        topMenuPage.clickOnMenuItem("Signup / Login");
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login");

        String validUsername = excelReader.getStringData("LoginData", 1, 0);
        String validEmail = excelReader.getStringData("LoginData", 1, 1);
        String validPassword = excelReader.getStringData("LoginData", 1, 2);

        loginSignupPage.loginUser(validEmail, validPassword);
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        Assert.assertTrue(topMenuPage.loggedInMsg.isDisplayed());
        Assert.assertEquals(topMenuPage.loggedInMsg.getText().trim(), "Logged in as " + validUsername);

        // (Step 11)
        topMenuPage.clickOnMenuItem("Cart");

        // (Step 12)
        Assert.assertFalse(cartPage.cartIsEmpty());
    }
}