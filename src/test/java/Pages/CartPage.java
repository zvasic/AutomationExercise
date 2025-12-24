package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "empty_cart")
    public WebElement emptyCartMessage;

    @FindBy(css = "a.check_out")
    public WebElement checkoutButton;

    @FindBy(id = "checkoutModal")
    public WebElement checkoutModal;

    @FindBy(linkText = "Register / Login")
    public WebElement registerLoginFromCartModal;

    @FindBy(css = "tr[id^='product-']")
    public WebElement itemRow;

    @FindBy(css = "tr[id^='product-']")
    public List<WebElement> listItemsInCart;

    @FindBy(css = "a[class='cart_quantity_delete']")
    public List<WebElement> listOfDeleteButtons;

    @FindBy(css = "a[class='cart_quantity_delete']")
    public WebElement singleDeleteButtonInCart;

    // --------------------------------

    public boolean cartIsEmpty() {
        if (emptyCartMessage.getText().contains("Cart is empty!")) {
            return true;
        }
        return false;
    }

    public void clickOnCheckoutButton() {
        checkoutButton.click();
    }

    public void clickOnRegisterLoginFromCartModal() {
        registerLoginFromCartModal.click();
    }

    public boolean isItemInCart(String id) {
        for (WebElement i : listItemsInCart) {
            if (i.getAttribute("id").equalsIgnoreCase("product-" + id)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkItemQuantityInCart(String id, String quantity) {
        for (WebElement i : listItemsInCart) {
            if (i.getAttribute("id").contains(id)) {
                String itemQuantity = i.findElement(By.className("cart_quantity")).getText();
                if (itemQuantity.equals(quantity)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkItemDataInCart(String id) {
        for (WebElement i : listItemsInCart) {
            if (i.getAttribute("id").contains(id)) {
                String itemPrice = i.findElement(By.className("cart_price")).getText();
                itemPrice = itemPrice.split(" ")[1];
                String itemQuantity = i.findElement(By.className("cart_quantity")).getText();
                String totalPrice = i.findElement(By.className("cart_total_price")).getText();
                totalPrice = totalPrice.split(" ")[1];

                int totalPriceInt = Integer.parseInt(totalPrice);
                int priceInt = Integer.parseInt(itemPrice);
                int quantityInt = Integer.parseInt(itemQuantity);
                int totalInt = priceInt * quantityInt;

                if (totalInt == totalPriceInt) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isItemDeleteInCart(String id) {
        for (WebElement i : listOfDeleteButtons) {
            if (i.getAttribute("data-product-id").equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void deleteItemFromCart(String id) {
        for (WebElement i : listItemsInCart) {
            WebElement deleteButton = i.findElement(By.cssSelector("a[class='cart_quantity_delete']"));
            if (deleteButton.getAttribute("data-product-id").equals(id)) {
                deleteButton.click();

                // Wait until element is invisible after deletion
                wait.until(ExpectedConditions.invisibilityOf(deleteButton));

                // Verify element is deleted from cart - not visible anymore (if visible = true)
                Assert.assertFalse(isItemInCart(id));
                break;
            }
        }
    }

    public void deleteAllItemsFromCart() {
        for (WebElement i : listItemsInCart) {
            WebElement deleteButton = i.findElement(By.cssSelector("a[class='cart_quantity_delete']"));
            deleteButton.click();
        }
        wait.until(ExpectedConditions.visibilityOf(emptyCartMessage));
        Assert.assertTrue(cartIsEmpty());
    }
}
