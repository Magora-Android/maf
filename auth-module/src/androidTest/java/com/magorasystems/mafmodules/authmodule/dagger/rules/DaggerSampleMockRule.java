package com.magorasystems.mafmodules.authmodule.dagger.rules;

import android.support.test.InstrumentationRegistry;

import com.magorasystems.mafmodules.authmodule.application.MockAuthApplication;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.dagger.module.AuthNetworkModule;
import com.magorasystems.mafmodules.common.application.BaseComponentApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class DaggerSampleMockRule extends DaggerMockRule<AuthComponent> {

    public DaggerSampleMockRule(AuthNetworkModule networkModule) {
        super(AuthComponent.class, networkModule);
        addComponentDependency(CommonModuleComponent.class, new ApplicationModule(getApp()));
        set(sampleComponent -> getApp().setComponent(sampleComponent));
    }

    public static BaseComponentApplication<AuthComponent> getApp() {
        return (MockAuthApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }

}
