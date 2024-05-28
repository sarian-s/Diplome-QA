package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isSystemAlertWindow;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.elements.Authorization;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;

@RunWith(AllureAndroidJUnit4.class)//тестирование Авторизации
public class AuthorizationTest {


    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
        public void setUp() {
        DataHelper dataHelper = new DataHelper();
        dataHelper.logOut();
    }

    @Test
    @DisplayName("Авторизация с валидными данными")
    @Description("При успешной авторизации происходит переход на главный экран приложения")
    public void shouldLogInWithValidData() {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        MenuPage menuPage = new MenuPage();
        authorizationPage.logIn(Data.validLogin, Data.validPassword);
        menuPage.checkTradeMark();
    }

    @Test
    @DisplayName("Авторизация с неверным логином")
    @Description("При вводе незарегистрированных значений логина и пароля отображается сообщение" +
            "Неверный логин или пароль")
    public void shouldLogInWithInValidData() {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        authorizationPage.logIn(Data.invalidLogin, Data.validPassword);
        authorizationPage.isAuthorizationWindow();
        onView(withText(Authorization.invalidMessage))
                .inRoot(isSystemAlertWindow())
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    @Description("При вводе незарегистрированных значений логина и пароля отображается сообщение " +
            "Неверный логин или пароль")
    public void shouldPasswordWithInValidData()  {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        authorizationPage.logIn(Data.validLogin, Data.invalidPassword);
        authorizationPage.isAuthorizationWindow();
        onView(withText(Authorization.invalidMessage))
                .inRoot(isSystemAlertWindow())
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Авторизация с пустыми полями логина и пароля")
    @Description("При авторизации с пустым логином и (или) паролем пользователю отображается сообщение " +
            "Логин и пароль не могут быть пустыми")
    public void shouldTryLogInWithEmptyField() {
        AuthorizationPage authorizationPage = new AuthorizationPage();
        authorizationPage.clickInButton();
        authorizationPage.isAuthorizationWindow();
        onView(withText(Authorization.invalidMessage1))
                .inRoot(isSystemAlertWindow())
                .check(matches(isDisplayed()));
    }
}