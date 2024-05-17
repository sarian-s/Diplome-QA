package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.Authorization;
public class AuthorizationPage { //Отображение окна авторизации
    public static void isAuthorizationWindow() {
        Authorization.authorization.check(matches(isDisplayed()));
    }
    public static void logIn(String login, String password) {//Выполняем вход в аккаунт
        Allure.step("Авторизация с заполненными полями логина и пароля существующем пользователем");
        Authorization.loginInput.perform(replaceText(login));
        Authorization.loginInput.check(matches(withText(login)));
        Authorization.passwordInput.perform(replaceText(password));
        Authorization.passwordInput.check(matches(withText(password)));
        Authorization.signInButton.perform(click());
    }
    public static void clickInButton() { //Кликаем на конпку Войти
        Allure.step("Авторизация с незаполненными полями логина и пароля");
        Authorization.signInButton.perform(click());
    }
}

