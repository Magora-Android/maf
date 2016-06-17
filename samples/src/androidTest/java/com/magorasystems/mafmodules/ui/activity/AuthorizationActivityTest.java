package com.magorasystems.mafmodules.ui.activity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;

import com.magorasystems.mafmodules.dagger.rules.WrongDaggerMockRule;
import com.magorasystems.mafmodules.idle.ElapsedTimeIdlingResource;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthorizationActivityTest {

    public static final long WAIT = 3 * 1000;

    @Rule public WrongDaggerMockRule daggerMockRule = new WrongDaggerMockRule();

    @Rule public ActivityTestRule<AuthorizationActivity> authorizationActivityActivityTestRule =
            new ActivityTestRule<>(AuthorizationActivity.class, false, false);


    @Test
    public void testOnCreate() {
        authorizationActivityActivityTestRule.launchActivity(null);
        IdlingPolicies.setMasterPolicyTimeout(WAIT * 60 * 60 * 60 * 2, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(WAIT * 60 * 60 * 60 * 2, TimeUnit.MILLISECONDS);
        IdlingResource idlingResource = new ElapsedTimeIdlingResource(WAIT * 60 * 60);
        Espresso.registerIdlingResources(idlingResource);
        Espresso.pressBack();
        Espresso.unregisterIdlingResources(idlingResource);
    }

}
