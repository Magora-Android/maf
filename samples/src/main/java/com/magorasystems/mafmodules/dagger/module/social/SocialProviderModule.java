package com.magorasystems.mafmodules.dagger.module.social;

import android.content.Context;

import com.magorasystems.mafmodule.security.store.AuthPreferencesStorage;
import com.magorasystems.mafmodule.security.store.SimplePreferencesTokenStorage;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.component.SocialComponent;
import com.magorasystems.android.module.social.network.wrapper.SimpleSocialApiClientWrapper;
import com.magorasystems.android.module.social.provider.SimpleSocialProvider;
import com.magorasystems.mafmodules.provider.social.SimpleSocialProviderImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class SocialProviderModule implements BaseModule {

    @Provides
    protected SimpleSocialProvider providerSocialProvider(Context context, SchedulersUtils.CoreScheduler scheduler,
                                                          SimpleSocialApiClientWrapper clientWrapper,
                                                          AuthPreferencesStorage authPreferencesStorage,
                                                          SimplePreferencesTokenStorage simplePreferencesTokenStorage) {
        if (context instanceof HasComponent<?>) {
            final Object component = ((HasComponent<?>) context).getComponent(SocialComponent.class.getSimpleName());
            if (component != null) {
                if (component instanceof SocialComponent) {
                    return new SimpleSocialProviderImpl((SocialComponent) component, scheduler,
                            clientWrapper, authPreferencesStorage, simplePreferencesTokenStorage);
                }
            }
        }
        throw new IllegalArgumentException("Context has not implement \"" + SocialComponent.class.getSimpleName() + "\" component");
    }
}
