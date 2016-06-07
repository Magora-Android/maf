package com.magorasystems.mafmodules.network;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magorasystems.mafmodules.network.client.LoggerOkHttpClientFactory;
import com.magorasystems.mafmodules.network.config.BasicTokenConfig;
import com.magorasystems.mafmodules.network.config.BearerTokenConfig;
import com.magorasystems.mafmodules.network.config.SimpleServerEndpoint;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.interceptor.HeaderInterceptor;
import com.magorasystems.mafmodules.network.model.SampleApiClient;
import com.magorasystems.mafmodules.network.model.SampleRestApiFactory;
import com.magorasystems.mafmodules.network.store.ApiTokenStorable;
import com.magorasystems.mafmodules.network.store.MemoryTokenStorable;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class RestApiFactoryTest {

    @Test
    public void testSampleRestApiController() throws IOException {
        final Gson gson = new GsonBuilder().create();
        final BearerTokenConfig bearerTokenConfig = new BearerTokenConfig();
        bearerTokenConfig.setAccessToken("dmFsa2FAMTIzNDU2");
        final ApiTokenStorable<BearerTokenConfig> apiTokenStorable = new MemoryTokenStorable<>();
        apiTokenStorable.storeObject(SimpleTokenConfig.HEADER_FIELD_NAME, bearerTokenConfig);
        final OkHttpClient client = LoggerOkHttpClientFactory.builder()
                .registerInterceptor(new HeaderInterceptor(SimpleTokenConfig.HEADER_FIELD_NAME, apiTokenStorable))
                .isDebug(true)
                .maxRequestsCount(1)
                .build()
                .create();
        final SampleApiClient restApiClient = new RestApiFactory.Builder<>(SampleRestApiFactory.class)
                .client(client)
                .endpoint(new SimpleServerEndpoint.Builder()
                        .host("http://test.dev2.mgrnix.com:23680")
                        .path("api")
                        .version("v2")
                        .build())
                .registerCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .registerConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create();
        Assert.assertNotNull(restApiClient);
    }

    @Test
    public void testTokenConfigs() throws IOException {
        final BasicTokenConfig basicTokenConfig = new BasicTokenConfig();
        basicTokenConfig.createAccessToken("valka", "123456");
        final String actual = basicTokenConfig.getAccessToken();
        Assert.assertEquals("Basic dmFsa2FAMTIzNDU2", actual);
        final BearerTokenConfig bearerTokenConfig = new BearerTokenConfig();
        bearerTokenConfig.setAccessToken("dmFsa2FAMTIzNDU2");
        Assert.assertEquals("Bearer dmFsa2FAMTIzNDU2", bearerTokenConfig.getAccessToken());
    }

}
