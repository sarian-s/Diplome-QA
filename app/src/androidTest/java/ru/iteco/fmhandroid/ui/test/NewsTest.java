package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.page.MenuPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@RunWith(AllureAndroidJUnit4.class)

public class NewsTest {

    @Before
    public void setUp() {DataHelper dataHelper = new DataHelper();
        dataHelper.authorizedUser();
    }

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    MenuPage menuPage = new MenuPage();
    NewsPage newsPage = new NewsPage();

    @Test
    @DisplayName("Создание новости и удаление")
    @Description("При заполнении всех полей новость создается и отображается, после удаления не отображается")
    public void shouldCreateNews() {
        menuPage.goNews();
        newsPage.creationOfNews();
        newsPage.fillСategory();
        newsPage.fillTitle();
        newsPage.fillDatePublication();
        newsPage.fillTime();
        newsPage.fillDescription();
        newsPage.saveNews();
        newsPage.checkCreatedNews();
        menuPage.goNews();
        newsPage.filterNews();
        newsPage.deleteNews();

    }
    @Test
    @DisplayName("Создание новости без категории")
    @Description("Пользователю отображается сообщение " + "Заполните пустые поля")
    public void shouldErrorNotCategory(){
        menuPage.goNews();
        newsPage.creationOfNews();
        newsPage.fillNotСategory();
        newsPage.fillTitle();
        newsPage.fillDatePublication();
        newsPage.fillTime();
        newsPage.fillDescription();
        newsPage.saveNews();
        newsPage.displayingMessage();
    }

    @Test
    @DisplayName("Проверка работы фильтра")
    @Description("При фильтре новостей по полю Категория в списке новостей отображаются только новости выбранной категории")
    public void shouldFilterNewsCategory() {
        menuPage.goNews();
        newsPage.creationOfNews();
        newsPage.fillСategory();
        newsPage.fillTitle();
        newsPage.fillDatePublication();
        newsPage.fillTime();
        newsPage.fillDescription();
        newsPage.saveNews();
        newsPage.checkCreatedNews();
        menuPage.goNews();
        newsPage.filterNews();
        newsPage.checkCreatedNews();
        newsPage.deleteNews();
    }

    @Test
    @DisplayName("Редактирование новости")
    @Description("У новости в поле Заголовок отображается отредактированный текст")
    public void shouldChangeNews() {
        menuPage.goNews();
        newsPage.creationOfNews();
        newsPage.fillСategory();
        newsPage.fillTitle();
        newsPage.fillDatePublication();
        newsPage.fillTime();
        newsPage.fillDescription();
        newsPage.saveNews();
        newsPage.checkCreatedNews();
        menuPage.goNews();
        newsPage.filterNews();
        newsPage.updateTitleNews();
        menuPage.goNews();
        newsPage.filterNews();
        newsPage.checkEditNews();
        newsPage.deleteEditNews();
    }
    @Test
    @DisplayName("Удаление новости")
    @Description("Удаленная новость не отобржается")
    public void shouldDeleteNews() {
        menuPage.goNews();
        newsPage.creationOfNews();
        newsPage.fillСategory();
        newsPage.fillTitle();
        newsPage.fillDatePublication();
        newsPage.fillTime();
        newsPage.fillDescription();
        newsPage.saveNews();
        newsPage.checkCreatedNews();
        menuPage.goNews();
        newsPage.filterNews();
        newsPage.deleteNews();
        menuPage.goNews();
        newsPage.checkDeleteNews();
    }
}