package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class Menu {

    //логотип приложения
    public static ViewInteraction tradeMark = onView(withId(R.id.trademark_image_view));

    // Выход из приложения
    public static ViewInteraction authorizationButton = onView(withId(R.id.authorization_image_button));
    public static ViewInteraction logOutButton = onView(withText(R.string.log_out));
    // Меню
    public static ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    public static ViewInteraction newsOfMenu = onView(withText(R.string.news));
    public static ViewInteraction aboutOfMenu = onView(withText(R.string.about));

    // Переход к блоку цитат о хосписе
    public static ViewInteraction goQuotesButton = onView(withId(R.id.our_mission_image_button));

}
