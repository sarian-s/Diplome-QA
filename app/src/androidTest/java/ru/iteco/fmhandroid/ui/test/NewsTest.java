package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.date.Data;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@RunWith(AllureAndroidJUnit4.class)

public class NewsTest {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logIn() throws InterruptedException {
        Thread.sleep(5000);
        try {
            AuthorizationPage.isAuthorizationWindow();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationPage.logIn(Data.validLogin, Data.validPassword);
    }
    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private View decorView;
    @Before
    public void setUp() {
        activityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Test
    @DisplayName("Создание новости без категории")
    public void shouldErrorNotCategory() throws InterruptedException {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields("yes", "no", "Объявление", "no", "Сергей Сарьян", "no", "no", "dial", "save", "no", "Сарьян Описание");
        NewsPage.saveNews();Thread.sleep(1000);

        onView(withText(R.string.empty_fields))
                .inRoot(withDecorView(Matchers.not(decorView)))

                .check(matches(withText(R.string.empty_fields)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Создание новости")
    public void shouldCreateNews() throws InterruptedException {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields("no", "yes", "Объявление", "no", "Сарьян С", "no", "no", "dial", "save", "no", "Сарьян Сергей");
        NewsPage.saveNews();Thread.sleep(1000);
        NewsPage.checkCreatedNews("Сарьян С");
        MenuPage.goNews();
        NewsPage.filterNews("Объявление");
        NewsPage.deleteNews("Сарьян С");
    }

    @Test
    @DisplayName("Удаление новости")
    public void shouldDeleteNews() throws InterruptedException {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields("no", "yes", "Объявление", "no", "Сарьян Тест", "no", "no", "dial", "save", "no", "Тест Сарьян");
        NewsPage.saveNews();Thread.sleep(1000);
        MenuPage.goNews();
        NewsPage.filterNews("Объявление");
        NewsPage.deleteNews("Сарьян Тест");

        MenuPage.goNews();
        NewsPage.checkDeleteNews("Сарьян Тест");
    }

    @Test
    @DisplayName("Проверка работы фильтра")
    public void shouldFilterNewsCategory() throws InterruptedException {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields("no", "yes", "Объявление", "no", "Проверка фильтра Сарьян", "no", "no", "dial", "save", "no", "Описание Сарьян");
        NewsPage.saveNews();Thread.sleep(1000);
        MenuPage.goNews();
        NewsPage.filterNews("Объявление");
        NewsPage.checkCreatedNews("Проверка фильтра Сарьян");
        NewsPage.deleteNews("Проверка фильтра Сарьян");
    }

    @Test
    @DisplayName("Редактирование новости")
    public void shouldChangeNews() throws InterruptedException {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNewsFields("no", "yes", "Объявление", "no", "Тест Сарьян", "no", "no", "dial", "save", "no", "Сарьян Тест");
        NewsPage.saveNews();Thread.sleep(1000);
        MenuPage.goNews();
        NewsPage.filterNews("Объявление");
        NewsPage.updateTitleNews("Тест Сарьян", "Новый Тест Сарьян");
        MenuPage.goNews();
        NewsPage.filterNews("Объявление");
        NewsPage.checkCreatedNews("Новый Тест Сарьян");
        NewsPage.deleteNews("Новый Тест Сарьян");
    }
}