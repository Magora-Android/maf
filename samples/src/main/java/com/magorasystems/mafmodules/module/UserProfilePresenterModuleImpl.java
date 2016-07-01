package com.magorasystems.mafmodules.module;

import android.content.Context;

import com.magorasystems.mafmodules.common.module.base.AbstractModulePresenter;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;
import com.magorasystems.mafmodules.dagger.component.ProfileComponent;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.module.input.impl.UserProfileViewInput;
import com.magorasystems.mafmodules.module.output.UserProfileViewOutput;
import com.magorasystems.mafmodules.presenter.impl.SimpleProfilePresenter;
import com.magorasystems.mafmodules.profile.router.ProfileRouter;
import com.magorasystems.mafmodules.store.UserProfilePreferencesStorage;
import com.magorasystems.mafmodules.view.impl.UserProfileInteractiveView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserProfilePresenterModuleImpl extends AbstractModulePresenter<ProfileRouter<UserProfile>,
        UserProfileViewInput, UserProfileViewOutput, UserProfileModuleInput>
        implements UserProfilePresenterModule, Injectable<ProfileComponent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfilePresenterModuleImpl.class);

    private SimpleProfilePresenter presenter;

    @Inject
    protected UserProfilePreferencesStorage preferencesStorage;

    @Inject
    public UserProfilePresenterModuleImpl(Context application, SimpleProfilePresenter presenter) {
        this.presenter = presenter;
        injectComponent(application, ProfileComponent.class);
    }

    @Override
    protected SimpleProfilePresenter getPresenter() {
        return presenter;
    }


    @Override
    public void onError(Throwable e) {
        final ProfileRouter<UserProfile> router = getModuleInput().getRouter();
        if (router != null) {
            router.onShowError(e);
        }
    }

    @Override
    public void onNext(UserProfileViewOutput profile) {
        preferencesStorage.storeObject("my", profile.getModel());
        final ProfileRouter<UserProfile> router = getModuleInput().getRouter();
        if (router != null) {
            router.onUpdateProfile(profile.getModel());
        }
    }

    @Override
    public void input(UserProfileModuleInput input) {
        super.input(input);
        getPresenter().setRouter(input.getRouter());
        getPresenter().attachView(input.getViewInput().getPassiveView());
    }

    @Override
    public void inject(ProfileComponent profileComponent) {
        profileComponent.inject(this);
    }

    @Override
    public void start() {
        final UserProfileInteractiveView interactiveView = getModuleInput().getViewInput().getInteractiveView();
        if (interactiveView != null) {
            subscription.add(interactiveView.model()
                    .subscribe(v -> getPresenter().takeMyProfile(), this::log, () -> log("complete")));
            subscription.add(interactiveView.edit()
                    .subscribe(v -> getPresenter().editProfile(preferencesStorage.restoreObject("my")), this::log, () -> log("complete")));
        }
        output(getPresenter().output());
        final UserProfile profile = preferencesStorage.restoreObject("my");
        if (profile != null) {
            getPresenter().updateProfile(profile);
        }
        super.start();
    }

    private void log(Throwable throwable) {
        LOGGER.error("something wrong", throwable);
    }

    private void log(String message) {
        LOGGER.debug(message);
    }

    protected final void injectComponent(Context context, Class<ProfileComponent> clazz) {
        if (context instanceof HasComponent<?>) {
            final Object component = ((HasComponent<?>) context).getComponent(clazz.getSimpleName());
            if (component != null) {
                if (component instanceof ProfileComponent) {
                    inject((ProfileComponent) component);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Context has not implement \"" + clazz.getSimpleName() + "\" component");
    }
}
