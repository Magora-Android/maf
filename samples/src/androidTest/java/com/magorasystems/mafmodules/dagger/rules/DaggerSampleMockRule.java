package com.magorasystems.mafmodules.dagger.rules;

import com.magorasystems.mafmodules.BaseTest;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.dagger.module.AuthNetworkModule;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class DaggerSampleMockRule extends DaggerMockRule<SampleComponent> {

    public DaggerSampleMockRule(AuthNetworkModule networkModule) {
        super(SampleComponent.class, networkModule);
        addComponentDependency(CommonModuleComponent.class, new ApplicationModule(BaseTest.getApp()));
        set(sampleComponent -> BaseTest.getApp().setComponent(sampleComponent));

    }

}
