package ru.iteco.fmhandroid.ui.page;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.date.DataHelper;

public class AboutClickLinksPage {
    public static void clickLinkPrivacyPolicy() { // Проверка перехода по ссылки Политики конфиденциальности
        Allure.step("В браузере открыта страница Политики конфиденциальности");
        DataHelper.shouldViewPrivacyPolicy();
    }

    public static void clickLinkUserAgreement() { // Проверка перехода по ссылки Пользовательского соглашения
        Allure.step("В браузере открыта страница Пользовательского соглашения");

        DataHelper.sshouldViewUserAgreement();
    }
}
