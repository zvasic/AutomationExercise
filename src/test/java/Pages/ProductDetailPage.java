package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailPage extends BaseTest {

    public ProductDetailPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.product-information > h2")
    public WebElement productName;

    @FindBy(css = "div.product-information > p:nth-of-type(1)")
    public WebElement productCategory;

    @FindBy(css = "div.product-information > span > span")
    public WebElement productPrice;

    @FindBy(css = "div.product-information > p:nth-of-type(2)")
    public WebElement productAvailability;

    @FindBy(css = "div.product-information > p:nth-of-type(3)")
    public WebElement productCondition;

    @FindBy(css = "div.product-information > p:nth-of-type(4)")
    public WebElement productBrand;

    @FindBy(id = "quantity")
    public WebElement quantityField;

    @FindBy(css = "button[class$='cart']")
    public WebElement addToCartButton;

    @FindBy(linkText = "View Cart")
    public WebElement viewCartButton;

    @FindBy(css = "a[data-toggle='tab']")
    public WebElement reviewTitle;

    @FindBy(id = "name")
    public WebElement reviewNameField;

    @FindBy(id = "email")
    public WebElement reviewEmailField;

    @FindBy(id = "review")
    public WebElement reviewTextField;

    @FindBy(id = "button-review")
    public WebElement reviewSubmitButton;

    @FindBy(css = "div.alert-success")
    public WebElement successMessage;

    // -------------------------------

    public void quantityFieldInput(String quantity){
        quantityField.clear();
        quantityField.sendKeys(quantity);
    }

    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }

    public void clickOnViewCartButton(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        viewCartButton.click();
    }

    public void reviewNameFieldInput(String name){
        reviewNameField.clear();
        reviewNameField.sendKeys(name);
    }

    public void reviewEmailFieldInput(String email){
        reviewEmailField.clear();
        reviewEmailField.sendKeys(email);
    }

    public void reviewTextFieldInput(String text){
        reviewTextField.clear();
        reviewTextField.sendKeys(text);
    }

    public void clickOnReviewSubmitButton(){
        reviewSubmitButton.click();
    }
}
