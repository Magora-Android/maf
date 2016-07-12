package com.magorasystems.mafmodules.network.tests;

import android.support.test.runner.AndroidJUnit4;

import com.magorasystems.mafmodules.dagger.module.MockSampleNetworkModule;
import com.magorasystems.mafmodules.dagger.rules.DaggerSampleMockRule;
import com.magorasystems.mafmodules.utils.JsonStub;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.observers.TestSubscriber;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@RunWith(AndroidJUnit4.class)
public class RegistrationProviderTest {

    @Rule
    public DaggerSampleMockRule daggerSampleMockRule
            = new DaggerSampleMockRule(new MockSampleNetworkModule());

    @Test
    public void testRegistration() throws Exception {
        final TestSubscriber<StringAuthInfo> testSubscriber = new TestSubscriber<>();
        daggerSampleMockRule.getRegistrationProvider()
                .registration(JsonStub.generateRegistrationRequest())
                .subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        Assert.assertNotNull(testSubscriber.getOnNextEvents().get(0));
    }
}
