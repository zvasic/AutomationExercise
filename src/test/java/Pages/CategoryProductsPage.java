package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryProductsPage extends BaseTest {

    public CategoryProductsPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "features_items")
    public WebElement pageTitle;
}
