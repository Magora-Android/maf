package com.magorasystems.mafmodules.activity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import com.magorasystems.mafmodules.authmodule.R;
import com.magorasystems.mafmodules.dagger.module.MockAuthNetworkModule;
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

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthorizationActivityTest {

    public static final long WAIT = 3 * 1000;

    /*@Rule
    public DaggerSampleMockRule daggerMockRule =
            new DaggerSampleMockRule(new MockAuthNetworkModule());*/

    @Rule
    public DaggerAuthComponentRule daggerAuthComponentRule = new DaggerAuthComponentRule(new MockAuthNetworkModule());


    @Rule
    public ActivityTestRule<AuthorizationActivityImpl> authorizationActivityActivityTestRule =
            new ActivityTestRule<>(AuthorizationActivityImpl.class, false, false);

    @Test
    public void testOnCreate() {
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
        Espresso.unregisterIdlingResources(idlingResource);
    }

}
