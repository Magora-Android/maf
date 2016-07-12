package com.magorasystems.mafmodules.dagger.module;

import com.google.gson.Gson;
import com.magorasystems.mafmodules.network.MockRegistrationRestClient;
import com.magorasystems.mafmodules.network.RegistrationApiClient;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;

import okhttp3.OkHttpClient;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockSampleNetworkModule extends SampleNetworkModule {

    @Override
    protected RegistrationApiClient providerRegistrationApiClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new MockRegistrationRestClient();
    }
}
