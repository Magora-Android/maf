package com.magorasystems.mafmodules.dagger.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magorasystems.mafmodules.BuildConfig;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.network.AuthApiClient;
import com.magorasystems.mafmodules.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.network.AuthApiClientWrapperImpl;
import com.magorasystems.mafmodules.network.MockAuthRestClient;
import com.magorasystems.mafmodules.network.MockRefreshTokenRestClient;
import com.magorasystems.mafmodules.network.RefreshTokenApiClient;
import com.magorasystems.mafmodules.network.client.LoggerOkHttpClientFactory;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.network.config.SimpleServerEndpoint;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.interceptor.HeaderInterceptor;
import com.magorasystems.mafmodules.network.store.SimpleMemoryTokenStorable;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@Module
public class TestAuthNetworkModule implements BaseModule {

    @Provides
    @ApplicationScope
    public Cache providerOkHttpCache(Context application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @ApplicationScope
    public Gson providerGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    public HeaderInterceptor providerHeaderInterceptor(SimpleMemoryTokenStorable tokenStorable) {
        return new HeaderInterceptor(SimpleTokenConfig.HEADER_FIELD_NAME, tokenStorable);
    }

    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient(Cache cache, HeaderInterceptor headerInterceptor) {
        return LoggerOkHttpClientFactory.builder()
                .registerInterceptor(headerInterceptor)
                .isDebug(true)
                .maxRequestsCount(BuildConfig.REQUESTS_COUNT)
                .cache(cache)
                .build()
                .create();
    }

    @Provides
    public ServerEndpoint providerServerEndpoint() {
        return new SimpleServerEndpoint.Builder()
                .host(BuildConfig.REST_API_HOST)
                .path(BuildConfig.REST_API_PATH)
                .version(BuildConfig.REST_API_VERSION)
                .build();
    }

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
