package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
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
import io.qameta.allure.kotlin.Description;
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
    @DisplayName("Создание новости и удаление")
    @Description("При заполнении всех полей новость создается и отображается, после удаления не отображается")
    public void shouldCreateNews() {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillСategory();
        NewsPage.fillTitle();
        NewsPage.fillDatePublication();
        NewsPage.fillTime();
        NewsPage.fillDescription();
        NewsPage.saveNews();
        NewsPage.checkCreatedNews();
        MenuPage.goNews();
        NewsPage.filterNews();
        NewsPage.deleteNews();

    }
    @Test
    @DisplayName("Создание новости без категории")
    @Description("Пользователю отображается сообщение " + "Заполните пустые поля")
    public void shouldErrorNotCategory(){
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillNotСategory();
        NewsPage.fillTitle();
        NewsPage.fillDatePublication();
        NewsPage.fillTime();
        NewsPage.fillDescription();
        NewsPage.saveNews();
        NewsPage.saveNews();
        onView(withText(R.string.empty_fields))
                .inRoot(withDecorView(Matchers.not(decorView)));
    }


    @Test
    @DisplayName("Проверка работы фильтра")
    @Description("При фильтре новостей по полю Категория в списке новостей отображаются только новости выбранной категории")
    public void shouldFilterNewsCategory() {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillСategory();
        NewsPage.fillTitle();
        NewsPage.fillDatePublication();
        NewsPage.fillTime();
        NewsPage.fillDescription();
        NewsPage.saveNews();
        NewsPage.checkCreatedNews();
        MenuPage.goNews();
        NewsPage.filterNews();
        NewsPage.checkCreatedNews();
        NewsPage.deleteNews();
    }

    @Test
    @DisplayName("Редактирование новости")
    @Description("У новости в поле Заголовок отображается отредактированный текст")
    public void shouldChangeNews() {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillСategory();
        NewsPage.fillTitle();
        NewsPage.fillDatePublication();
        NewsPage.fillTime();
        NewsPage.fillDescription();
        NewsPage.saveNews();
        NewsPage.checkCreatedNews();
        MenuPage.goNews();
        NewsPage.filterNews();
        NewsPage.updateTitleNews();
        MenuPage.goNews();
        NewsPage.filterNews();
        NewsPage.checkEditNews();
        NewsPage.deleteEditNews();
    }
    @Test
    @DisplayName("Удаление новости")
    @Description("Удаленная новость не отобржается")
    public void shouldDeleteNews() {
        MenuPage.goNews();
        NewsPage.creationOfNews();
        NewsPage.fillСategory();
        NewsPage.fillTitle();
        NewsPage.fillDatePublication();
        NewsPage.fillTime();
        NewsPage.fillDescription();
        NewsPage.saveNews();
        NewsPage.checkCreatedNews();
        MenuPage.goNews();
        NewsPage.filterNews();
        NewsPage.deleteNews();

        MenuPage.goNews();
        NewsPage.checkDeleteNews();
    }
}