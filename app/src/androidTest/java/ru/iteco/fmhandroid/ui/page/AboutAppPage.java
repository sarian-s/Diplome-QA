package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.data.DataHelper.GetElementText;

import io.qameta.allure.kotlin.Allure;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.AboutApp;
public class AboutAppPage {
    //Проверка отображения всей информации на странице О приложении
    public void checkAboutAppInfIsFull() {
        Allure.step("Проверка элементов экрана О приложении");
        GetElementText(AboutApp.versionValue).check(matches(isDisplayed()));
        GetElementText(AboutApp.privacyPolicyValue).check(matches(isDisplayed()));
        GetElementText(AboutApp.termsOfUseValue).check(matches(isDisplayed()));
        GetElementText(AboutApp.infoLabel).check(matches(isDisplayed()));
    }

    public void clickPrivacyPolicy() { // Проверка кликабельности ссылки Политики конфиденциальности
        Allure.step("Кликабельности ссылки Политики конфиденциальности");
        onView(withId(R.id.about_privacy_policy_value_text_view)).check(matches(isClickable()));
    }

    public void clickUserAgreement() { // Проверка кликабельности ссылки Пользовательского соглашения
        Allure.step("Кликабельности ссылки Пользовательского соглашения");
        onView(withId(R.id.about_terms_of_use_value_text_view)).check(matches(isClickable()));
    }
}
