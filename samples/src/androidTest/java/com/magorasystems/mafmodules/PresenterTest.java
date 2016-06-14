package com.magorasystems.mafmodules;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.mvp.presenter.SimpleAuthPresenter;
import com.magorasystems.mafmodules.utils.JsonStub;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class PresenterTest extends BaseTest {

    @Inject
    protected SimpleAuthPresenter authPresenter;

    private CountDownLatch signalLifecycle;
    private CountDownLatch signalDetach;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getComponent().inject(this);
    }

    @Test
    public void testDaggerInit() throws Exception {
        Assert.assertNotNull("AuthPresenter ", authPresenter);
        Assert.assertNotNull("Memory token store ", stringApiTokenStorage);
    }

    @Test
    public void testPresenterAuthMethod() throws Exception {
        Assert.assertNotNull("AuthPresenter ", authPresenter);
        signalLifecycle = new CountDownLatch(3);
        signalDetach = new CountDownLatch(1);
        authPresenter.attachView(authView);
        authPresenter.setRouter(authRouter);
        authPresenter.authorization(JsonStub.generateAuthViewModel());
        signalLifecycle.await();
        authPresenter.detachView(false);
        signalDetach.await(1, TimeUnit.SECONDS);
    }

    private StringAuthView authView = new StringAuthView() {
        @Override
        public void showProgress() {
            signalLifecycle.countDown();
        }

        @Override
        public void showContent() {
            signalLifecycle.countDown();
        }

        @Override
        public void setModel(StringAuthInfo model) {
            Assert.assertNotNull("AuthInfo", model);
            LOGGER.debug("{}", model);
            signalLifecycle.countDown();
        }

        @Override
        public void detachView() {
            signalDetach.countDown();
        }

        @Override
        public void showError(Throwable e) {
            Assert.fail("unknown error " + e.getMessage());
        }
    };

    private AuthRouter authRouter = new AuthRouter() {
        @Override
        public void onAfterAuth() {
            LOGGER.debug("onAfterAuth");
        }

        @Override
        public void onBack() {
            LOGGER.debug(" onBack");
        }
    };
}
