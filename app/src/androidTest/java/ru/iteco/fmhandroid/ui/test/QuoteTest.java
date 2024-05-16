package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.date.DataHelper.elementWaiting;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.date.DataHelper;
import ru.iteco.fmhandroid.ui.page.MenuPage;
import ru.iteco.fmhandroid.ui.page.QuotePage;

@RunWith(AllureAndroidJUnit4.class)

public class QuoteTest {
    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logIn() {
        DataHelper.authorizedUser();
    }

    @Test
    @DisplayName("Проверка просмотра цитат и их описаний")
    public void shouldGoQuotes() {
        elementWaiting(withId(R.id.main_menu_image_button), 2000);
        MenuPage.goQuotes();
        QuotePage.viewQuote();
        QuotePage.checkQuoteDisplay();
    }
}