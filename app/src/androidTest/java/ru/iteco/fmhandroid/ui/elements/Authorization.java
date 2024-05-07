package ru.iteco.fmhandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class Authorization {

    public static ViewInteraction authorization = onView(withText(R.string.authorization));
    public static ViewInteraction loginInput = onView(withHint("Логин")); // ожидаем появление нужного элемента
    public static ViewInteraction passwordInput = onView(withHint("Пароль"));

    //не работают при вводе логина и пароля - ошибка
    //public static ViewInteraction loginInput = onView(withText(R.string.login));
    //public static ViewInteraction passwordInput = onView(withText(R.string.password));
    public static ViewInteraction signInButton = onView(withText(R.string.sign_in));

}
