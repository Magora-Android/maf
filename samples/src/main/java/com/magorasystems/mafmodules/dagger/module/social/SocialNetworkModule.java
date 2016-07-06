package com.magorasystems.mafmodules.dagger.module.social;

import com.google.gson.Gson;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.SocialScope;
import com.magorasystems.mafmodules.network.RestApiFactory;
import com.magorasystems.mafmodules.network.SocialApiClient;
import com.magorasystems.mafmodules.network.SocialRestApiFactory;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.provider.social.SimpleSocialApiClientWrapper;
import com.magorasystems.mafmodules.provider.social.SimpleSocialApiClientWrapperImpl;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class SocialNetworkModule implements BaseModule {

    @Provides
    @SocialScope
    protected SocialApiClient providerSocialRestClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new RestApiFactory.Builder<>(SocialRestApiFactory.class)
                .client(client)
                .endpoint(serverEndpoint)
                .registerCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .registerConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create();
    }

    @Provides
    @SocialScope
    protected SimpleSocialApiClientWrapper providerAuthClientWrapper(SocialApiClient apiClient) {
        return new SimpleSocialApiClientWrapperImpl(apiClient);
    }
}
