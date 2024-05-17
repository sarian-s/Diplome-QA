package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class News {

    // Заголовок блока "Новости"
    public static ViewInteraction titleNews = onView(withText(R.string.news));
    // Кнопка редактирования новостей
    public static ViewInteraction buttonEditNews = onView(withId(R.id.edit_news_material_button));
    // Кнопка создание новости
    public static ViewInteraction buttonAddNews = onView(withId(R.id.add_news_image_view));

    //кнопка фильтр новостей
    public static ViewInteraction buttonFilterNews = onView(withId(R.id.filter_news_material_button));

    //галочка Не активна
    public static ViewInteraction buttonNotActive = onView(withId(R.id.filter_news_inactive_material_check_box));
    //дата для начала фильтра
    public static ViewInteraction dateStartFilter = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    //дата для окончания фильтра
    public static ViewInteraction dateEndFilter = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    //кнопка фильтровать
    public static ViewInteraction buttonFilter = onView(withId(R.id.filter_button));
    public static ViewInteraction titleNewsCreatWindow = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    //заполняем категорию
    public static ViewInteraction categoryNews = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction buttonShowingDropdownMenu = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction titleTextInputNews = onView(withId(R.id.news_item_title_text_input_edit_text));
    //заполнение даты
    public static ViewInteraction dateTextInputNews = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public static ViewInteraction okButton = onView(withId(android.R.id.button1));
    public static ViewInteraction cancelButton = onView(withText(R.string.cancel));
    //заполняем время
    public static ViewInteraction timeTextInputNews = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    //заполняем описание
    public static ViewInteraction descriptionTextInputNews = onView(withId(R.id.news_item_description_text_input_edit_text));
    //кнопка Сохранить
    public static ViewInteraction buttonSaveNews = onView(withId(R.id.save_button));
    public static ViewInteraction iconError = onView(withId(R.id.message));
    public static Matcher<View> editNews = withId(R.id.edit_news_item_image_view);

}
