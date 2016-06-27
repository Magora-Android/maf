package com.magorasystems.mafmodules.network.tests;

import android.support.test.runner.AndroidJUnit4;

import com.magorasystems.mafmodules.dagger.module.MockProfileNetworkModule;
import com.magorasystems.mafmodules.dagger.rules.DaggerProfileComponentRule;
import com.magorasystems.mafmodules.model.UserProfile;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.observers.TestSubscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@RunWith(AndroidJUnit4.class)
public class ProfileNetworkTest {

    @Rule
    public DaggerProfileComponentRule daggerProfileComponentRule =
            new DaggerProfileComponentRule(new MockProfileNetworkModule(0));

    @Test
    public void testMyProfile() throws Exception {
        final TestSubscriber<UserProfile> testSubscriber = new TestSubscriber<>();
        daggerProfileComponentRule.getProfileProvider()
                .getMyProfile()
                .subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        Assert.assertNotNull(testSubscriber.getOnNextEvents().get(0));
    }
}
