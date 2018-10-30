package ozon.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ozon.base.TestBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HomePage extends TestBase {

    @FindBy(xpath = "//div[@class = 'jsUserMenuWrap']")
    WebElement myOzonPopUp;

    @FindBy(xpath = "//div[contains(text(), 'Вход')]")
    WebElement myOzonMenuSignIn;

    @FindBy(xpath = "//a[contains(text(), 'Войти по почте')]")
    WebElement signInUsingMail;

    @FindBy(xpath = "//input[@type = 'text']")
    WebElement signInMailInput;

    @FindBy(xpath = "//input[@type = 'password']")
    WebElement signInPasswordInput;

    @FindBy(xpath = "//button[contains(text(), 'Войти')]")
    WebElement signInButton;

    @FindBy(xpath = "//input[@type = 'text']")
    WebElement searchInput;

    @FindBy(xpath = "//a[@class = 'eMyOzon_ItemWrap']//span[contains(text(), 'Корзина')]")
    WebElement shoppingCardButton;

    public HomePage() {
        PageFactory.initElements(driver,this);
    }

    public HomePage myOzonPopUpClick() {
        Actions action = new Actions(driver);
        action.moveToElement(myOzonPopUp).build().perform();
        return this;
    }

    public HomePage setMyOzonMenuSignInClick() {
        myOzonMenuSignIn.click();
        return this;
    }

    public HomePage signInUsingMailClick() {
        waitForVisible(signInUsingMail);
        signInUsingMail.click();
        return this;
    }

    public HomePage login(String username, String password) {
        signInMailInput.sendKeys(username);
        signInPasswordInput.sendKeys(password);
        signInButton.click();
        return this;
    }

    public HomePage search(String item) {
        waitForVisible(searchInput);
        searchInput.sendKeys(item);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
        return this;
    }

    public HashSet<String> addItemsToCard(int amountOfItems) {
        String tile = "//div[@class = 'tile']";
        String name = "//div[@class = 'tile']//p[@class = 'name']";
        HashSet<String> namesOfItems = new HashSet<>();
        for (int i = 1; i <= amountOfItems; i++) {
            String patern = String.format("//div[@class = 'item-view']/following-sibling::div[%d]", i);
            String nameItemXpath = String.format("%s//p[@class = 'name']", patern);
            String descriptionItem = driver.findElement(By.xpath(nameItemXpath)).getText();
            namesOfItems.add(descriptionItem);

            String toCardXath = String.format("%s//span[contains(text(), 'корзину')]", patern);
            driver.findElement(By.xpath(toCardXath)).click();
        }
        return namesOfItems;
    }

    public HomePage moveToCard() {
        waitForVisible(shoppingCardButton);
        shoppingCardButton.click();
        return this;
    }

    @FindBy(xpath = "//div[@class = 'bFlatButton mHuge mGreen jsMakeOrder']")
    WebElement confirmTheOrderButton;

    @FindBy(xpath = "//div[@class = 'bIconButton mRemove mGray jsRemoveAll']")
    WebElement removeItemsButton;

    @FindBy(xpath = "//a[contains(text(), 'Начать покупки')]")
    WebElement startShoppingButton;

    @FindBy(xpath = "//span[@class = 'jsInnerContentpage_title']")
    WebElement emptyMessageShpoongCard;

    public HomePage checkCardItems(HashSet<String> items) {
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

    public HomePage removeItemsFromCard() {
        waitForVisible(confirmTheOrderButton);

        waitForVisible(removeItemsButton);
        removeItemsButton.click();
        return this;
    }


    public HomePage setMyOzonMenuSignOutClick() {
        WebElement popUp = driver.findElement(By.xpath("//div[@class = 'eMyOzon_Item_Top ']"));
        Actions action = new Actions(driver);
        action.moveToElement(popUp).build().perform();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        action.moveToElement(popUp, 0, 171).click().build().perform();
        return this;
    }

    public HomePage repeatPopUp() {
        WebElement PopUp = driver.findElement(By.xpath("//div[@class = 'top-block']"));
        Actions action = new Actions(driver);
        action.moveToElement(PopUp).build().perform();



        action.moveToElement(PopUp, 0, 160).click().build().perform();
        System.out.println("finish");
        return this;
    }

    public HomePage checkSoppingCardForEmpty(String expectedText) {
        String availableText = emptyMessageShpoongCard.getText();
        Assert.assertEquals("Заголовок корзины не соответствует ожидаемому",expectedText, availableText);
        return this;
    }

}
