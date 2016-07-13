package com.magorasystems.mafmodules.dagger.module;

import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.interactor.SimpleRegistrationInteractor;
import com.magorasystems.mafmodules.interactor.impl.SimpleRegistrationInteractorImpl;
import com.magorasystems.mafmodules.provider.registration.SimpleRegistrationProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class SampleInteractorModule implements BaseModule {

    @Provides
    @ApplicationScope
    protected SimpleRegistrationInteractor providerRegistrationInteractor(SchedulersUtils.CoreScheduler scheduler,
                                                                          SimpleRegistrationProvider provider) {
        return new SimpleRegistrationInteractorImpl(scheduler, provider);
    }
}
