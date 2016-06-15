package com.magorasystems.mafmodules.dagger.module;

import com.google.gson.Gson;
import com.magorasystems.mafmodules.network.AuthApiClient;
import com.magorasystems.mafmodules.network.MockWrongAuthRestClient;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;

import okhttp3.OkHttpClient;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class TestAuthNetworkWrongModule extends TestAuthNetworkModule {

    @Override
    public AuthApiClient providerAuthRestClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new MockWrongAuthRestClient();
    }
}
