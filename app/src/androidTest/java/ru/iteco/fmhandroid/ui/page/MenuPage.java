package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.date.DataHelper.waitForElement;

import androidx.test.rule.ActivityTestRule;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.elements.AboutApp;
import ru.iteco.fmhandroid.ui.elements.Menu;
import ru.iteco.fmhandroid.ui.elements.News;
import ru.iteco.fmhandroid.ui.elements.Quotes;

public class MenuPage {

    //Выход из личного кабинета
    public static void logOut() {
        Menu.authorizationButton.perform(click());
        Menu.logOutButton.check(matches(isDisplayed()));
        Menu.logOutButton.perform(click());
    }

    //Проверяем видимость логотипа приложения
    public static void checkTradeMark() {
        onView(isRoot()).perform(waitForElement(withId(R.id.authorization_image_button), 5000));
        Menu.tradeMark.check(matches(isDisplayed()));
    }

    //Переходим на страницу О приложении
    public static void goToAboutApp() {
        onView(isRoot()).perform(waitForElement(withId(R.id.authorization_image_button), 5000));
        Menu.menuButton.perform(click());
        Menu.aboutOfMenu.check(matches(isDisplayed()));
        Menu.aboutOfMenu.perform(click());
        AboutApp.versionValue.check(matches(isDisplayed()));
    }

    public static void goQuotes() {
        //переходим в цитаты - нажимаем на бабочку
        Menu.goQuotesButton.perform(click());
        Quotes.missionTitleText.check(matches(isDisplayed()));
    }

    //переходим в News
    public static void goNews() throws InterruptedException {
        Menu.menuButton.perform(click());Thread.sleep(2000);
        Menu.newsOfMenu.check(matches(isDisplayed()));
        Menu.newsOfMenu.perform(click());
        News.titleNews.check(matches(isDisplayed()));
    }


    public static void checkDisplayErrorMessage(ActivityTestRule<AppActivity> activityTestRule, String message) {
        onView(withText(message)).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())); // проверяем что отображается окно с нужным текстом
    }
}
