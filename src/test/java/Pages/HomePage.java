package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BaseTest {

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "features_items")
    public WebElement pageTitle;

    @FindBy(css = "div.productinfo > a")
    public List<WebElement> listOfAddToCartButtons;

    @FindBy(css = ".btn.btn-success.close-modal.btn-block")
    public WebElement continueShoppingButtonInCartModal;

    @FindBy(linkText = "View Cart")
    public WebElement cartButtonInCartModal;

    @FindBy(css = "div.recommended_items > h2")
    public WebElement recommendedItemsTitle;

    @FindBy(css = "div.recommended_items div.productinfo > a.add-to-cart")
    public List<WebElement> recommendedCarouselItems;

    @FindBy(linkText = "View Cart")
    public WebElement cartModalButton;

    @FindBy(css = "div.single-widget > h2")
    public WebElement subscribeFormTitle;

    @FindBy(id = "susbscribe_email")
    public WebElement subscribeEmailField;

    @FindBy(id = "subscribe")
    public WebElement subscribeSubmitButton;

    @FindBy(css = "div.alert-success")
    public WebElement subscribeMessage;

    // -------------------------------------

    public void addProductToCart(String id) {
        for (WebElement add : listOfAddToCartButtons) {
            if (add.getAttribute("data-product-id").equals(id)) {
                add.click();
                clickOnContinueShoppingButtonInCartModal();
                break;
            }
        }
    }

    public void addMultipleProductToCart(String[] id) {
        for (int i = 0; i < id.length; i++) {
            for (WebElement add : listOfAddToCartButtons) {
                scrollToELement(add);
                if (add.getAttribute("data-product-id").equals(id[i])) {
                    add.click();
                    if (i == id.length - 1) {
                        clickOnCartButtonInCartModal();
                    } else {
                        clickOnContinueShoppingButtonInCartModal();
                    }
                    break;
                }
            }
        }
    }

    public void clickOnContinueShoppingButtonInCartModal() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButtonInCartModal));
        continueShoppingButtonInCartModal.click();
    }

    public void clickOnCartButtonInCartModal() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButtonInCartModal));
        cartButtonInCartModal.click();
    }

    public String pickRecommendedItemAddToCart(){
        WebElement addToCartFromRecomended = wait.until(ExpectedConditions.elementToBeClickable(recommendedCarouselItems.get(0)));
        String productId = addToCartFromRecomended.getAttribute("data-product-id");
        return productId;
    }

    public void clickOnRecommendedItemAddToCart(String productId){
        for (WebElement ri : recommendedCarouselItems){
            if(ri.getAttribute("data-product-id").equals(productId)){
                ri.click();
                break;
            }
        }
    }

    public void clickOnCartButtonModal(){
        wait.until(ExpectedConditions.elementToBeClickable(cartModalButton));
        cartModalButton.click();
    }

    public void subscribeEmailFieldInput(String email){
        subscribeEmailField.clear();
        subscribeEmailField.sendKeys(email);
    }

    public void subscribeSubmitButton(){
        subscribeSubmitButton.click();
    }
}
