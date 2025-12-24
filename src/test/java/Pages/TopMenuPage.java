package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TopMenuPage extends BaseTest {

    public TopMenuPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "ul[class='nav navbar-nav'] > li > a")
    public List<WebElement> menuList;

    //@FindBy(partialLinkText = "Logged in as")
    @FindBy(css = "ul[class='nav navbar-nav'] > li:last-child")
    public WebElement loggedInMsg;

    // -------------------------------

    public void clickOnMenuItem(String link) {
        for (int i = 0; i < menuList.size(); i++) {
            if (menuList.get(i).getText().trim().contains(link.trim())) {
                menuList.get(i).click();
                break;
            }
        }
    }

    public void checkMenuItem(String link) {
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(menuList.get(i).getText().trim());
            //if (menuList.get(i).getText().trim().equalsIgnoreCase(link.trim())) {

            //menuList.get(i).click();
            //Assert.assertEquals(menuList.get(i).getText().trim(), link);
            //break;
            //}
        }
    }
}
