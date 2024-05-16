package ru.iteco.fmhandroid.ui.test;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.date.DataHelper;
import ru.iteco.fmhandroid.ui.page.AboutAppPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;

@RunWith(AllureAndroidJUnit4.class)

public class AboutAppTest {

    @Rule // указываем какое приложение будем запускать
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logIn() {
        DataHelper.authorizedUser();
    }

    @Test
    @DisplayName("Проверяем информацию о приложении")
    public void shouldBeInfInAboutApp() throws InterruptedException {
        MenuPage.goToAboutApp();
        AboutAppPage.checkAboutAppInfIsFull();Thread.sleep(2000);
    }

    @Test
    @DisplayName("Кликабельность ссылки Политики конфиденциальности")
    public void shouldViewPrivacyPolicy() {
        MenuPage.goToAboutApp();
        AboutAppPage.clickPrivacyPolicy();

    }

    @Test
    @DisplayName("Кликабельность ссылки Пользовательское соглашение")
    public void shouldViewUserAgreement() {
        MenuPage.goToAboutApp();
        AboutAppPage.clickUserAgreement();
    }

}
