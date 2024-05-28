package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static ru.iteco.fmhandroid.ui.data.DataHelper.GetElementTextString;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.elements.Quotes;

public class QuotePage {

    public void viewQuote() {// просмотр цитаты и её описания
        Allure.step("Отображение цитаты");
        GetElementTextString(Quotes.missionTitleText).check(matches(isDisplayed()));
        GetElementTextString(Quotes.missionTitleText).perform(click());
    }

    public void checkQuoteDisplay() { //проверка отображения цитаты и описания
        Allure.step("Отображение описаниятцитаты");
        GetElementTextString(Quotes.descriptionText).check(matches(isDisplayed()));
    }
}
