package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public ExcelReader excelReader;

    public HomePage homePage;
    public TopMenuPage topMenuPage;
    public SideBarPage sideBarPage;
    public CategoryProductsPage categoryProductsPage;
    public BrandProductsPage brandProductsPage;
    public ProductsPage productsPage;
    public ProductDetailPage productDetailPage;
    public LoginSignupPage loginSignupPage;
    public SignupPage signupPage;
    public AccountPage accountPage;
    public DeleteAccountPage deleteAccountPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public PaymentPage paymentPage;
    public PaymentDonePage paymentDonePage;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void scrollToELement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
