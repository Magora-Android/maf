package com.magorasystems.mafmodules.dagger.module;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magorasystems.mafmodules.BuildConfig;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.network.AuthApiClient;
import com.magorasystems.mafmodules.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.network.AuthApiClientWrapperImpl;
import com.magorasystems.mafmodules.network.AuthRestApiFactory;
import com.magorasystems.mafmodules.network.RefreshTokenApiClient;
import com.magorasystems.mafmodules.network.RefreshTokenRestApiFactory;
import com.magorasystems.mafmodules.network.RestApiFactory;
import com.magorasystems.mafmodules.network.client.LoggerOkHttpClientFactory;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.network.config.SimpleServerEndpoint;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.interceptor.HeaderInterceptor;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;

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
public class AuthNetworkModule implements BaseModule {

    @Provides
    protected Cache providerOkHttpCache(Context application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    protected Gson providerGson() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    protected HeaderInterceptor providerHeaderInterceptor(StringApiTokenStorage tokenStorable) {
        return new HeaderInterceptor(SimpleTokenConfig.HEADER_FIELD_NAME, tokenStorable);
    }

    @Provides
    @ApplicationScope
    protected OkHttpClient provideOkHttpClient(Cache cache, HeaderInterceptor headerInterceptor) {
        return LoggerOkHttpClientFactory.builder()
                .registerInterceptor(headerInterceptor)
                .isDebug(true)
                .maxRequestsCount(BuildConfig.REQUESTS_COUNT)
                .cache(cache)
                .build()
                .create();
    }

    @Provides
    protected ServerEndpoint providerServerEndpoint() {
        return new SimpleServerEndpoint.Builder()
                .host(BuildConfig.REST_API_HOST)
                .path(BuildConfig.REST_API_PATH)
                .version(BuildConfig.REST_API_VERSION)
                .build();
    }

    @Provides
    protected AuthApiClient providerAuthRestClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new RestApiFactory.Builder<>(AuthRestApiFactory.class)
                .client(client)
                .endpoint(serverEndpoint)
                .registerCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .registerConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create();
    }

    @Provides
    protected RefreshTokenApiClient providerRefreshTokenApiClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new RestApiFactory.Builder<>(RefreshTokenRestApiFactory.class)
                .client(client)
                .endpoint(serverEndpoint)
                .registerCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .registerConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create();
    }

    @Provides
    protected AuthApiClientWrapper providerAuthClientWrapper(AuthApiClient apiClient) {
        return new AuthApiClientWrapperImpl(apiClient);
    }
}
