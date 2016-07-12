package com.magorasystems.mafmodules.dagger.module;

import com.google.gson.Gson;
import com.magorasystems.mafmodule.security.network.RefreshTokenApiClient;
import com.magorasystems.mafmodules.dagger.module.profile.ProfileNetworkModule;
import com.magorasystems.mafmodules.network.MockProfileRestClient;
import com.magorasystems.mafmodules.network.MockRefreshTokenRestClient;
import com.magorasystems.mafmodules.network.ProfileApiClient;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;

import okhttp3.OkHttpClient;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockProfileNetworkModule extends ProfileNetworkModule {

    private int useCase;

    public MockProfileNetworkModule(int useCase) {
        this.useCase = useCase;
    }

    @Override
    protected ProfileApiClient providerProfileApiClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new MockProfileRestClient(useCase);
    }

    @Override
    protected RefreshTokenApiClient providerRefreshTokenApiClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new MockRefreshTokenRestClient(serverEndpoint, gson, client);
    }
}
