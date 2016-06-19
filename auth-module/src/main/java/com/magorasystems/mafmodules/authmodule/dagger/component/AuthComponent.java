package com.magorasystems.mafmodules.authmodule.dagger.component;


import android.location.LocationManager;

import com.google.gson.Gson;
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
import com.magorasystems.mafmodules.common.application.ApplicationSettings;
import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.network.interceptor.HeaderInterceptor;
import com.magorasystems.mafmodules.network.manager.NetworkConnectionManager;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;

import dagger.Component;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

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

    LocationManager locationManager();

    NetworkConnectionManager networkConnectionManager();

    SchedulersUtils.CoreScheduler coreScheduler();

    StringApiTokenStorage memoryTokenStorable();

    ApplicationSettings applicationSettings();

    Cache okHttpCache();

    OkHttpClient okHttpClient();

    HeaderInterceptor headerInterceptor();

    ServerEndpoint serverEndpoint();

    Gson gson();

    void inject(AuthRestProvider provider);

    void inject(SimpleAuthInteractorImpl i);

    void inject(SimpleAuthPresenterImpl p);

}
