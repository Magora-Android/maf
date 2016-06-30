package com.magorasystems.mafmodules.activity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.dagger.module.MockProfileNetworkModule;
import com.magorasystems.mafmodules.dagger.rules.DaggerProfileComponentRule;
import com.magorasystems.mafmodules.idle.ElapsedTimeIdlingResource;
import com.magorasystems.mafmodules.ui.activity.ProfileActivity;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class ProfileActivityTest {

    public static final long WAIT = 3 * 1000;

    @Rule
    public DaggerProfileComponentRule daggerProfileComponentRule =
            new DaggerProfileComponentRule(new MockProfileNetworkModule(1));

    @Rule
    public ActivityTestRule<ProfileActivity> profileActivityActivityTestRule =
            new ActivityTestRule<>(ProfileActivity.class, false, false);

    @Test
    public void testOnCreate() throws Exception {
        profileActivityActivityTestRule.launchActivity(null);
        IdlingPolicies.setMasterPolicyTimeout(WAIT * 60 * 60 * 60 * 2, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(WAIT * 60 * 60 * 60 * 2, TimeUnit.MILLISECONDS);
        IdlingResource idlingResource = new ElapsedTimeIdlingResource(60 * WAIT);
        onView(withId(R.id.button_edit_profile))
                .check(matches(isEnabled()))
                .perform(click());
        Espresso.registerIdlingResources(idlingResource);
        Espresso.unregisterIdlingResources(idlingResource);

    }
}
