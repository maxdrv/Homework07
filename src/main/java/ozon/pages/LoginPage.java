package ozon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ozon.base.TestBase;

public class LoginPage extends TestBase {

    @FindBy(xpath = "//a[contains(text(), 'Войти по почте')]")
    WebElement signInUsingMail;

    @FindBy(xpath = "//input[@type = 'text']")
    WebElement signInMailInput;

    @FindBy(xpath = "//input[@type = 'password']")
    WebElement signInPasswordInput;

    @FindBy(xpath = "//button[contains(text(), 'Войти')]")
    WebElement signInButton;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public LoginPage signInUsingMailClick() {
        waitForVisible(signInUsingMail);
        signInUsingMail.click();
        return this;
    }

    public HomePage login(String username, String password) {
        signInMailInput.sendKeys(username);
        signInPasswordInput.sendKeys(password);
        signInButton.click();
        return new HomePage();
    }
}
