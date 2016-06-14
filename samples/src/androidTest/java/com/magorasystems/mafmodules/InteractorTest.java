package com.magorasystems.mafmodules;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.magorasystems.mafmodules.mvp.provider.impl.SimpleAuthProvider;
import com.magorasystems.mafmodules.mvp.interactor.SimpleAuthInteractor;
import com.magorasystems.mafmodules.utils.JsonStub;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class InteractorTest extends BaseTest {

    @Inject
    protected SimpleAuthProvider mockSimpleAuthProvider;

    @Inject
    protected SimpleAuthInteractor authInteractor;


    private CountDownLatch signalLifecycle;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        getComponent().inject(this);
    }

    @Test
    public void testDaggerInit() throws Exception {
        Assert.assertNotNull("AuthProvider ", mockSimpleAuthProvider);
        Assert.assertNotNull("Memory token store ", stringApiTokenStorage);
        Assert.assertNotNull("SimpleAuthInteractor ", authInteractor);
    }

    @Test
    public void testAuthMethod() throws Exception {
        Assert.assertNotNull("AuthInteractor ", authInteractor);
        signalLifecycle = new CountDownLatch(2);
        authInteractor.executeAuthToken(JsonStub.generateMetaAuthRequest(), new Subscriber<StringAuthInfo>() {
            @Override
            public void onCompleted() {
                signalLifecycle.countDown();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StringAuthInfo stringAuthInfo) {
                Assert.assertNotNull("Auth User", stringAuthInfo);
                signalLifecycle.countDown();
            }
        });
        signalLifecycle.await();
    }
}
