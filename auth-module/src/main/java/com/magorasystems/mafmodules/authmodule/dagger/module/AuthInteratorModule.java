package com.magorasystems.mafmodules.authmodule.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.dagger.scope.AuthScope;
import com.magorasystems.mafmodules.authmodule.interactor.impl.SimpleAuthInteractor;
import com.magorasystems.mafmodules.authmodule.interactor.impl.SimpleAuthInteractorImpl;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class AuthInteratorModule implements BaseModule {

    @Provides
    @AuthScope
    public SimpleAuthInteractor providerAuthInteractor(Context application, SchedulersUtils.CoreScheduler scheduler) {
        Object component = ((HasComponent<?>) application).getComponent(AuthComponent.class.getSimpleName());
        return new SimpleAuthInteractorImpl((AuthComponent) component, scheduler);
    }
}
