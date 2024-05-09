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
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;

@RunWith(AllureAndroidJUnit4.class)
//тестирование Авторизации
public class AuthorizationTest {

    @Rule   // указываем какое приложение будем запускать.
    public ActivityTestRule<AppActivity> activityTestRule = //Cмотрим activity в AndroidManifest.xml
            new ActivityTestRule<>(AppActivity.class);

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

    String validLogin = "login2";
    String validPassword = "password2";
    String invalidLogin = "login200";
    String invalidPassword = "password200";

    @Before
    public void sleep() throws InterruptedException {
        Thread.sleep(5000);
        try {
            AuthorizationPage.isAuthorizationWindow();
        } catch (NoMatchingViewException e) {
            MenuPage.logOut();
        }
    }

    @Test
    @DisplayName("Авторизация с валидными данными")
    public void shouldLogInWithValidData() throws InterruptedException {
        AuthorizationPage.logIn(validLogin, validPassword);
        Thread.sleep(1000);
        MenuPage.checkTradeMark();
    }

    @Test
    @DisplayName("Авторизация с неверным логином")
    public void shouldLogInWithInValidData() throws InterruptedException {
        AuthorizationPage.logIn(invalidLogin, validPassword);
        AuthorizationPage.isAuthorizationWindow();
        Thread.sleep(1000);
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(Matchers.not(decorView)))

                .check(matches(withText(R.string.empty_login_or_password)))
                .check(matches(isDisplayed()));
    } @Test
    @DisplayName("Авторизация с неверным паролем")
    public void shouldPasswordWithInValidData() throws InterruptedException {
        AuthorizationPage.logIn(validLogin, invalidPassword);
        AuthorizationPage.isAuthorizationWindow();
        Thread.sleep(1000);
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(Matchers.not(decorView)))

                .check(matches(withText(R.string.empty_login_or_password)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Авторизация с пустыми полями логина и пароля")
    public void shouldTryLogInWithEmptyField() throws InterruptedException {
        AuthorizationPage.clickInButton();
        AuthorizationPage.isAuthorizationWindow();
        Thread.sleep(1000);
        onView(withText(R.string.empty_login_or_password))
                .inRoot(withDecorView(Matchers.not(decorView)))

                .check(matches(withText(R.string.empty_login_or_password)))
                .check(matches(isDisplayed()));
    }
}