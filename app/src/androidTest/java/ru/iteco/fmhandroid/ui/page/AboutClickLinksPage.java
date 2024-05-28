package ru.iteco.fmhandroid.ui.page;

import ru.iteco.fmhandroid.ui.data.DataHelper;

import io.qameta.allure.kotlin.Allure;

public class AboutClickLinksPage {
    public void clickLinkPrivacyPolicy() {
        Allure.step("В браузере открыта страница Политики конфиденциальности");
        DataHelper dataHelper = new DataHelper();// Проверка перехода по ссылки Политики конфиденциальности
        dataHelper.shouldViewPrivacyPolicy();
    }

    public void clickLinkUserAgreement() {
        Allure.step("В браузере открыта страница Пользовательского соглашения");
        DataHelper dataHelper = new DataHelper();// Проверка перехода по ссылки Пользовательского соглашения
        dataHelper.shouldViewUserAgreement();
    }
}
