package Tests;

import Base.BaseTest;
import Pages.ProductDetailPage;
import Pages.ProductsPage;
import Pages.TopMenuPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase08 extends BaseTest {

    @BeforeMethod
    public void setUpPage() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        topMenuPage = new TopMenuPage();
        productsPage = new ProductsPage();
        productDetailPage = new ProductDetailPage();
    }

    @Test
    public void VerifyAllProductsAndProductDetailPage(){
        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // (Step 4)
        topMenuPage.clickOnMenuItem("Products");

        // (Step 5)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products");
        Assert.assertTrue(productsPage.pageTitle.isDisplayed());
        Assert.assertEquals(productsPage.pageTitle.getText().trim().toLowerCase(), "All Products".toLowerCase());

        // (Step 6)
        Assert.assertFalse(productsPage.listOfProducts.isEmpty());

        /*
        // Add to cart element with specific id (Step 7)
        String productId = "1";
        productsPage.clickOnViewProductButton(productId);

        // (Step 8)
        Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/product_details" + productId);
        */

        // Add to cart first element (Step 7)
        scrollToELement(productsPage.listOfViewProductButtons.get(0));
        productsPage.clickOnFirstProductViewButton();

        // (Step 8)
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://automationexercise.com/product_details"));

        // (Step 9)
        Assert.assertTrue(productDetailPage.productName.isDisplayed());
        Assert.assertTrue(productDetailPage.productCategory.isDisplayed());
        Assert.assertTrue(productDetailPage.productPrice.isDisplayed());
        Assert.assertTrue(productDetailPage.productAvailability.isDisplayed());
        Assert.assertTrue(productDetailPage.productCondition.isDisplayed());
        Assert.assertTrue(productDetailPage.productBrand.isDisplayed());
    }
}