package Tests;

import Base.BaseTest;
import Pages.ProductDetailPage;
import Pages.ProductsPage;
import Pages.TopMenuPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase21 extends BaseTest {

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
    public void AddReviewOnProduct(){
        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        topMenuPage.clickOnMenuItem("Products");

        // (Step 4)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products");
        Assert.assertTrue(productsPage.pageTitle.isDisplayed());
        Assert.assertEquals(productsPage.pageTitle.getText().trim().toLowerCase(), "All Products".toLowerCase());

        // (Step 5)
        Assert.assertFalse(productsPage.listOfProducts.isEmpty());

        scrollToELement(productsPage.listOfViewProductButtons.get(0));
        productsPage.clickOnFirstProductViewButton();

        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://automationexercise.com/product_details"));

        // (Step 6)
        Assert.assertTrue(productDetailPage.reviewTitle.isDisplayed());
        Assert.assertEquals(productDetailPage.reviewTitle.getText().trim().toLowerCase(), "Write Your Review".toLowerCase());

        // (Step 7)
        productDetailPage.reviewNameFieldInput("John");
        productDetailPage.reviewEmailFieldInput("exampleUser+1@example.com");
        productDetailPage.reviewTextFieldInput("Some review text");
        productDetailPage.clickOnReviewSubmitButton();

        Assert.assertTrue(productDetailPage.successMessage.isDisplayed());
        Assert.assertEquals(productDetailPage.successMessage.getText().trim(), "Thank you for your review.");
    }
}