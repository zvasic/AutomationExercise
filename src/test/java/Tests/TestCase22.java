package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase22 extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        // (Step 1))
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homePage = new HomePage();
        cartPage = new CartPage();

        // (Step 2)
        driver.navigate().to("https://automationexercise.com/");
    }

    @Test
    public void addItemFromRecommended() {
        // Scroll to recomended section (Step 3)

        scrollToELement(homePage.recommendedItemsTitle);

        // Verify 'RECOMMENDED ITEMS' are visible (Step 4)
        Assert.assertTrue(homePage.recommendedItemsTitle.isDisplayed());

        // Return id of element which is added from recommended
        String productId = homePage.pickRecommendedItemAddToCart();
        // Add item in cart with above returned id (Step 5)
        homePage.clickOnRecommendedItemAddToCart(productId);
        // Click on View cart from modal window (Step 6)
        homePage.clickOnCartButtonModal();

        // Verify cart is empty (if empty = true) (Step 7)
        Assert.assertFalse(cartPage.cartIsEmpty());
        // Verify that id of added element is an element in cart with same id (Step 7)
        Assert.assertTrue(cartPage.isItemInCart(productId));
    }
}