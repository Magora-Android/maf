package com.magorasystems.mafmodules.activity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.dagger.module.MockSocialNetworkModule;
import com.magorasystems.mafmodules.dagger.rules.DaggerSocialComponentRule;
import com.magorasystems.mafmodules.idle.ElapsedTimeIdlingResource;
import com.magorasystems.mafmodules.ui.activity.SocialAuthorizationActivity;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SocialActivityTest {

    public static final long WAIT = 3 * 1000;

    @Rule
    public DaggerSocialComponentRule daggerSocialComponentRule =
            new DaggerSocialComponentRule(new MockSocialNetworkModule());

    @Rule
    public ActivityTestRule<SocialAuthorizationActivity> activityTestRule =
            new ActivityTestRule<>(SocialAuthorizationActivity.class, false, false);

    @Test
    public void testOnCreate() throws Exception {
        activityTestRule.launchActivity(null);
        IdlingPolicies.setMasterPolicyTimeout(WAIT * 60 * 60 * 60 * 2, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(WAIT * 60 * 60 * 60 * 2, TimeUnit.MILLISECONDS);
        IdlingResource idlingResource = new ElapsedTimeIdlingResource(60 * 20 * WAIT);
        Espresso.registerIdlingResources(idlingResource);
        onView(withId(R.id.button_connect_vk))
                .check(matches(isEnabled()))
                .perform(click());
        Espresso.unregisterIdlingResources(idlingResource);
    }
}
