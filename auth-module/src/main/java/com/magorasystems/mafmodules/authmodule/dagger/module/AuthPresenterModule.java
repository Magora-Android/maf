package com.magorasystems.mafmodules.authmodule.dagger.module;

import com.magorasystems.mafmodules.authmodule.dagger.scope.AuthScope;
import com.magorasystems.mafmodules.authmodule.interactor.impl.SimpleAuthInteractor;
import com.magorasystems.mafmodules.authmodule.presenter.SimpleAuthPresenter;
import com.magorasystems.mafmodules.authmodule.presenter.SimpleAuthPresenterImpl;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;

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
    public SimpleAuthPresenter providerSimpleAuthPresenter(SimpleAuthInteractor authInteractor) {
        return new SimpleAuthPresenterImpl(authInteractor);
    }
}
