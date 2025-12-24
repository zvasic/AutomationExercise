package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SideBarPage extends BaseTest {

    public SideBarPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.panel-heading h4.panel-title a")
    public List<WebElement> categoryItems;

    @FindBy(css = "div.brands-name ul.nav-stacked li a")
    public List<WebElement> brandItems;

    @FindBy(css = "div.left-sidebar > h2")
    public WebElement categoryTitle;

    @FindBy(className = "brands_products")
    public WebElement brandsTitle;

    @FindBy(className = "features_items")
    public WebElement pageTitle;

    // ---------------------------

    public void clickOnCategory(String cat, String subCat) {
        scrollToELement(categoryTitle);

        for (WebElement categoryLink : categoryItems) {
            if (categoryLink.getText().trim().equalsIgnoreCase(cat.trim())) {
                categoryLink.click();
                break;
            }
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.panel-collapse.in")
        ));

        List<WebElement> subCategoryItems = driver.findElements(
                By.cssSelector("div.panel-collapse.in ul li a"));

        for (WebElement subCategory : subCategoryItems) {
            if (subCategory.getText().trim().equalsIgnoreCase(subCat.trim())) {
                subCategory.click();
                break;
            }
        }
    }

    public void clickOnBrand(String brand) {
        scrollToELement(brandsTitle);

        for (WebElement b : brandItems) {
            String name = b.getText().split("\n")[1].trim();
            if (name.equalsIgnoreCase(brand.trim())) {
                //wait.until(ExpectedConditions.elementToBeClickable(b));
                b.click();
                break;
            }
        }
    }
}
