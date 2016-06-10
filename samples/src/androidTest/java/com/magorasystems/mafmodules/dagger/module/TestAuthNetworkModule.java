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
import com.magorasystems.mafmodules.network.AuthRestApiFactory;
import com.magorasystems.mafmodules.network.MockAuthRestClient;
import com.magorasystems.mafmodules.network.RestApiFactory;
import com.magorasystems.mafmodules.network.client.LoggerOkHttpClientFactory;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.network.config.SimpleServerEndpoint;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.interceptor.HeaderInterceptor;
import com.magorasystems.mafmodules.network.store.SimpleMemoryTokenStorable;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
    @Named(QUALIFIER_COMBAT)
    public AuthApiClient providerAuthRestClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new RestApiFactory.Builder<>(AuthRestApiFactory.class)
                .client(client)
                .endpoint(serverEndpoint)
                .registerCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .registerConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create();
    }

    @Provides
    @ApplicationScope
    @Named(QUALIFIER_COMBAT)
    public AuthApiClientWrapper providerAuthClientWrapper(AuthApiClient apiClient) {
        return new AuthApiClientWrapperImpl(apiClient);
    }
    @Provides
    @ApplicationScope
    @Named(QUALIFIER_MOCK)
    protected AuthApiClient providerMockAuthRestClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new MockAuthRestClient();
    }

    @Provides
    @ApplicationScope
    @Named(QUALIFIER_MOCK)
    protected AuthApiClientWrapper providerMockAuthClientWrapper(@Named(QUALIFIER_MOCK) AuthApiClient apiClient) {
        return new AuthApiClientWrapperImpl(apiClient);
    }
}