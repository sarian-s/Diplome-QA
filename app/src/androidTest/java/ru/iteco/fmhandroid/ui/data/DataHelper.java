package ru.iteco.fmhandroid.ui.data;

import android.view.View;

import org.hamcrest.Matcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import java.util.concurrent.TimeoutException;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.elements.Quotes;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MenuPage;

public class DataHelper {
    private UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    private UiDevice GetUiDevice()  {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }
    public static ViewInteraction GetElement(int id) {
        return onView(withId(id));
    }
    public static ViewInteraction GetElementText(int id) {
        return onView(withText(id));
    }
    public static ViewInteraction GetElementTextString(String id) {
        return onView(withText(id));
    }
    public static ViewInteraction GetElementHint(int id) {
        return onView(withHint(id));
    }
    public static Matcher<View> GetElementMatcher(int id) {
        return withId(id);
    }


    public void shouldViewPrivacyPolicy() {
        MenuPage menuPage = new MenuPage();
        menuPage.goToAboutApp();
        GetElement(Quotes.linkPrivacy).perform(click());

        uiDevice.wait(Until.hasObject(By.pkg("com.android.chrome")), Data.TimeOut);

        // Ожидание появления текста заголовка
        String expectedTitle = Data.HeaderPrivacyPolicy;
        boolean titleFound = false;
        long startTime = System.currentTimeMillis();
        long endTime = startTime + Data.TimeOut;

        while (!titleFound && System.currentTimeMillis() < endTime) {
            String title = uiDevice.findObject(By.clazz("android.widget.TextView")).getText();
            if (title != null && title.contains(expectedTitle)) {
                titleFound = true;
                assert title.contains(expectedTitle);
            }
        }

        if (!titleFound) {
            throw new AssertionError(Data.HeaderPrivacyPolicy);
        }
    }


public void shouldViewUserAgreement() {
    MenuPage menuPage = new MenuPage();
    menuPage.goToAboutApp();
    GetElement(Quotes.linkAgreement).perform(click());

    uiDevice.wait(Until.hasObject(By.pkg("com.android.chrome")), Data.TimeOut);

    // Ожидание появления текста заголовка
    String expectedTitle = Data.HeaderAgreement;
    boolean titleFound = false;
    long startTime = System.currentTimeMillis();
    long endTime = startTime + Data.TimeOut;

    while (!titleFound && System.currentTimeMillis() < endTime) {
        String title = uiDevice.findObject(By.clazz("android.widget.TextView")).getText();
        if (title != null && title.contains(expectedTitle)) {
            titleFound = true;
            assert title.contains(expectedTitle);
        }
    }

    if (!titleFound) {
        throw new AssertionError(Data.HeaderAgreement);
    }
}

    public static void elementWaiting(Matcher matcher, int millis) {
        onView(isRoot()).perform(waitForElement(matcher, millis));
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

    public void authorizedUser() {//авторизованный пользователь
        elementWaiting(withId(R.id.main_menu_image_button), 6000);
        try {
            AuthorizationPage authorizationPage = new AuthorizationPage();
            authorizationPage.isAuthorizationWindow();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationPage authorizationPage = new AuthorizationPage();
        authorizationPage.logIn(Data.validLogin, Data.validPassword);
    }
    public void logOut() {
        onView(isRoot()).perform(waitForElement(withId(R.id.container_custom_app_bar_include_on_fragment_main), 5000));
        try {
            AuthorizationPage authorizationPage = new AuthorizationPage();
            authorizationPage.isAuthorizationWindow();
        } catch (NoMatchingViewException e) {
            MenuPage menuPage = new MenuPage();
            menuPage.logOut();
        }
    }

}
