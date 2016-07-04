package com.magorasystems.mafmodules.dagger.module;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;

import dagger.Module;

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
}
