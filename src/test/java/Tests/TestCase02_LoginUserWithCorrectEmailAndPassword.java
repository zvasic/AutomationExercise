package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.LoginSignupPage;
import Pages.TopMenuPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCase02_LoginUserWithCorrectEmailAndPassword extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        topMenuPage = new TopMenuPage();
        loginSignupPage = new LoginSignupPage();
    }

    @Test
    public void LoginUserWithCorrectEmailAndPassword() {
        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // (Step 4)
        topMenuPage.clickOnMenuItem("Signup / Login");

        // (Step 5)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login");
        Assert.assertTrue(loginSignupPage.loginFormTitle.isDisplayed());
        Assert.assertEquals(loginSignupPage.loginFormTitle.getText(), "Login to your account");

        // (Step 6 -7)
        String validEmail = "exampleUser+1@example.com";
        String validPassword = "somePassword";
        loginSignupPage.loginUser(validEmail, validPassword);

        // (Step 8)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        Assert.assertTrue(topMenuPage.loggedInMsg.isDisplayed());
        Assert.assertTrue(topMenuPage.loggedInMsg.getText().trim().contains("Logged in as"));

        // (Step 9 - Ignored)
    }

    @Test
    public void LoginUserWithCorrectEmailAndPasswordExcel() throws IOException {
        excelReader = new ExcelReader("data\\LoginData.xlsx");

        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // (Step 4)
        topMenuPage.clickOnMenuItem("Signup / Login");

        // (Step 5)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login");
        Assert.assertTrue(loginSignupPage.loginFormTitle.isDisplayed());
        Assert.assertEquals(loginSignupPage.loginFormTitle.getText(), "Login to your account");

        // (Step 6 -7)
        String validEmail = excelReader.getStringData("LoginData", 1, 1);
        String validPassword = excelReader.getStringData("LoginData", 1, 2);
        loginSignupPage.loginUser(validEmail, validPassword);

        // (Step 8)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        Assert.assertTrue(topMenuPage.loggedInMsg.isDisplayed());
        Assert.assertTrue(topMenuPage.loggedInMsg.getText().trim().contains("Logged in as"));

        // (Step 9 - Ignored)
    }
}