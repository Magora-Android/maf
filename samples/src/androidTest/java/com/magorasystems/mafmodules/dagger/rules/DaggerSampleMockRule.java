package com.magorasystems.mafmodules.dagger.rules;

import android.support.test.InstrumentationRegistry;

import com.magorasystems.mafmodules.application.MockAuthApplication;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.dagger.module.SampleApplicationModule;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class DaggerSampleMockRule extends DaggerMockRule<SampleComponent> {

    public DaggerSampleMockRule() {
        super(SampleComponent.class, new SampleApplicationModule(getApp()));
        set(sampleComponent -> getApp().setComponent(sampleComponent));
    }

    public static MockAuthApplication getApp() {
        return (MockAuthApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }

}
