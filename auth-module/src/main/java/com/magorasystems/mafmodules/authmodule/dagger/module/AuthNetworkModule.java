package com.magorasystems.mafmodules.authmodule.dagger.module;

import com.google.gson.Gson;
import com.magorasystems.mafmodule.security.network.RefreshTokenApiClient;
import com.magorasystems.mafmodule.security.network.RefreshTokenRestApiFactory;
import com.magorasystems.mafmodules.authmodule.network.AuthApiClient;
import com.magorasystems.mafmodules.authmodule.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.authmodule.network.AuthApiClientWrapperImpl;
import com.magorasystems.mafmodules.authmodule.network.AuthRestApiFactory;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.network.RestApiFactory;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class AuthNetworkModule implements BaseModule {


    @Provides
    protected AuthApiClient providerAuthRestClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new RestApiFactory.Builder<>(AuthRestApiFactory.class)
                .client(client)
                .endpoint(serverEndpoint)
                .registerCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .registerConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create();
    }

    @Provides
    protected RefreshTokenApiClient providerRefreshTokenApiClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new RestApiFactory.Builder<>(RefreshTokenRestApiFactory.class)
                .client(client)
                .endpoint(serverEndpoint)
                .registerCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .registerConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create();
    }

    @Provides
    protected AuthApiClientWrapper providerAuthClientWrapper(AuthApiClient apiClient) {
        return new AuthApiClientWrapperImpl(apiClient);
    }
}
