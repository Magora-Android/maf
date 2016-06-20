package com.magorasystems.mafmodules.dagger.rules;

import com.magorasystems.mafmodules.application.MockAuthApplication;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.dagger.module.AuthNetworkModule;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class DaggerAuthComponentRule extends DaggerMockRule<AuthComponent> {

    public DaggerAuthComponentRule(AuthNetworkModule networkModule) {
        super(AuthComponent.class, networkModule);
        addComponentDependency(CommonModuleComponent.class, new ApplicationModule(DaggerSampleMockRule.getApp()));
        set(authComponent -> {
            final MockAuthApplication app = DaggerSampleMockRule.getApp();
            app.putComponent(AuthComponent.class.getSimpleName(), authComponent);
        });
    }
}
