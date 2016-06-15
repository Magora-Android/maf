package com.magorasystems.mafmodules.dagger.module;

import com.google.gson.Gson;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.network.AuthApiClient;
import com.magorasystems.mafmodules.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.network.AuthApiClientWrapperImpl;
import com.magorasystems.mafmodules.network.MockAuthRestClient;
import com.magorasystems.mafmodules.network.MockRefreshTokenRestClient;
import com.magorasystems.mafmodules.network.RefreshTokenApiClient;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class TestAuthNetworkModule implements BaseModule {

    @Provides
    public AuthApiClient providerAuthRestClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new MockAuthRestClient();
    }

    @Provides
    public RefreshTokenApiClient providerRefreshTokenApiClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new MockRefreshTokenRestClient(serverEndpoint, gson, client);
    }

    @Provides
    @ApplicationScope
    public AuthApiClientWrapper providerAuthClientWrapper(AuthApiClient apiClient) {
        return new AuthApiClientWrapperImpl(apiClient);
    }
}
