package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.date.DataHelper;
import ru.iteco.fmhandroid.ui.page.AboutClickLinksPage;

@RunWith(AndroidJUnit4.class)
public class AboutClickingLinksTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void main() {
        DataHelper.authorizedUser();
    }

    @Test
    @DisplayName("Переход по ссылке Политики конфиденциальности")
    public void shouldViewPrivacyPolicy() {
        AboutClickLinksPage.clickLinkPrivacyPolicy();
    }

    @Test
    @DisplayName("Переход по ссылке Пользовательское соглашение")
    public void sshouldViewUserAgreement() {
        AboutClickLinksPage.clickLinkUserAgreement();
    }

}