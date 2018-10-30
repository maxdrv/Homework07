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

    public LoginPage setMyOzonMenuSignInClick() {
        myOzonMenuSignIn.click();
        return new LoginPage();
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

    public CardPage moveToCard() {
        waitForVisible(shoppingCardButton);
        shoppingCardButton.click();
        return new CardPage();
    }

    public LoginPage repeatPopUp() {
        WebElement PopUp = driver.findElement(By.xpath("//div[@class = 'top-block']"));
        Actions action = new Actions(driver);
        action.moveToElement(PopUp).build().perform();

        action.moveToElement(PopUp, 0, 160).click().build().perform();
        System.out.println("finish");
        return new LoginPage();
    }

}
