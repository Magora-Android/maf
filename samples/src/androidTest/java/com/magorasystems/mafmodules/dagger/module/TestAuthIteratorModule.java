package com.magorasystems.mafmodules.dagger.module;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.mvp.interactor.SimpleAuthInteractor;
import com.magorasystems.mafmodules.mvp.interactor.SimpleAuthInteractorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class TestAuthIteratorModule implements BaseModule{

    @Provides
    @ApplicationScope
    protected SimpleAuthInteractor providerAuthInteractor(SampleApplication application, SchedulersUtils.CoreScheduler scheduler) {
        return new SimpleAuthInteractorImpl(application, scheduler);
    }
}
