package com.magorasystems.mafmodules.authmodule.dagger.module;

import android.content.Context;

import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.dagger.scope.AuthScope;
import com.magorasystems.mafmodules.authmodule.interactor.impl.SimpleAuthInteractor;
import com.magorasystems.mafmodules.authmodule.presenter.SimpleAuthPresenter;
import com.magorasystems.mafmodules.authmodule.presenter.SimpleAuthPresenterImpl;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;

import dagger.Module;
import dagger.Provides;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class AuthPresenterModule implements BaseModule {

    @Provides
    @AuthScope
    public SimpleAuthPresenter providerSimpleAuthPresenter(Context application, SimpleAuthInteractor authInteractor) {
        AuthComponent component = (AuthComponent) ((HasComponent<?>) application).getComponent(AuthComponent.class.getSimpleName());
        return new SimpleAuthPresenterImpl(
                component,
                authInteractor);
    }
}
