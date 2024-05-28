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

import static ru.iteco.fmhandroid.ui.data.DataHelper.GetElement;
import static ru.iteco.fmhandroid.ui.data.DataHelper.GetElementMatcher;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Data;
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
    public void creationOfNews() {
        Allure.step("Переход на страницу создания новости");
        GetElement(News.buttonEditNews).perform(click());
        GetElement(News.buttonAddNews).check(matches(isDisplayed()));
        GetElement(News.buttonAddNews).perform(click());
        GetElement(News.titleNewsCreatWindow).check(matches(isDisplayed()));
    }
    public void fillСategory( ) {// заполнение поля "Категория"
        Allure.step("Заполненное поле Категория");
        GetElement(News.buttonShowingDropdownMenu).perform(click());
        GetElement(News.buttonShowingDropdownMenu).perform(replaceText(Data.categoryFilledIn));
        GetElement(News.buttonShowingDropdownMenu).check(matches(isDisplayed()));
    }
    public void fillNotСategory( ) {// НЕ заполнено поле "Категория"
        Allure.step("Пустое поле Категория");
        GetElement(News.buttonShowingDropdownMenu).perform(click());
        GetElement(News.buttonShowingDropdownMenu).perform(replaceText(Data.categoryNotFilledIn));
        GetElement(News.buttonShowingDropdownMenu).check(matches(isDisplayed()));
    }
    public void fillTitle( ) {// заполнение поля "Заголовок"
        Allure.step("Заполненное поле Заголовок");
        GetElement(News.titleTextInputNews).perform(replaceText(Data.titleFilledIn));
    }
    public void fillDatePublication( ) {// заполнение поля "дата публикации"
        Allure.step("Заполненное поле дата публикации");
        GetElement(News.dateTextInputNews).perform(click());
        GetElement(News.okButton).perform(click());
    }
    public void fillTime( ) {// заполнение поля "время"
        Allure.step("Заполненное поле время");
        GetElement(News.timeTextInputNews).perform(click());
        GetElement(News.okButton).perform(click());
    }
    public void fillDescription( ) {// заполнение поля "описание"
        Allure.step("Заполненное поле Описание");
        GetElement(News.descriptionTextInputNews).perform(replaceText(Data.descriptionFilledIn));
        GetElement(News.descriptionTextInputNews).check(matches(withText(Data.descriptionFilledIn)));
    }
    public void saveNews() {//Сохраняем новость — нажимаем на кнопку SAVE
        Allure.step("Сохранение созданной новости по клику на кнопку сохранить");
        GetElement(News.buttonSaveNews).perform(click());
    }
    public void filterNews() {//фильтруем Новости (после нажатия на кнопку редактировать)
        Allure.step("Отображается новость с выбранной Категорией");
        GetElement(News.buttonEditNews).perform(click());
        GetElement(News.buttonFilterNews).perform(click());
        GetElement(News.categoryNews).perform(replaceText(Data.categoryFilledIn));
        GetElement(News.dateStartFilter).perform(click());
        GetElement(News.okButton).perform(click());
        GetElement(News.dateEndFilter).perform(click());
        GetElement(News.okButton).perform(click());
        GetElement(News.buttonNotActive).perform(click());
        GetElement(News.buttonFilter).perform(click());
    }
    public void deleteNews() { //удаление новости
        Allure.step("Удаляем созданную новость");
        onView(allOf(withId(R.id.delete_news_item_image_view), hasSibling(withText(Data.titleFilledIn)))).perform(click());
        GetElement(News.okButton).check(matches(isDisplayed()));
        GetElement(News.okButton).perform(click());// подтверждаем удаление новости
    }
    public void deleteEditNews() {//удаление отредактированной новости
        Allure.step("Удаляем отредактированную новость");
        onView(allOf(withId(R.id.delete_news_item_image_view), hasSibling(withText(Data.newTitleEdit)))).perform(click());
        GetElement(News.okButton).check(matches(isDisplayed()));
        GetElement(News.okButton).perform(click());// подтверждаем удаление новости
    }

    public void checkCreatedNews() {//проверка созданной новости
        Allure.step("Отображение созданной новости");
        //---DataHelper.isDisplayedSwipe(onView(withText(Data.titleFilledIn)), 3, true);
    }
    public void checkEditNews() {//проверка отредактированной  новости
        Allure.step("Отображение отредактированной новости");
        //DataHelper.isDisplayedSwipe(onView(withText(Data.newTitleEdit)), 3, true);
    }
    public void checkDeleteNews() { //проверка удаления новости
        Allure.step("Удаленная новость не отображается");
        onView(withText(Data.titleFilledIn)).check(doesNotExist());
    }
    public void updateTitleNews() {//изменение заголовка новости
        Allure.step("Редактирование текста в поле заголовок у Новости");
        onView(allOf(GetElementMatcher(News.editNews), hasSibling(withText(Data.titleFilledIn)))).perform(click());
        GetElement(News.titleTextInputNews).perform(replaceText(Data.newTitleEdit)); //меняем текст заголовка
        GetElement(News.buttonSaveNews).perform(click()); //нажимаем на кнопку сохранить
    }
}