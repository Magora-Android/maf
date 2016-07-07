package com.magorasystems.mafmodules.dagger.component;

import com.magorasystems.mafmodule.security.store.AuthPreferencesStorage;
import com.magorasystems.mafmodule.security.store.SimplePreferencesTokenStorage;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.dagger.module.social.SocialInteratorModule;
import com.magorasystems.mafmodules.dagger.module.social.SocialModulePresenterModule;
import com.magorasystems.mafmodules.dagger.module.social.SocialNetworkModule;
import com.magorasystems.mafmodules.dagger.module.social.SocialPresenterModule;
import com.magorasystems.mafmodules.dagger.module.social.SocialProviderModule;
import com.magorasystems.mafmodules.dagger.module.social.SocialStoreModule;
import com.magorasystems.mafmodules.dagger.module.social.SocialsModule;
import com.magorasystems.mafmodules.dagger.scope.SocialScope;
import com.magorasystems.mafmodules.module.SimpleSocialPresenterModuleImpl;
import com.magorasystems.mafmodules.provider.social.SimpleSocialProviderImpl;
import com.magorasystems.mafmodules.ui.fragment.SocialAuthorizationFragment;
import com.magorasystems.mafmodules.view.impl.SimpleSocialInteractiveView;

import dagger.Component;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@SocialScope
@Component(dependencies = {CommonModuleComponent.class},
           modules = {
                   SocialsModule.class,
                   SocialNetworkModule.class,
                   SocialProviderModule.class,
                   SocialInteratorModule.class,
                   SocialPresenterModule.class,
                   SocialModulePresenterModule.class,
                   SocialStoreModule.class})
public interface SocialComponent {

    AuthPreferencesStorage getAuthPreferencesStorage();

    SimplePreferencesTokenStorage getPreferencesTokenStorage();

    void inject(SimpleSocialProviderImpl p);

    void inject(SimpleSocialPresenterModuleImpl m);

    void inject(SocialAuthorizationFragment f);

    void inject(SimpleSocialInteractiveView v);
}
