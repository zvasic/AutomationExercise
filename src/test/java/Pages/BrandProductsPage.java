package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BrandProductsPage extends BaseTest {

    public BrandProductsPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "features_items")
    public WebElement pageTitle;

    @FindBy(className = "single-products")
    public List<WebElement> listOfProducts;
}
