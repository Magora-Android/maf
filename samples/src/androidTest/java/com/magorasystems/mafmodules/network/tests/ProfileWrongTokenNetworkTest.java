package com.magorasystems.mafmodules.network.tests;

import android.support.test.runner.AndroidJUnit4;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.rx.PresenterSubscriber;
import com.magorasystems.mafmodules.common.utils.rx.TestCoreScheduler;
import com.magorasystems.mafmodules.dagger.module.MockProfileNetworkModule;
import com.magorasystems.mafmodules.dagger.rules.DaggerProfileComponentRule;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.network.RestApiTestSubscriber;
import com.magorasystems.mafmodules.presenter.SimpleProfilePresenter;
import com.magorasystems.mafmodules.view.impl.UserProfileLceView;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.observers.TestSubscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@RunWith(AndroidJUnit4.class)
public class ProfileWrongTokenNetworkTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileWrongTokenNetworkTest.class);

    final CountDownLatch signalLifecycle = new CountDownLatch(3);
    final CountDownLatch signalDetach = new CountDownLatch(1);

    @Rule
    public DaggerProfileComponentRule daggerProfileComponentRule =
            new DaggerProfileComponentRule(new MockProfileNetworkModule(1));

    @Test
    public void testMyProfileProvider() throws Exception {
        final TestSubscriber<UserProfile> testSubscriber = new RestApiTestSubscriber<>();
        daggerProfileComponentRule.getProfileProvider()
                .getMyProfile()
                .compose(SchedulersUtils.applySchedulers(new TestCoreScheduler()))
                .subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        final UserProfile userProfile = testSubscriber.getOnNextEvents().get(0);
        LOGGER.debug("{}", userProfile);
        Assert.assertNotNull("User profile is null", userProfile);
    }

    @Test
    public void testMyProfileInteractor() throws Exception {
        final TestSubscriber<UserProfile> testSubscriber = new RestApiTestSubscriber<>();
        daggerProfileComponentRule.getProfileInteractor()
                .executeMyProfile(new PresenterSubscriber<>(testSubscriber));
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        final UserProfile userProfile = testSubscriber.getOnNextEvents().get(0);
        LOGGER.debug("{}", userProfile);
        Assert.assertNotNull("User profile is null", userProfile);

    }

    @Test
    public void testMyProfilePresenter() throws Exception {

        final SimpleProfilePresenter profilePresenter = daggerProfileComponentRule.getProfilePresenter();

        profilePresenter.attachView(lceView);
        profilePresenter.takeMyProfile();
        signalLifecycle.await(60, TimeUnit.SECONDS);
        profilePresenter.detachView(false);
        signalDetach.await(5, TimeUnit.SECONDS);
    }

    private final UserProfileLceView lceView = new UserProfileLceView() {
        @Override
        public void showProgress() {
            signalLifecycle.countDown();
        }

        @Override
        public void showContent() {
            signalLifecycle.countDown();
        }

        @Override
        public void setModel(UserProfile model) {

            signalLifecycle.countDown();
            Assert.assertNotNull("Moder is null", model);
            LOGGER.debug("UserProfile: " + model);
        }

        @Override
        public void detachView() {
            signalDetach.countDown();
        }

        @Override
        public void showError(Throwable e) {

        }
    };
}
