package com.magorasystems.mafmodules.dagger.rules;

import com.magorasystems.mafmodules.application.MockSampleApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.dagger.component.ProfileComponent;
import com.magorasystems.mafmodules.dagger.module.MockStorableModule;
import com.magorasystems.mafmodules.dagger.module.profile.ProfileNetworkModule;
import com.magorasystems.mafmodules.interactor.SimpleProfileInteractor;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.presenter.impl.SimpleProfilePresenter;
import com.magorasystems.mafmodules.profile.provider.ProfileDataProvider;
import com.magorasystems.mafmodules.provider.SimpleRestProfileProvider;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class DaggerProfileComponentRule extends DaggerMockRule<ProfileComponent> {

    private SimpleRestProfileProvider profileProvider;
    private SimpleProfileInteractor profileInteractor;
    private SimpleProfilePresenter profilePresenter;

    public DaggerProfileComponentRule(ProfileNetworkModule networkModule) {
        super(ProfileComponent.class, networkModule);
        addComponentDependency(CommonModuleComponent.class,
                new ApplicationModule(DaggerSampleMockRule.getApp()),
                new MockStorableModule());
        set(profileComponent -> {
            final MockSampleApplication app = DaggerSampleMockRule.getApp();
            app.putComponent(ProfileComponent.class.getSimpleName(), profileComponent);
            profileProvider = profileComponent.getRestApiProvider();
            profileInteractor = profileComponent.getProfileInteractor();
            profilePresenter = profileComponent.getProfilePresneter();
        });
    }

    public ProfileDataProvider<UserProfile> getProfileProvider() {
        return profileProvider;
    }

    public SimpleProfileInteractor getProfileInteractor() {
        return profileInteractor;
    }

    public SimpleProfilePresenter getProfilePresenter() {
        return profilePresenter;
    }
}
