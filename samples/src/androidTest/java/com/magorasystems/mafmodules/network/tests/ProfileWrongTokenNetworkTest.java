package com.magorasystems.mafmodules.network.tests;

import android.support.test.runner.AndroidJUnit4;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.rx.TestCoreScheduler;
import com.magorasystems.mafmodules.dagger.module.MockProfileNetworkModule;
import com.magorasystems.mafmodules.dagger.rules.DaggerProfileComponentRule;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.network.RestApiTestSubscriber;
import com.magorasystems.mafmodules.network.exception.NetworkErrorException;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseCodes;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@RunWith(AndroidJUnit4.class)
public class ProfileWrongTokenNetworkTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileWrongTokenNetworkTest.class);

    @Rule
    public DaggerProfileComponentRule daggerProfileComponentRule =
            new DaggerProfileComponentRule(new MockProfileNetworkModule(1));

    @Test
    public void testMyProfile() throws Exception {
        final TestSubscriber<UserProfile> testSubscriber = new RestApiTestSubscriber<>();
        daggerProfileComponentRule.getProfileProvider()
                .getMyProfile()
                .compose(SchedulersUtils.applySchedulers(new TestCoreScheduler()))
                .subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();
        assertEquals("Number of errors", 1, testSubscriber.getOnErrorEvents().size());
        LOGGER.error("Exception: ", testSubscriber.getOnErrorEvents().get(0));
        assertTrue("Exception is not NetworkErrorException", testSubscriber.getOnErrorEvents().get(0) instanceof NetworkErrorException);
        assertEquals("Access token invalid error", AuthResponseCodes.ACCESS_TOKEN_INVALID_ERROR,
                ((NetworkErrorException) testSubscriber.getOnErrorEvents().get(0)).getCode());
    }
}
