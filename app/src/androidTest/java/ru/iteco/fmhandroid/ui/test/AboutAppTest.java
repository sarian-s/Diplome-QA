package ru.iteco.fmhandroid.ui.test;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AboutAppPage;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;

@RunWith(AllureAndroidJUnit4.class)

public class AboutAppTest {

    @Rule // указываем какое приложение будем запускать
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
        AuthorizationPage.logIn("login2", "password2");
    }
    @Before
    public void setUp() {
        // Инициализация Intents перед началом тестов
        Intents.init();
    }

    @After
    public void tearDown() {
        // Освобождение ресурсов Intents после завершения тестов
        Intents.release();
    }

    @Test
    @DisplayName("Проверяем информацию о приложении")
    public void shouldBeInfInAboutApp() {
        MenuPage.goToAboutApp();
        AboutAppPage.checkAboutAppInfIsFull();
    }

    @Test
    @DisplayName("Переход по ссылке Политики конфиденциальности")
    public void shouldViewPrivacyPolicy() {
        MenuPage.goToAboutApp();
        AboutAppPage.clickPrivacyPolicy();
        AboutAppPage.checkPrivacyPolicy();
    }

    @Test
    @DisplayName("Переход по ссылке Пользовательское соглашение")
    public void shouldViewUserAgreement() {
        MenuPage.goToAboutApp();
        AboutAppPage.clickUserAgreement();
        AboutAppPage.checkTermsOfUse();
    }

}
