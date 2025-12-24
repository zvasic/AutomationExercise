package Tests;

import Base.BaseTest;
import Pages.CategoryProductsPage;
import Pages.HomePage;
import Pages.SideBarPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCase18_RemoveProductsFromCart extends BaseTest {

    @BeforeMethod
    public void setUpPage() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        homePage = new HomePage();
        sideBarPage = new SideBarPage();
        categoryProductsPage = new CategoryProductsPage();
    }

    @Test
    public void ViewCategoryProducts(){
        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        Assert.assertFalse(sideBarPage.categoryItems.isEmpty());

        // (Step 4 - 5)
        String categoryW = "WOMEN";
        String subCategoryW = "TOPS";
        sideBarPage.clickOnCategory(categoryW, subCategoryW);

        // (Step 6)
        Assert.assertTrue(driver.getCurrentUrl().contains("https://automationexercise.com/category_products"));

        String titleToAssertW = categoryW + " - " + subCategoryW;
        Assert.assertTrue(categoryProductsPage.pageTitle.getText().contains(titleToAssertW));

        // (Step 7)
        String categoryM = "MEN";
        String subCategoryM = "JEANS";
        sideBarPage.clickOnCategory(categoryM, subCategoryM);

        // (Step 8)
        Assert.assertTrue(driver.getCurrentUrl().contains("https://automationexercise.com/category_products"));

        String titleToAssertM = categoryM + " - " + subCategoryM;
        Assert.assertTrue(categoryProductsPage.pageTitle.getText().contains(titleToAssertM));
    }
}
