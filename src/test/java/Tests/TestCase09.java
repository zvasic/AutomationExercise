package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.ProductsPage;
import Pages.TopMenuPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class TestCase09 extends BaseTest {

    @BeforeMethod
    public void setUpPage() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        topMenuPage = new TopMenuPage();
        productsPage = new ProductsPage();
    }

    @Test
    public void SearchProduct() throws IOException {
        excelReader = new ExcelReader("data\\LoginData.xlsx");

        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // (Step 4)
        topMenuPage.clickOnMenuItem("Products");

        // (Step 5)
        Assert.assertEquals(productsPage.pageTitle.getText().trim().toLowerCase(), "ALL PRODUCTS".toLowerCase());
        Assert.assertFalse(productsPage.listOfProducts.isEmpty());

        // (Step 6)
        productsPage.searchFieldInput("shirt");
        productsPage.clickOnSearchButton();

        // (Step 7)
        Assert.assertEquals(productsPage.pageTitle.getText().trim().toLowerCase(), "Searched Products".toLowerCase());

        // (Step 8)
        Assert.assertFalse(productsPage.listOfProducts.isEmpty());
        Assert.assertTrue(productsPage.listOfProducts.get(0).isDisplayed());
    }
}