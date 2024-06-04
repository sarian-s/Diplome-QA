package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.page.AboutClickLinksPage;

@RunWith(AndroidJUnit4.class)
public class AboutClickingLinksTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void main() {DataHelper dataHelper = new DataHelper();
        dataHelper.authorizedUser();
    }
    AboutClickLinksPage aboutClickLinksPage = new AboutClickLinksPage();

    @Test
    @DisplayName("Переход по ссылке Политики конфиденциальности")
    @Description("По клику на ссылку Политики конфиденциальности открывается страница Политики конфиденциальности в браузере")
    public void shouldViewPrivacyPolicy() {
        aboutClickLinksPage.clickLinkPrivacyPolicy();
    }

    @Test
    @DisplayName("Переход по ссылке Пользовательское соглашение")
    @Description("По клику на ссылку Пользовательское соглашение открывается страница Пользовательское соглашение в браузере")

    public void sshouldViewUserAgreement() {
        aboutClickLinksPage.clickLinkUserAgreement();
    }

}