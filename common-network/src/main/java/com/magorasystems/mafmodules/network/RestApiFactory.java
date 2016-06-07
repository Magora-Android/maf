package com.magorasystems.mafmodules.network;

import com.google.common.collect.Lists;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.network.exception.RestApiException;

import java.lang.reflect.Constructor;
import java.util.Collection;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RestApiFactory<CLIENT> {

    CLIENT create() throws RestApiException;

    class Builder<CLIENT, T extends RestApiFactory<CLIENT>> {

        private OkHttpClient client;
        private ServerEndpoint endpoint;
        private Collection<Converter.Factory> convertFactories;
        private Collection<CallAdapter.Factory> callAdapterFactories;
        private final Class<T> clazz;

        public Builder(Class<T> clazz) {
            this.clazz = clazz;
        }

        public Builder<CLIENT, T> client(OkHttpClient val) {
            this.client = val;
            return this;
        }

        public Builder<CLIENT, T> endpoint(ServerEndpoint val) {
            this.endpoint = val;
            return this;
        }

        public Builder<CLIENT, T> registerConverterFactory(Converter.Factory val) {
            if (convertFactories == null) {
                convertFactories = Lists.newArrayList();
            }
            if (!convertFactories.contains(val)) {
                convertFactories.add(val);
            }
            return this;
        }

        public Builder<CLIENT, T> registerCallAdapterFactory(CallAdapter.Factory val) {
            if (callAdapterFactories == null) {
                callAdapterFactories = Lists.newArrayList();
            }
            if (!callAdapterFactories.contains(val)) {
                callAdapterFactories.add(val);
            }
            return this;
        }

        OkHttpClient getClient() {
            return client;
        }

        ServerEndpoint getEndpoint() {
            return endpoint;
        }

        Collection<Converter.Factory> getConvertFactories() {
            if (convertFactories == null) {
                return Lists.newArrayList();
            }
            return Lists.newArrayList(convertFactories);
        }

        Collection<CallAdapter.Factory> getCallAdapterFactories() {
            if (callAdapterFactories == null) {
                return Lists.newArrayList();
            }
            return Lists.newArrayList(callAdapterFactories);
        }

        public T build() throws RestApiException {
            if (endpoint == null) {
                throw new RestApiException("server endpoint not set ");
            }
            if (client == null) {
                throw new RestApiException("http client not set ");
            }
            try {
                Constructor<T> constructor = clazz.getConstructor(this.getClass());
                return constructor.newInstance(this);
            } catch (Exception e) {
                throw new RestApiException("can't find public constructor \'" + clazz.getSimpleName() + "\' ", e);
            }
        }

    }

}
