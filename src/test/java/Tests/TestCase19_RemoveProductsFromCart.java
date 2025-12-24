package Tests;

import Base.BaseTest;
import Pages.BrandProductsPage;
import Pages.HomePage;
import Pages.SideBarPage;
import Pages.TopMenuPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCase19_RemoveProductsFromCart extends BaseTest {

    @BeforeMethod
    public void setUpPage() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        homePage = new HomePage();
        topMenuPage = new TopMenuPage();
        sideBarPage = new SideBarPage();
        brandProductsPage = new BrandProductsPage();
    }

    @Test
    public void ViewCartBrandProducts(){
        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        topMenuPage.clickOnMenuItem("Products");

        // (Step 4)
        Assert.assertFalse(sideBarPage.brandItems.isEmpty());

        // (Step 5)
        String brand1 = "POLO";
        sideBarPage.clickOnBrand(brand1);

        // (Step 6)
        Assert.assertEquals(driver.getCurrentUrl().toLowerCase(),("https://automationexercise.com/brand_products/" + brand1).toLowerCase());
        String titleToAssert1 = "BRAND - " + brand1;
        Assert.assertTrue(brandProductsPage.pageTitle.getText().contains(titleToAssert1));
        Assert.assertFalse(brandProductsPage.listOfProducts.isEmpty());
        Assert.assertTrue(brandProductsPage.listOfProducts.get(0).isDisplayed());

        // (Step 7)
        String brand2 = "BABYHUG";
        sideBarPage.clickOnBrand(brand2);

        // (Step 8)
        Assert.assertEquals(driver.getCurrentUrl().toLowerCase(),("https://automationexercise.com/brand_products/" + brand2).toLowerCase());
        String titleToAssert2 = "BRAND - " + brand2;
        Assert.assertTrue(brandProductsPage.pageTitle.getText().contains(titleToAssert2));
        Assert.assertFalse(brandProductsPage.listOfProducts.isEmpty());
        Assert.assertTrue(brandProductsPage.listOfProducts.get(0).isDisplayed());
    }
}