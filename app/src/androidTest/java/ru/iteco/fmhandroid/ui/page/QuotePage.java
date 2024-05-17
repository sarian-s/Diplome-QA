package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.Quotes;

public class QuotePage {

    public static void viewQuote() {// просмотр цитаты и её описания
        Allure.step("Отображение цитаты");
        Quotes.missionTitleText.check(matches(isDisplayed()));
        Quotes.missionTitleText.perform(click());
    }

    public static void checkQuoteDisplay() { //проверка отображения цитаты и описания
        Allure.step("Отображение описаниятцитаты");
        Quotes.descriptionText.check(matches(isDisplayed()));
    }
}
