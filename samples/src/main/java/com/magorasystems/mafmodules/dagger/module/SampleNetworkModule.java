package com.magorasystems.mafmodules.dagger.module;

import com.google.gson.Gson;
import com.magorasystems.mafmodules.common.dagger.module.BaseModule;
import com.magorasystems.mafmodules.dagger.scope.ApplicationScope;
import com.magorasystems.mafmodules.network.RegistrationApiClient;
import com.magorasystems.mafmodules.network.RegistrationRestApiFactory;
import com.magorasystems.mafmodules.network.RestApiFactory;
import com.magorasystems.mafmodules.network.SimpleRegistrationApiClientWrapper;
import com.magorasystems.mafmodules.network.SimpleRegistrationApiClientWrapperImpl;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;

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
public class SampleNetworkModule implements BaseModule {

    @Provides
    @ApplicationScope
    protected RegistrationApiClient providerRegistrationApiClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        return new RestApiFactory.Builder<>(RegistrationRestApiFactory.class)
                .client(client)
                .endpoint(serverEndpoint)
                .registerCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .registerConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create();
    }

    @Provides
    @ApplicationScope
    protected SimpleRegistrationApiClientWrapper providerClientWrapper(RegistrationApiClient apiClient) {
        return new SimpleRegistrationApiClientWrapperImpl(apiClient);
    }

}
