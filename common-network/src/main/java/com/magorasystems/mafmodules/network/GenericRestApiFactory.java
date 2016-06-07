package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.common.utils.ReflectionUtils;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.network.exception.RestApiException;

import java.util.Collection;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericRestApiFactory<CLIENT> implements RestApiFactory<CLIENT> {

    private final OkHttpClient client;
    private final ServerEndpoint endpoint;
    private final Collection<Converter.Factory> convertFactories;
    private final Collection<CallAdapter.Factory> callAdapterFactories;

    protected GenericRestApiFactory(Builder<CLIENT, ? extends RestApiFactory<CLIENT>> builder) {
        client = builder.getClient();
        endpoint = builder.getEndpoint();
        convertFactories = builder.getConvertFactories();
        callAdapterFactories = builder.getCallAdapterFactories();
    }

    @Override
    public CLIENT create() throws RestApiException{
        final Retrofit.Builder builder = new Retrofit.Builder()
                .client(client)
                .baseUrl(endpoint.getUrl());
        for (Converter.Factory factory : convertFactories) {
            builder.addConverterFactory(factory);
        }
        for (CallAdapter.Factory factory : callAdapterFactories) {
            builder.addCallAdapterFactory(factory);
        }
        try {
            final Class clazz = ReflectionUtils.getGenericParameterClass(
                    this.getClass(), GenericRestApiFactory.class, 0);
            return builder.build().create((Class<CLIENT>) clazz );
        } catch (NullPointerException | IllegalArgumentException | ClassCastException e) {
            throw new RestApiException("can't init rest api client ", e);
        }
    }
}
