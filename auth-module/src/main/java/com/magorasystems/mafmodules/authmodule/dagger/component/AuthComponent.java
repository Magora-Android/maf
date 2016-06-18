package com.magorasystems.mafmodules.authmodule.dagger.component;


import com.magorasystems.mafmodule.security.network.RefreshTokenApiClient;
import com.magorasystems.mafmodules.authmodule.dagger.module.AuthInteratorModule;
import com.magorasystems.mafmodules.authmodule.dagger.module.AuthNetworkModule;
import com.magorasystems.mafmodules.authmodule.dagger.module.AuthPresenterModule;
import com.magorasystems.mafmodules.authmodule.dagger.module.AuthProviderModule;
import com.magorasystems.mafmodules.authmodule.dagger.scope.AuthScope;
import com.magorasystems.mafmodules.authmodule.interactor.impl.SimpleAuthInteractor;
import com.magorasystems.mafmodules.authmodule.interactor.impl.SimpleAuthInteractorImpl;
import com.magorasystems.mafmodules.authmodule.network.AuthApiClient;
import com.magorasystems.mafmodules.authmodule.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.authmodule.network.provider.AuthRestProvider;
import com.magorasystems.mafmodules.authmodule.presenter.SimpleAuthPresenter;
import com.magorasystems.mafmodules.authmodule.presenter.SimpleAuthPresenterImpl;
import com.magorasystems.mafmodules.authmodule.provider.impl.SimpleAuthProvider;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;

import dagger.Component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */

@Component(dependencies = CommonModuleComponent.class,
           modules = {AuthNetworkModule.class,
                   AuthProviderModule.class, AuthInteratorModule.class,
                   AuthPresenterModule.class})
@AuthScope
public interface AuthComponent {

    CommonModuleComponent commonModuleComponent();

    SimpleAuthInteractor getSimpleAuthInteractor();

    SimpleAuthPresenter getSimpleAuthPresenter();

    SimpleAuthProvider getSimpleAuthProvider();

    AuthApiClient getAuthApiClient();

    RefreshTokenApiClient getRefreshTokenApiClient();

    AuthApiClientWrapper getAuthApiClientWrapper();

    void inject(AuthRestProvider provider);

    void inject(SimpleAuthInteractorImpl i);

    void inject(SimpleAuthPresenterImpl p);

}
