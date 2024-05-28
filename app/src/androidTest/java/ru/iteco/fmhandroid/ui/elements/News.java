package ru.iteco.fmhandroid.ui.elements;

import ru.iteco.fmhandroid.R;

public class News {

    // Заголовок блока "Новости"
    public static int titleNews = R.string.news;
    // Кнопка редактирования новостей
    public static int buttonEditNews = R.id.edit_news_material_button;
    // Кнопка создание новости
    public static int buttonAddNews = R.id.add_news_image_view;

    //кнопка фильтр новостей
    public static int buttonFilterNews = R.id.filter_news_material_button;

    //галочка Не активна
    public static int buttonNotActive = R.id.filter_news_inactive_material_check_box;
    //дата для начала фильтра
    public static int dateStartFilter = R.id.news_item_publish_date_start_text_input_edit_text;
    //дата для окончания фильтра
    public static int dateEndFilter = R.id.news_item_publish_date_end_text_input_edit_text;
    //кнопка фильтровать
    public static int buttonFilter = R.id.filter_button;
    public static int titleNewsCreatWindow = R.id.custom_app_bar_sub_title_text_view;
    //заполняем категорию
    public static int categoryNews = R.id.news_item_category_text_auto_complete_text_view;
    public static int buttonShowingDropdownMenu = R.id.news_item_category_text_auto_complete_text_view;
    public static int titleTextInputNews = R.id.news_item_title_text_input_edit_text;
    //заполнение даты
    public static int dateTextInputNews = R.id.news_item_publish_date_text_input_edit_text;
    public static int okButton = android.R.id.button1;
    //заполняем время
    public static int timeTextInputNews = R.id.news_item_publish_time_text_input_edit_text;
    //заполняем описание
    public static int descriptionTextInputNews = R.id.news_item_description_text_input_edit_text;
    //кнопка Сохранить
    public static int buttonSaveNews = R.id.save_button;
    //public static ViewInteraction iconError = onView(withId(R.id.message));
    //public static Matcher<View> editNews1 = withId(R.id.edit_news_item_image_view);
    public static int editNews = R.id.edit_news_item_image_view;

}