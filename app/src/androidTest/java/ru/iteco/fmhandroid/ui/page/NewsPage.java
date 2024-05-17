package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.date.Data;
import ru.iteco.fmhandroid.ui.date.DataHelper;
import ru.iteco.fmhandroid.ui.elements.News;

public class NewsPage {//создание, редактирование, удаление и фильтр новости

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private View decorView;
    @Before
    public void setUp() {
        activityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }
    public static void creationOfNews() {
        Allure.step("Переход на страницу создания новости");
        News.buttonEditNews.perform(click());
        News.buttonAddNews.check(matches(isDisplayed()));
        News.buttonAddNews.perform(click());
        News.titleNewsCreatWindow.check(matches(isDisplayed()));
    }
    public static void fillСategory( ) {// заполнение поля "Категория"
        Allure.step("Заполненное поле Категория");
        News.buttonShowingDropdownMenu.perform(click());
        News.buttonShowingDropdownMenu.perform(replaceText(Data.categoryFilledIn));
        News.buttonShowingDropdownMenu.check(matches(isDisplayed()));
    }
    public static void fillNotСategory( ) {// НЕ заполнено поле "Категория"
        Allure.step("Пустое поле Категория");
        News.buttonShowingDropdownMenu.perform(click());
        News.buttonShowingDropdownMenu.perform(replaceText(Data.categoryNotFilledIn));
        News.buttonShowingDropdownMenu.check(matches(isDisplayed()));
    }
    public static void fillTitle( ) {// заполнение поля "Заголовок"
        Allure.step("Заполненное поле Заголовок");
        News.titleTextInputNews.perform(replaceText(Data.titleFilledIn));
    }
    public static void fillDatePublication( ) {// заполнение поля "дата публикации"
        Allure.step("Заполненное поле дата публикации");
        News.dateTextInputNews.perform(click());
        News.okButton.perform(click());
    }
    public static void fillTime( ) {// заполнение поля "время"
        Allure.step("Заполненное поле время");
        News.timeTextInputNews.perform(click());
        News.okButton.perform(click());
    }
    public static void fillDescription( ) {// заполнение поля "описание"
        Allure.step("Заполненное поле Описание");
        News.descriptionTextInputNews.perform(replaceText(Data.descriptionFilledIn));
        News.descriptionTextInputNews.check(matches(withText(Data.descriptionFilledIn)));
    }
    public static void saveNews() {//Сохраняем новость — нажимаем на кнопку SAVE
        Allure.step("Сохранение созданной новости по клику на кнопку сохранить");
        News.buttonSaveNews.perform(click());
    }
    public static void filterNews() {//фильтруем Новости (после нажатия на кнопку редактировать)
        Allure.step("Отображается новость с выбранной Категорией");
        News.buttonEditNews.perform(click());
        News.buttonFilterNews.perform(click());
        News.categoryNews.perform(replaceText(Data.categoryFilledIn));
        News.dateStartFilter.perform(click());
        News.okButton.perform(click());
        News.dateEndFilter.perform(click());
        News.okButton.perform(click());
        News.buttonNotActive.perform(click());
        News.buttonFilter.perform(click());
    }
    public static void deleteNews() { //удаление новости
        Allure.step("Удаляем созданную новость");
        onView(allOf(withId(R.id.delete_news_item_image_view), hasSibling(withText(Data.titleFilledIn)))).perform(click());
        News.okButton.check(matches(isDisplayed()));
        News.okButton.perform(click());// подтверждаем удаление новости
    }
    public static void deleteEditNews() {//удаление отредактированной новости
        Allure.step("Удаляем отредактированную новость");
        onView(allOf(withId(R.id.delete_news_item_image_view), hasSibling(withText(Data.newTitleEdit)))).perform(click());
        News.okButton.check(matches(isDisplayed()));
        News.okButton.perform(click());// подтверждаем удаление новости
    }

    public static void checkCreatedNews() {//проверка созданной новости
        Allure.step("Отображение созданной новости");
        DataHelper.isDisplayedSwipe(onView(withText(Data.titleFilledIn)), 3, true);
    }
    public static void checkEditNews() {//проверка отредактированной  новости
        Allure.step("Отображение отредактированной новости");
        DataHelper.isDisplayedSwipe(onView(withText(Data.newTitleEdit)), 3, true);
    }
    public static void checkDeleteNews() { //проверка удаления новости
        Allure.step("Удаленная новость не отображается");
        onView(withText(Data.titleFilledIn)).check(doesNotExist());
    }
    public static void updateTitleNews() {//изменение заголовка новости
        Allure.step("Редактирование текста в поле заголовок у Новости");
        onView(allOf(News.editNews, hasSibling(withText(Data.titleFilledIn)))).perform(click());
        News.titleTextInputNews.perform(replaceText(Data.newTitleEdit)); //меняем текст заголовка
        News.buttonSaveNews.perform(click()); //нажимаем на кнопку сохранить
    }
}