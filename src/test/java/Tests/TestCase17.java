package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.TopMenuPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase17 extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        topMenuPage = new TopMenuPage();
        homePage = new HomePage();
        cartPage = new CartPage();
    }

    @Test
    public void RemoveProductsFromCart(){
        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // Scroll to first element in list to avoid 'element not clickable' problem
        scrollToELement(homePage.listOfAddToCartButtons.get(0));

        // (Step 4 -5)
        String product1 = "1";
        String product2 = "11";
        String[] productId = {product1, product2};
        homePage.addMultipleProductToCart(productId);

        // (Step 6)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart");
        // Verify cart is empty (if empty = true)
        Assert.assertFalse(cartPage.cartIsEmpty());

        // (Step 7 - 8)
        cartPage.deleteItemFromCart(product2);
    }
}