package com.magorasystems.mafmodules.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodule.security.store.AuthPreferencesStorage;
import com.magorasystems.mafmodule.security.store.SimplePreferencesTokenStorage;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.network.SimpleRegistrationApiClientWrapper;
import com.magorasystems.mafmodules.provider.registration.SimpleRegistrationProvider;
import com.magorasystems.mafmodules.provider.registration.SimpleRegistrationProviderImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class SampleProvidersModule implements BaseModule {


    @Provides
    @ApplicationScope
    protected SimpleRegistrationProvider providerRegistrationProvider(Context context,
                                                                      SchedulersUtils.CoreScheduler scheduler,
                                                                      SimpleRegistrationApiClientWrapper wrapper,
                                                                      SimplePreferencesTokenStorage preferencesTokenStorage,
                                                                      AuthPreferencesStorage authPreferencesStorage) {
        final SampleComponent component = (SampleComponent) ((HasComponent<?>) context).getComponent(SampleComponent.class.getSimpleName());
        return new SimpleRegistrationProviderImpl(component, scheduler, wrapper, preferencesTokenStorage, authPreferencesStorage);
    }
}
