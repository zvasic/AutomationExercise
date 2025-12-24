package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.ProductsPage;
import Pages.TopMenuPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCase12 extends BaseTest {

    @BeforeMethod
    public void setUpPage() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        topMenuPage = new TopMenuPage();
        productsPage = new ProductsPage();
        cartPage = new CartPage();
    }

    @Test
    public void AddProductsinCart() {
        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);
        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // (Step 4)
        topMenuPage.clickOnMenuItem("Products");

        // Avoid element is not clickable at the moment
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", productsPage.pageTitle);

        // (Step 5 - 8)
        String[] productId = {"1", "2"};
        productsPage.addMultipleProductToCart(productId);

        // (Step 9)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart");
        // Verify cart is empty (if empty = true)
        Assert.assertFalse(cartPage.cartIsEmpty());

        // (Step 10)
        for (String pId : productId) {
            Assert.assertTrue(cartPage.isItemInCart(pId));
            Assert.assertTrue(cartPage.isItemDeleteInCart(pId));

            // Varify that item price * quantity is equals to total item price
            Assert.assertTrue(cartPage.checkItemDataInCart(pId));
        }
    }
}