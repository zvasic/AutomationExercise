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

public class TestCase05_RegisterUserWithExistingEmail extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        topMenuPage = new TopMenuPage();
        loginSignupPage = new LoginSignupPage();
    }

    @Test
    public void RegisterUserWithExistingEmail() {
        // (Step 2)
        String homepage = "https://automationexercise.com/";
        driver.navigate().to(homepage);

        // (Step 3)
        Assert.assertEquals(driver.getCurrentUrl(), homepage);

        // (Step 4)
        topMenuPage.clickOnMenuItem("Signup / Login");

        // (Step 5)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login");
        Assert.assertTrue(loginSignupPage.signupFormTitle.isDisplayed());
        Assert.assertEquals(loginSignupPage.signupFormTitle.getText(), "New User Signup!");

        // (Step 6)
        String validName = "ExampleUser";
        String invalidEmail = "exampleUser+1@example.com";
        loginSignupPage.signupNameFieldInput(validName);
        loginSignupPage.signupEmailFieldInput(invalidEmail);

        // (Step 7)
        loginSignupPage.clickOnSignupButton();

        // (Step 8)
        Assert.assertTrue(loginSignupPage.loginSignupMessage.isDisplayed());
        Assert.assertEquals(loginSignupPage.loginSignupMessage.getText().trim(), "Email Address already exist!");
    }

    @Test
    public void RegisterUserWithExistingEmailExcel() throws IOException {
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
        Assert.assertTrue(loginSignupPage.signupFormTitle.isDisplayed());
        Assert.assertEquals(loginSignupPage.signupFormTitle.getText(), "New User Signup!");

        // (Step 6)
        String validName = excelReader.getStringData("LoginData", 4, 0);
        String invalidEmail = excelReader.getStringData("LoginData", 4, 1);
        loginSignupPage.signupNameFieldInput(validName);
        loginSignupPage.signupEmailFieldInput(invalidEmail);

        // (Step 7)
        loginSignupPage.clickOnSignupButton();

        // (Step 8)
        Assert.assertTrue(loginSignupPage.loginSignupMessage.isDisplayed());
        Assert.assertEquals(loginSignupPage.loginSignupMessage.getText().trim(), "Email Address already exist!");
    }
}
