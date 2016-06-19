package com.magorasystems.mafmodules.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class SampleApplicationModule implements BaseModule {

    private final SampleApplication application;

    public SampleApplicationModule(final SampleApplication application) {
        this.application = application;
    }

    @Provides
    @ApplicationScope
    public Context providerContext() {
        return this.application.getContext();
    }
}
