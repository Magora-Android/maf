package com.magorasystems.mafmodules.dagger.module;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class SampleModule implements BaseModule {

    private final SampleApplication application;

    public SampleModule(final SampleApplication sampleApplication) {
        this.application = sampleApplication;
    }

    @Provides
    protected SampleApplication providerApplication() {
        return application;
    }
}
