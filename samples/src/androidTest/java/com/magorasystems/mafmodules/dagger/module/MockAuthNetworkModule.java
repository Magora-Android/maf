package com.magorasystems.mafmodules.dagger.module;

import com.google.gson.Gson;
import com.magorasystems.mafmodule.security.network.RefreshTokenApiClient;
import com.magorasystems.mafmodules.authmodule.dagger.module.AuthNetworkModule;
import com.magorasystems.mafmodules.authmodule.network.AuthApiClient;
import com.magorasystems.mafmodules.authmodule.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.authmodule.network.AuthApiClientWrapperImpl;
import com.magorasystems.mafmodules.network.MockAuthRestClient;
import com.magorasystems.mafmodules.network.MockRefreshTokenRestClient;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;

import okhttp3.OkHttpClient;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockAuthNetworkModule extends AuthNetworkModule {

    @Override
    public AuthApiClient providerAuthRestClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new MockAuthRestClient();
    }

    @Override
    public RefreshTokenApiClient providerRefreshTokenApiClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new MockRefreshTokenRestClient(serverEndpoint, gson, client);
    }

    @Override
    public AuthApiClientWrapper providerAuthClientWrapper(AuthApiClient apiClient) {
        return new AuthApiClientWrapperImpl(apiClient);
    }
}
