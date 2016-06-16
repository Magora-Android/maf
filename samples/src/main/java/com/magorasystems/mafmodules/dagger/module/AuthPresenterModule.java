package com.magorasystems.mafmodules.dagger.module;

import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.mvp.interactor.SimpleAuthInteractor;
import com.magorasystems.mafmodules.mvp.presenter.SimpleAuthPresenter;
import com.magorasystems.mafmodules.mvp.presenter.SimpleAuthPresenterImpl;

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
    @ApplicationScope
    public SimpleAuthPresenter providerSimpleAuthPresenter(SimpleAuthInteractor authInteractor) {
        return new SimpleAuthPresenterImpl(authInteractor);
    }
}
