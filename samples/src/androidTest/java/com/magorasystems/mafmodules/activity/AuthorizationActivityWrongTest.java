package com.magorasystems.mafmodules.activity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.magorasystems.mafmodules.authmodule.R;
import com.magorasystems.mafmodules.dagger.module.MockAuthNetworkWrongModule;
import com.magorasystems.mafmodules.dagger.rules.DaggerAuthComponentRule;
import com.magorasystems.mafmodules.idle.ElapsedTimeIdlingResource;
import com.magorasystems.mafmodules.ui.activity.AuthorizationActivityImpl;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthorizationActivityWrongTest {

    public static final long WAIT = 3 * 1000;

    @Rule
    public DaggerAuthComponentRule daggerMockRule =
            new DaggerAuthComponentRule(new MockAuthNetworkWrongModule());


    @Rule
    public ActivityTestRule<AuthorizationActivityImpl> authorizationActivityActivityTestRule =
            new ActivityTestRule<>(AuthorizationActivityImpl.class, false, false);

    @Test
    public void testError() {
        authorizationActivityActivityTestRule.launchActivity(null);
        IdlingPolicies.setMasterPolicyTimeout(WAIT * 60 * 60 * 60 * 2, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(WAIT * 60 * 60 * 60 * 2, TimeUnit.MILLISECONDS);
        IdlingResource idlingResource = new ElapsedTimeIdlingResource(WAIT);
        onView(withId(R.id.text_email))
                .check(matches(isDisplayed()))
                .perform(clearText())
                .perform(typeText("test@mail.ru"))
                .perform(ViewActions.pressImeActionButton());
        onView(withId(R.id.text_password))
                .check(matches(isDisplayed()))
                .perform(clearText())
                .perform(typeText("1234567"))
                .perform(ViewActions.pressImeActionButton());
        onView(withId(R.id.button_sign_in))
                .check(matches(isEnabled()))
                .perform(click());
        Espresso.registerIdlingResources(idlingResource);
        onView(withText(android.R.string.ok))
                .check(matches(isDisplayed()));
        Espresso.unregisterIdlingResources(idlingResource);

    }
}
