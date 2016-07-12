package com.magorasystems.mafmodules.dagger.module.profile;

import com.google.gson.Gson;
import com.magorasystems.mafmodule.security.network.RefreshTokenApiClient;
import com.magorasystems.mafmodule.security.network.RefreshTokenRestApiFactory;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.ProfileScope;
import com.magorasystems.mafmodules.network.ProfileApiClient;
import com.magorasystems.mafmodules.network.ProfileRestApiFactory;
import com.magorasystems.mafmodules.network.RestApiFactory;
import com.magorasystems.mafmodules.network.UserProfileApiClientWrapper;
import com.magorasystems.mafmodules.network.UserProfileApiClientWrapperImpl;
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
public class ProfileNetworkModule implements BaseModule {

    @Provides
    @ProfileScope
    protected ProfileApiClient providerProfileApiClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new RestApiFactory.Builder<>(ProfileRestApiFactory.class)
                .client(client)
                .endpoint(serverEndpoint)
                .registerCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .registerConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create();
    }

    @Provides
    @ProfileScope
    protected UserProfileApiClientWrapper providerClientWrapper(ProfileApiClient apiClient) {
        return new UserProfileApiClientWrapperImpl(apiClient);
    }

    @Provides
    @ProfileScope
    protected RefreshTokenApiClient providerRefreshTokenApiClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new RestApiFactory.Builder<>(RefreshTokenRestApiFactory.class)
                .client(client)
                .endpoint(serverEndpoint)
                .registerCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .registerConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create();
    }
}
