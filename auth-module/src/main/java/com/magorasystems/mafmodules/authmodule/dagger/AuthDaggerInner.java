package com.magorasystems.mafmodules.authmodule.dagger;

import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.dagger.component.DaggerAuthComponent;
import com.magorasystems.mafmodules.common.application.ComponentApplication;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.component.DaggerCommonModuleComponent;
import com.magorasystems.mafmodules.common.dagger.module.ApplicationModule;
import com.magorasystems.mafmodules.common.dagger.module.DomainModule;
import com.magorasystems.mafmodules.common.dagger.module.StorableModule;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public final class AuthDaggerInner {

    private AuthDaggerInner() {

    }

    public static CommonModuleComponent buildCommonModuleComponent(final ComponentApplication<?> context) {
        return DaggerCommonModuleComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .storableModule(new StorableModule())
                .domainModule(new DomainModule()).build();
    }

    public static  AuthComponent buildGraph(final ComponentApplication<?> context) {
        return DaggerAuthComponent.builder()
                .commonModuleComponent(buildCommonModuleComponent(context))
                .build();
    }

    public static AuthComponent buildGraph(final CommonModuleComponent component) {
        return DaggerAuthComponent.builder()
                .commonModuleComponent(component)
                .build();
    }
}
