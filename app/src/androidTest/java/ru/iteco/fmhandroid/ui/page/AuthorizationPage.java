package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.data.DataHelper.GetElementHint;
import static ru.iteco.fmhandroid.ui.data.DataHelper.GetElementText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.Authorization;
public class AuthorizationPage { //Отображение окна авторизации
    public  void isAuthorizationWindow() {
        Allure.step("Авторизация с заполненными полями логина и пароля");
        GetElementText(Authorization.authorization).check(matches(isDisplayed()));
    }
    public void logIn(String login, String password) {//Выполняем вход в аккаунт
        Allure.step("Авторизация с заполненными полями логина и пароля");
        GetElementHint(Authorization.loginInput).perform(replaceText(login));
        GetElementHint(Authorization.loginInput).check(matches(withText(login)));
        GetElementHint(Authorization.passwordInput).perform(replaceText(password));
        GetElementHint(Authorization.passwordInput).check(matches(withText(password)));
        GetElementText(Authorization.signInButton).perform(click());
    }
    public void clickInButton() { //Кликаем на конпку Войти
        Allure.step("Авторизация с незаполненными полями логина и пароля");
        GetElementText(Authorization.signInButton).perform(click());
    }
}

