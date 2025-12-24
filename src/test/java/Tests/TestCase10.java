package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.TopMenuPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase10 extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        homePage = new HomePage();
        topMenuPage = new TopMenuPage();
    }

    @Test
    public void VerifySubscriptionInHomePage() {
        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // (Step 4)
        scrollToELement(homePage.subscribeFormTitle);

        // (Step 5)
        Assert.assertTrue(homePage.subscribeFormTitle.isDisplayed());
        Assert.assertEquals(homePage.subscribeFormTitle.getText().trim().toLowerCase(), "SUBSCRIPTION".toLowerCase());

        // (Step 6)
        String email = "exampleUser+1@example.com";
        homePage.subscribeEmailFieldInput(email);
        homePage.subscribeSubmitButton();

        // (Step 8)
        Assert.assertTrue(homePage.subscribeMessage.isDisplayed());
        Assert.assertTrue(homePage.subscribeMessage.getText().trim().toLowerCase().contains("You have been successfully subscribed!".toLowerCase()));

    }
}
