package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CheckoutPage extends BaseTest {

    public CheckoutPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "ul.address > li.address_title > h3")
    public WebElement deliveryTitle;

    @FindBy(css = "ul.address.item box > li")
    public List<WebElement> listDeliveryAddressFields;

    @FindBy(css = "ul.address.alternate_item box > li")
    public List<WebElement> listBillingAddressFields;

    @FindBy(css = "textarea[name='message']")
    public WebElement orderCommentField;

    @FindBy(css = "a.check_out")
    public WebElement placeOrderButton;

    @FindBy(css = "tr[id^='product-']")
    public List<WebElement> listItemsInCart;

    @FindBy(className = "cart_quantity")
    public WebElement cartQuantity;

    // ----------------------------------------------

    public void checkPersonalInfo() {
        for (int i = 0; i < listDeliveryAddressFields.size(); i++) {
            Assert.assertEquals(
                    listDeliveryAddressFields.get(i).getText().trim(),
                    listBillingAddressFields.get(i).getText().trim());
        }
    }

    public boolean isItemInCart(String id) {
        for (WebElement i : listItemsInCart) {
            if (i.getAttribute("id").contains(id)) {
                return true;
            }
        }
        return false;
    }

    public void orderCommentFieldInput(String comment) {
        orderCommentField.clear();
        orderCommentField.sendKeys(comment);
    }

    public void clickOnPlaceOrderButton() {
        placeOrderButton.click();
    }

}
