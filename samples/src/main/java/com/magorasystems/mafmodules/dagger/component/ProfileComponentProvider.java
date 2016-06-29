package com.magorasystems.mafmodules.dagger.component;

import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;
import com.magorasystems.mafmodules.module.UserProfilePresenterModule;
import com.magorasystems.mafmodules.network.UserProfileApiClientWrapper;
import com.magorasystems.mafmodules.presenter.impl.SimpleProfilePresenter;

import javax.inject.Inject;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class ProfileComponentProvider implements Injectable<ProfileComponent> {

    @Inject
    protected UserProfileApiClientWrapper apiClientWrapper;

    @Inject
    protected SimpleProfilePresenter profilePresenter;

    @Inject
    protected Observable<UserProfilePresenterModule> presenterModuleObservable;

    protected UserProfilePresenterModule profilePresenterModule;


    public ProfileComponentProvider(HasComponent<?> hasComponent) {
        final Object object = hasComponent.getComponent(ProfileComponent.class.getSimpleName());
        if (object != null) {
            if (object instanceof ProfileComponent) {
                inject((ProfileComponent) object);
                return;
            }
        }
        throw new IllegalArgumentException("wrong input args \"" + hasComponent +
                "\" is not contain ProfileComponent");

    }

    public UserProfileApiClientWrapper getApiClientWrapper() {
        return apiClientWrapper;
    }

    public SimpleProfilePresenter getProfilePresenter() {
        return profilePresenter;
    }

    public UserProfilePresenterModule getProfilePresenterModule() {
        return profilePresenterModule;
    }

    @Override
    public void inject(ProfileComponent profileComponent) {
        profileComponent.inject(this);
        if (presenterModuleObservable != null) {
            presenterModuleObservable.subscribe(userProfilePresenterModule -> {
                profilePresenterModule = userProfilePresenterModule;
            });
        }
    }
}
