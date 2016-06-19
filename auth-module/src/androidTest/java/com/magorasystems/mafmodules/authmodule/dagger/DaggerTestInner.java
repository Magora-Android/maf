package com.magorasystems.mafmodules.authmodule.dagger;

import com.magorasystems.mafmodules.authmodule.application.MockAuthApplication;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.dagger.component.DaggerAuthComponent;
import com.magorasystems.mafmodules.common.dagger.component.DaggerCommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.common.dagger.module.DomainModule;
import com.magorasystems.mafmodules.common.dagger.module.StorableModule;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public final class DaggerTestInner {

    private DaggerTestInner() {

    }

    public static AuthComponent buildGraph(final MockAuthApplication context) {
        return DaggerAuthComponent.builder()
                .commonModuleComponent(DaggerCommonModuleComponent.builder()
                        .applicationModule(new ApplicationModule(context))
                        .storableModule(new StorableModule())
                        .domainModule(new DomainModule()).build())
                .build();
    }
}
