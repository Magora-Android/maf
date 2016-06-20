package com.magorasystems.mafmodules.dagger;

import com.magorasystems.mafmodules.application.MockAuthApplication;
import com.magorasystems.mafmodules.dagger.component.DaggerSampleComponent;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.dagger.module.SampleApplicationModule;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public final class DaggerTestInner {

    private DaggerTestInner() {

    }

    public static SampleComponent buildGraph(final MockAuthApplication context) {
        return DaggerSampleComponent.builder()
                .sampleApplicationModule(new SampleApplicationModule(context))
                .build();


    }
}
