package com.magorasystems.mafmodules.dagger.rules;

import com.magorasystems.mafmodule.security.store.AuthPreferencesStorage;
import com.magorasystems.mafmodule.security.store.SimplePreferencesTokenStorage;
import com.magorasystems.mafmodules.application.MockSampleApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.dagger.component.SocialComponent;
import com.magorasystems.mafmodules.dagger.module.MockStorableModule;
import com.magorasystems.mafmodules.dagger.module.social.SocialNetworkModule;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class DaggerSocialComponentRule extends DaggerMockRule<SocialComponent> {

    private AuthPreferencesStorage authPreferencesStorage;
    private SimplePreferencesTokenStorage preferencesTokenStorage;

    public DaggerSocialComponentRule(SocialNetworkModule module) {
        super(SocialComponent.class, module);
        addComponentDependency(CommonModuleComponent.class,
                new ApplicationModule(DaggerSampleMockRule.getApp()),
                new MockStorableModule());
        set(socialComponent -> {
            MockSampleApplication app = DaggerSampleMockRule.getApp();
            app.putComponent(SocialComponent.class.getSimpleName(), socialComponent);
            authPreferencesStorage = socialComponent.getAuthPreferencesStorage();
            preferencesTokenStorage = socialComponent.getPreferencesTokenStorage();
        });
    }

    public AuthPreferencesStorage getAuthPreferencesStorage() {
        return authPreferencesStorage;
    }

    public SimplePreferencesTokenStorage getPreferencesTokenStorage() {
        return preferencesTokenStorage;
    }
}
