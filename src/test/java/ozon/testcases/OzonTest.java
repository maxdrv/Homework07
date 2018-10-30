package ozon.testcases;

import org.junit.Ignore;
import org.junit.Test;
import ozon.base.TestBase;
import ozon.pages.HomePage;
import ozon.util.TestUtil;

import java.util.HashSet;

import static ozon.testdata.TestData.searchItem;

public class OzonTest extends TestBase {

    HomePage homePage = new HomePage();
    HashSet<String> items = new HashSet<>();

    @Test
    public void loginOzonTest() {
        homePage.myOzonPopUpClick()                                     // Перевод курсора для отображения выпадающего меню
                .setMyOzonMenuSignInClick()                             // Кнопка "Выход"
                .signInUsingMailClick()                                 // Кнопка "С использованием почты"
                .login(properties.getProperty("username"),              // Ввод логина и пароля
                        properties.getProperty("password"))
                .search(searchItem);                                    // Ввод названия предмета в строку поиска

        items = homePage.addItemsToCard(TestUtil.AMOUNT_OF_ITEMS);      // Добавляем указаное в аргументу метода, возвращает HashSet добавленных предметов

        homePage.moveToCard()                                           // Кнопка "Корзина"
                .checkCardItems(items)                                  // Проверка на соответствие добавленных ранее предметов в корзину
                .removeItemsFromCard();                                 // Удалить предметы из корзины

        homePage.setMyOzonMenuSignOutClick()
                .repeatPopUp()
                .signInUsingMailClick()                                 // Кнопка "С использованием почты"
                .login(properties.getProperty("username"),              // Ввод логина и пароля
                        properties.getProperty("password"))
                .search("apple")
                .moveToCard()
                .checkSoppingCardForEmpty("Корзина пуста");


    }

}
