package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.page.AboutAppPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;

@RunWith(AllureAndroidJUnit4.class)

public class AboutAppTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logIn() {
        DataHelper dataHelper = new DataHelper();
        dataHelper.authorizedUser();
    }

    @Test
    @DisplayName("Проверяем информацию о приложении")
    @Description("На странице отображаются все элементы")
    public void shouldBeInfInAboutApp() {
        MenuPage menuPage = new MenuPage();
        AboutAppPage page = new AboutAppPage();

        menuPage.goToAboutApp();
        page.checkAboutAppInfIsFull();
    }

    @Test
    @DisplayName("Кликабельность ссылки Политики конфиденциальности")
    @Description("Ссылка Политики конфиденциальности кликабельна")
    public void shouldViewPrivacyPolicy() {
        MenuPage menuPage = new MenuPage();
        AboutAppPage page = new AboutAppPage();

        menuPage.goToAboutApp();
        page.clickPrivacyPolicy();

    }

    @Test
    @DisplayName("Кликабельность ссылки Пользовательское соглашение")
    @Description("Ссылка Пользовательское соглашение кликабельна")

    public void shouldViewUserAgreement() {
        MenuPage menuPage = new MenuPage();
        AboutAppPage aboutAppPage = new AboutAppPage();

        menuPage.goToAboutApp();
        aboutAppPage.clickUserAgreement();
    }

}
