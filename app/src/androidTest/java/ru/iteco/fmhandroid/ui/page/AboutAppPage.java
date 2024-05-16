package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.AboutApp;
public class AboutAppPage {
    //Проверка отображения всей информации на странице О приложении
    public static void checkAboutAppInfIsFull() {
        AboutApp.versionValue.check(matches(isDisplayed()));
        AboutApp.privacyPolicyValue.check(matches(isDisplayed()));
        AboutApp.termsOfUseValue.check(matches(isDisplayed()));
        AboutApp.infoLabel.check(matches(isDisplayed()));
    }

    public static void clickPrivacyPolicy() { // Проверка кликабельности ссылки Политики конфиденциальности
        onView(withId(R.id.about_privacy_policy_value_text_view)).check(matches(isClickable()));
    }

    public static void clickUserAgreement() { // Проверка кликабельности ссылки Пользовательского соглашения
        onView(withId(R.id.about_terms_of_use_value_text_view)).check(matches(isClickable()));
    }
}
