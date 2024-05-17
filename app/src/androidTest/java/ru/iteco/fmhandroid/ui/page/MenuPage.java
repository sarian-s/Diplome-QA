package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.date.DataHelper.waitForElement;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.AboutApp;
import ru.iteco.fmhandroid.ui.elements.Menu;
import ru.iteco.fmhandroid.ui.elements.News;
import ru.iteco.fmhandroid.ui.elements.Quotes;

public class MenuPage {

    public static void logOut() {//Выход из личного кабинета
        Allure.step("Отображается страница авторизации");
        Menu.authorizationButton.perform(click());
        Menu.logOutButton.check(matches(isDisplayed()));
        Menu.logOutButton.perform(click());
    }

    public static void checkTradeMark() {//Проверяем видимость иконки авторизации
        Allure.step("Отображается иконка авторизации");
        onView(isRoot()).perform(waitForElement(withId(R.id.authorization_image_button), 5000));
        Menu.tradeMark.check(matches(isDisplayed()));
    }

    public static void goToAboutApp() { //Переходим на страницу О приложении
        Allure.step("Отображается страница О приложении");
        onView(isRoot()).perform(waitForElement(withId(R.id.authorization_image_button), 5000));
        Menu.menuButton.perform(click());
        Menu.aboutOfMenu.check(matches(isDisplayed()));
        Menu.aboutOfMenu.perform(click());
        AboutApp.versionValue.check(matches(isDisplayed()));
    }

    public static void goQuotes() {//переходим в цитаты - нажимаем на бабочку
        Allure.step("Отображается страница цитаты");
        Menu.goQuotesButton.perform(click());
        Quotes.missionTitleText.check(matches(isDisplayed()));
    }

    public static void goNews() {//переходим в Новости
        Allure.step("Отображается страница Новости");
        Menu.menuButton.perform(click());
        Menu.newsOfMenu.check(matches(isDisplayed()));
        Menu.newsOfMenu.perform(click());
        News.titleNews.check(matches(isDisplayed()));
    }
}
