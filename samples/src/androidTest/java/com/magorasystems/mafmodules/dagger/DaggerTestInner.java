package com.magorasystems.mafmodules.dagger;

import com.magorasystems.mafmodules.application.SampleApplication;
import com.magorasystems.mafmodules.common.dagger.component.DaggerCommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.dagger.module.TestSampleModule;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public final class DaggerTestInner {

    private DaggerTestInner() {

    }

    public static SampleComponent buildGraph(final SampleApplication context) {
        return DaggerTestSampleComponent.builder()
                .commonModuleComponent(DaggerCommonModuleComponent.builder()
                        .applicationModule(new ApplicationModule(context)).build())
                .testSampleModule(new TestSampleModule(context))
                .build();
    }
}
