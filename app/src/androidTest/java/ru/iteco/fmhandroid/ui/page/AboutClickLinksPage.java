package ru.iteco.fmhandroid.ui.page;

import ru.iteco.fmhandroid.ui.date.DataHelper;

public class AboutClickLinksPage {
    public static void clickLinkPrivacyPolicy() { // Проверка перехода по ссылки Политики конфиденциальности
        DataHelper.shouldViewPrivacyPolicy();
    }

    public static void clickLinkUserAgreement() { // Проверка перехода по ссылки Пользовательского соглашения
        DataHelper.sshouldViewUserAgreement();
    }
}
