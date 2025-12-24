package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.ProductDetailPage;
import Pages.ProductsPage;
import Pages.TopMenuPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase13 extends BaseTest {

    @BeforeMethod
    public void setUpPage() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        topMenuPage = new TopMenuPage();
        productsPage = new ProductsPage();
        productDetailPage = new ProductDetailPage();
        cartPage = new CartPage();
    }

    @Test
    public void VerifyProductQuantityInCart(){
        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);
        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // (Step 4)
        String productId = "11";
        productsPage.clickOnViewProductButton(productId);
        // (Step 5)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/product_details/" + productId);
        Assert.assertTrue(productDetailPage.productName.isDisplayed());
        Assert.assertEquals(productDetailPage.quantityField.getAttribute("value"), "1");

        // (Step 6)
        String newQuantity = "4";
        productDetailPage.quantityFieldInput(newQuantity);
        // (Step 7)
        productDetailPage.clickOnAddToCartButton();
        // (Step 8)
        productDetailPage.clickOnViewCartButton();

        // Verify cart is empty (if empty = true) (Step 9)
        Assert.assertFalse(cartPage.cartIsEmpty());
        // Verify that id of added element is an element in cart with same id (Step 9)
        Assert.assertTrue(cartPage.isItemInCart(productId));
        // Verify that quantity of item added to cart is the same as in cart after addition (Step 9)
        Assert.assertTrue(cartPage.checkItemQuantityInCart(productId,newQuantity));
    }
}