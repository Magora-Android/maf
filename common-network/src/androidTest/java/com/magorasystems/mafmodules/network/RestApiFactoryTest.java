package com.magorasystems.mafmodules.network;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magorasystems.mafmodules.network.config.SimpleServerEndpoint;
import com.magorasystems.mafmodules.network.model.SampleApiClient;
import com.magorasystems.mafmodules.network.model.SampleRestApiFactory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.mockito.Mockito.mock;

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
        OkHttpClient client = mock(OkHttpClient.class);
        final Gson gson = new GsonBuilder().create();
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

}
