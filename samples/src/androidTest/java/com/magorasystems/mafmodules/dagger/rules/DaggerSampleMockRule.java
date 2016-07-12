package com.magorasystems.mafmodules.dagger.rules;

import android.support.test.InstrumentationRegistry;

import com.magorasystems.mafmodules.application.MockSampleApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.provider.registration.SimpleRegistrationProvider;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class DaggerSampleMockRule extends DaggerMockRule<SampleComponent> {

    private SimpleRegistrationProvider registrationProvider;

    public DaggerSampleMockRule(Object... modules) {
        super(SampleComponent.class, modules);
        addComponentDependency(CommonModuleComponent.class,
                new ApplicationModule(getApp()));
        set(sampleComponent -> {
            //getApp().setComponent(sampleComponent);
            getApp().putComponent(SampleComponent.class.getSimpleName(), sampleComponent);
            registrationProvider = sampleComponent.getRegistrationProvider();

        });
    }

    public static MockSampleApplication getApp() {
        return (MockSampleApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }

    public SimpleRegistrationProvider getRegistrationProvider() {
        return registrationProvider;
    }
}
