package com.magorasystems.mafmodules;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.magorasystems.mafmodules.mvp.provider.impl.SimpleAuthProvider;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.network.RestApiTestSubscriber;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.utils.JsonStub;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.inject.Named;

import rx.observers.TestSubscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ProvidersTest extends BaseTest {

    @Inject
    @Named(BaseModule.QUALIFIER_MOCK)
    protected AuthApiClientWrapper mockAuthApiClientWrapper;

    @Inject
    protected SimpleAuthProvider mockSimpleAuthProvider;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        getComponent().inject(this);
    }

    @Test
    public void testDaggerInit() throws Exception {
        Assert.assertNotNull("AuthApiClientWrapper ", mockAuthApiClientWrapper);
        Assert.assertNotNull("AuthProvider ", mockSimpleAuthProvider);
        Assert.assertNotNull("Memory token store ", stringApiTokenStorage);
    }

    @Test
    public void testAuthMethod() throws Exception {
        Assert.assertNotNull("AuthProvider ", mockSimpleAuthProvider);
        final TestSubscriber<StringAuthInfo> testSubscriber = new RestApiTestSubscriber<>();
        mockSimpleAuthProvider.authToken(JsonStub.generateMetaAuthRequest())
                .compose(SchedulersUtils.applySchedulers(coreScheduler))
                .subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        StringAuthInfo authUser = testSubscriber.getOnNextEvents().get(0);
        Assert.assertNotNull(authUser);
        LOGGER.info("Auth User {} ", authUser);
        LOGGER.info("stringApiTokenStorage {} ", stringApiTokenStorage);
        SimpleTokenConfig config = stringApiTokenStorage.restoreObject(SimpleTokenConfig.HEADER_FIELD_NAME);
        LOGGER.info("Config {} ", config);
        Assert.assertNotNull("Config ", config);
    }
}
