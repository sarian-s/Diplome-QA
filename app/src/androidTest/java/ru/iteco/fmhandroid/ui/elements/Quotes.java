package ru.iteco.fmhandroid.ui.elements;

import androidx.test.espresso.ViewInteraction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class Quotes {
    //Цитаты
    public static ViewInteraction missionTitleText = onView(withText("«Хоспис для меня - это то, каким должен быть мир.\""));
    public static ViewInteraction descriptionText = onView(withText("\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер"));

}
