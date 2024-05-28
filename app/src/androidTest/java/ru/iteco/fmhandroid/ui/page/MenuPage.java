package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.data.DataHelper.GetElement;
import static ru.iteco.fmhandroid.ui.data.DataHelper.GetElementText;
import static ru.iteco.fmhandroid.ui.data.DataHelper.GetElementTextString;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitForElement;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.AboutApp;
import ru.iteco.fmhandroid.ui.elements.Menu;
import ru.iteco.fmhandroid.ui.elements.News;
import ru.iteco.fmhandroid.ui.elements.Quotes;

public class MenuPage {

    public void logOut() {//Выход из личного кабинета
        Allure.step("Отображается страница авторизации");
        GetElement(Menu.authorizationButton).perform(click());
        GetElementText(Menu.logOutButton).check(matches(isDisplayed()));
        GetElementText(Menu.logOutButton).perform(click());
    }

    public void checkTradeMark() {//Проверяем видимость иконки авторизации
        Allure.step("Отображается иконка авторизации");
        onView(isRoot()).perform(waitForElement(withId(R.id.authorization_image_button), 5000));
        GetElementTradeMark(Menu.tradeMark).check(matches(isDisplayed()));
    }

    public ViewInteraction GetElementTradeMark(int id) {
        return onView(withId(id));
    }

    public void goToAboutApp() { //Переходим на страницу О приложении
        Allure.step("Отображается страница О приложении");
        onView(isRoot()).perform(waitForElement(withId(R.id.authorization_image_button), 5000));
        GetElement(Menu.menuButton).perform(click());
        GetElementText(Menu.aboutOfMenu).check(matches(isDisplayed()));
        GetElementText(Menu.aboutOfMenu).perform(click());
        GetElementText(AboutApp.versionValue).check(matches(isDisplayed()));
    }

    public void goQuotes() {//переходим в цитаты - нажимаем на бабочку
        Allure.step("Отображается страница цитаты");
//        Quotes quotes = new Quotes();
        GetElement(Menu.goQuotesButton).perform(click());
        GetElementTextString(Quotes.missionTitleText).check(matches(isDisplayed()));
    }

    public void goNews() {//переходим в Новости
        Allure.step("Отображается страница Новости");
        GetElement(Menu.menuButton).perform(click());
        GetElementText(Menu.newsOfMenu).check(matches(isDisplayed()));
        GetElementText(Menu.newsOfMenu).perform(click());
        GetElementText(News.titleNews).check(matches(isDisplayed()));
    }
}
