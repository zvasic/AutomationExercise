package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsPage extends BaseTest {

    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search_product")
    public WebElement searchField;

    @FindBy(id = "submit_search")
    public WebElement submitSearchButton;

    @FindBy(css = "h2[class='title text-center']")
    public WebElement pageTitle;

    @FindBy(className = "single-products")
    public List<WebElement> listOfProducts;

    @FindBy(css = "div.choose > ul > li > a")
    public List<WebElement> listOfViewProductButtons;

    @FindBy(css = "div.productinfo > a")
    public List<WebElement> listOfAddToCartButtons;

    @FindBy(id = "cartModal")
    public WebElement addedToCartModal;

    @FindBy(css = ".btn.btn-success.close-modal.btn-block")
    public WebElement continueShoppingButtonInCartModal;

    @FindBy(linkText = "View Cart")
    public WebElement cartButtonInCartModal;

    // -----------------------------------

    public void searchFieldInput(String searchString) {
        searchField.clear();
        searchField.sendKeys(searchString);
    }

    public void clickOnSearchButton() {
        submitSearchButton.click();
    }

    public void clickOnViewProductButton(String id) {
        for (WebElement e : listOfViewProductButtons) {
            if (e.getAttribute("href").contains(id)) {
                e.click();
                break;
            }
        }
    }

    public void clickOnFirstProductViewButton() {
        listOfViewProductButtons.get(0).click();
    }

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

    public void addAllProductToCart() {
        for (int i = 0; i < listOfAddToCartButtons.size(); i++) {
            WebElement add = listOfAddToCartButtons.get(i);
            scrollToELement(add);
            add.click();
            if (i == listOfAddToCartButtons.size() - 1) {
                clickOnCartButtonInCartModal();
            } else {
                clickOnContinueShoppingButtonInCartModal();
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
}
