package com.magorasystems.mafmodules.dagger.module;

import com.google.gson.Gson;
import com.magorasystems.mafmodules.dagger.module.social.SocialNetworkModule;
import com.magorasystems.mafmodules.network.MockSocialRestClient;
import com.magorasystems.mafmodules.network.SocialApiClient;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;

import okhttp3.OkHttpClient;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockSocialNetworkModule extends SocialNetworkModule {

    @Override
    protected SocialApiClient providerSocialRestClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new MockSocialRestClient();
    }
}
