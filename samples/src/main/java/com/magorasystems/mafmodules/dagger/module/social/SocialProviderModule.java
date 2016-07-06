package com.magorasystems.mafmodules.dagger.module.social;

import android.content.Context;

import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.component.SocialComponent;
import com.magorasystems.mafmodules.provider.social.SimpleSocialApiClientWrapper;
import com.magorasystems.mafmodules.provider.social.SimpleSocialProvider;
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
                                                          SimpleSocialApiClientWrapper clientWrapper) {
        if (context instanceof HasComponent<?>) {
            final Object component = ((HasComponent<?>) context).getComponent(SocialComponent.class.getSimpleName());
            if (component != null) {
                if (component instanceof SocialComponent) {
                    return new SimpleSocialProviderImpl((SocialComponent) component, scheduler, clientWrapper);
                }
            }
        }
        throw new IllegalArgumentException("Context has not implement \"" + SocialComponent.class.getSimpleName() + "\" component");
    }
}
