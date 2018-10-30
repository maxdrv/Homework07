package ozon.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ozon.base.TestBase;

import java.util.HashSet;
import java.util.Set;

public class CardPage extends TestBase {

    @FindBy(xpath = "//div[@class = 'bFlatButton mHuge mGreen jsMakeOrder']")
    WebElement confirmTheOrderButton;

    @FindBy(xpath = "//div[@class = 'bIconButton mRemove mGray jsRemoveAll']")
    WebElement removeItemsButton;

    @FindBy(xpath = "//a[contains(text(), 'Начать покупки')]")
    WebElement startShoppingButton;

    @FindBy(xpath = "//span[@class = 'jsInnerContentpage_title']")
    WebElement emptyMessageShpoongCard;

    @FindBy(xpath = "//div[@class = 'eMyOzon_Item_Top ']")
    WebElement logOutFromCard;

    public CardPage() {
        PageFactory.initElements(driver,this);
    }

    public CardPage checkCardItems(HashSet<String> items) {
        Set<String> cardItems = new HashSet<>();
        String firstItem = "//div[@class = 'jsViewCollection jsChild_DOM_items']/div";
        String spanXpath = "//span[@class = 'eCartItem_nameValue']";
        String mainPatern = "//div[@class = 'jsViewCollection jsChild_DOM_items']/div/following-sibling::div[%d]";

        cardItems.add(driver.findElement(By.xpath(firstItem + spanXpath)).getText());
        for (int i = 1; i < items.size(); i++) {
            String paternIter = String.format(mainPatern, i);
            String nameItemXpath = paternIter + spanXpath;
            String descriptionItem = driver.findElement(By.xpath(nameItemXpath)).getText();
            cardItems.add(descriptionItem);
        }
        Assert.assertEquals("Коллекции не равны",cardItems, items);
        return this;
    }

    public CardPage removeItemsFromCard() {
        waitForVisible(confirmTheOrderButton);

        waitForVisible(removeItemsButton);
        removeItemsButton.click();
        return this;
    }

    public CardPage checkSoppingCardForEmpty(String expectedText) {
        String availableText = emptyMessageShpoongCard.getText();
        Assert.assertEquals("Заголовок корзины не соответствует ожидаемому",expectedText, availableText);
        return this;
    }

    public HomePage setMyOzonMenuSignOutClick() {
        //WebElement popUp = driver.findElement(By.xpath("//div[@class = 'eMyOzon_Item_Top ']"));
        Actions action = new Actions(driver);
        action.moveToElement(logOutFromCard).build().perform();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        action.moveToElement(logOutFromCard, 0, 171).click().build().perform();
        return new HomePage();
    }
}
