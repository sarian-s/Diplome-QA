package ru.iteco.fmhandroid.ui.date;

import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.rules.Timeout;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import java.util.concurrent.TimeoutException;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;

public class DataHelper {
    private static UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    public static void shouldViewPrivacyPolicy() { // Проверка открытия ссылки
        MenuPage.goToAboutApp();
        onView(withId(R.id.about_privacy_policy_value_text_view)) //  id ссылка
                .perform(click());

        // Ожидаем, пока браузер откроется
        uiDevice.wait(Until.hasObject(By.pkg("com.android.chrome")), Data.TimeOut); // "com.android.chrome" пакет вашего браузера

        // Проверяем, что открыта нужная страница (это может быть сложно, так как UiAutomator не предназначен для работы с WebView)
        // Вместо этого можно проверить заголовок страницы или другой видимый элемент на странице
        // Например, проверяем заголовок страницы

        try {// Не нашел другого способа для задержки
            Thread.sleep(4000); // Задержка в 2 секунды (2000 миллисекунд)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String title = uiDevice.findObject(By.clazz("android.widget.TextView")).getText();

        assert title.contains(Data.HeaderPrivacyPolicy);// ожидаемый заголовок страницы
    }
    public static void sshouldViewUserAgreement() {
        MenuPage.goToAboutApp();
        onView(withId(R.id.about_terms_of_use_value_text_view))
                .perform(click());

        uiDevice.wait(Until.hasObject(By.pkg("com.android.chrome")), Data.TimeOut);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String title = uiDevice.findObject(By.clazz("android.widget.TextView")).getText();

        assert title.contains(Data.HeaderAgreement);
    }

    public static boolean isDisplayedSwipe(ViewInteraction locator, int recycler, boolean finishSwipe) {
        try {
            locator.check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException ignored) {
        }
        boolean invisible = true;
        int n = 1;
        while (invisible) {
            try {

                if (recycler == 3) {
                    onView(allOf(withId(R.id.news_list_recycler_view), isDisplayed())).perform(actionOnItemAtPosition(n, swipeUp()));
                }
            } catch (PerformException e) {
                return false;
            }
            try {
                locator.check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));//.check(matches(isDisplayed()));
                invisible = false;
            } catch (NoMatchingViewException ignored) {
            }
            n++;
            if (!invisible & finishSwipe) {
                try {

                } catch (PerformException e) {
                    return false;
                }
            }
            if (n > 2000) {
                return false;
            }
            SystemClock.sleep(500);
        }
        return true;
    }

    public static void elementWaiting(Matcher matcher, int millis) {
        onView(isRoot()).perform(waitForElement(matcher, millis));
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }
    public static ViewAction waitForElement(final Matcher matcher, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with attribute <" + matcher + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = matcher;

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        try {
                            if (viewMatcher.matches(child)) {
                                return;
                            }
                        } catch (NoMatchingViewException e) {
                        }

                        uiController.loopMainThreadForAtLeast(100);
                    }

                }
                while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }
    public static void timeout() {
        onView(isRoot()).perform(waitForElement(withId(R.id.container_custom_app_bar_include_on_fragment_main), 5000));
        try {
            AuthorizationPage.isAuthorizationWindow();
        } catch (NoMatchingViewException e) {
            MenuPage.logOut();
        }
    }
    public static void authorizedUser() {//авторизованный пользователь
        elementWaiting(withId(R.id.main_menu_image_button), 6000);
        try {
            AuthorizationPage.isAuthorizationWindow();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationPage.logIn(Data.validLogin, Data.validPassword);
    }
    public static void logOut() {
        onView(isRoot()).perform(waitForElement(withId(R.id.container_custom_app_bar_include_on_fragment_main), 5000));
        try {
            AuthorizationPage.isAuthorizationWindow();
        } catch (NoMatchingViewException e) {
            MenuPage.logOut();
        }
    }

}
