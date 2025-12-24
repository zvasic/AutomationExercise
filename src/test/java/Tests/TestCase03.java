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

public class TestCase03 extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        // (Step 1)
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        topMenuPage = new TopMenuPage();
        loginSignupPage = new LoginSignupPage();
    }

    @Test
    public void LoginUserWithIncorrectEmailAndPassword() {
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
        Assert.assertEquals(loginSignupPage.loginFormTitle.getText().trim().toLowerCase(), "Login to your account".toLowerCase());

        // (Step 6 -7)
        String validEmail = "exampleUser+100@example.com";
        String validPassword = "somePassword100";
        loginSignupPage.loginUser(validEmail, validPassword);

        // (Step 8)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login");
        Assert.assertTrue(loginSignupPage.loginSignupMessage.isDisplayed());
        Assert.assertTrue(loginSignupPage.loginSignupMessage.getText().trim().toLowerCase().contains("Your email or password is incorrect!".toLowerCase()));
    }

    @Test
    public void LoginUserWithIncorrectEmailAndPasswordExcel() throws IOException {
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
        Assert.assertEquals(loginSignupPage.loginFormTitle.getText().trim().toLowerCase(), "Login to your account".toLowerCase());

        // (Step 6 -7)
        String validEmail = excelReader.getStringData("LoginData", 5, 1);
        String validPassword = excelReader.getStringData("LoginData", 5, 2);
        loginSignupPage.loginUser(validEmail, validPassword);

        // (Step 8)
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login");
        Assert.assertTrue(loginSignupPage.loginSignupMessage.isDisplayed());
        Assert.assertTrue(loginSignupPage.loginSignupMessage.getText().trim().toLowerCase().contains("Your email or password is incorrect!".toLowerCase()));
    }
}
