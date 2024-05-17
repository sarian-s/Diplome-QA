package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.ui.date.Data;


public class Quotes {  //Цитаты
    public static ViewInteraction missionTitleText = onView(withText(Data.missionTitle));
    public static ViewInteraction descriptionText = onView(withText(Data.missionDescription));

}
