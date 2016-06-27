package com.magorasystems.mafmodules.dagger.rules;

import com.magorasystems.mafmodules.application.MockAuthApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.dagger.component.ProfileComponent;
import com.magorasystems.mafmodules.dagger.module.ProfileNetworkModule;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.provider.ProfileDataProvider;
import com.magorasystems.mafmodules.provider.SimpleRestProfileProvider;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class DaggerProfileComponentRule extends DaggerMockRule<ProfileComponent> {

    private SimpleRestProfileProvider profileProvider;

    public DaggerProfileComponentRule(ProfileNetworkModule networkModule) {
        super(ProfileComponent.class, networkModule);
        addComponentDependency(CommonModuleComponent.class, new ApplicationModule(DaggerSampleMockRule.getApp()));
        set(profileComponent -> {
            final MockAuthApplication app = DaggerSampleMockRule.getApp();
            app.putComponent(ProfileComponent.class.getSimpleName(), profileComponent);
            profileProvider = profileComponent.getRestApiProvider();
        });
    }

    public ProfileDataProvider<UserProfile> getProfileProvider() {
        return profileProvider;
    }
}
