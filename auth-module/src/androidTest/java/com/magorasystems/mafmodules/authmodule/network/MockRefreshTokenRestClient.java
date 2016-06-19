package com.magorasystems.mafmodules.authmodule.network;

import com.google.gson.Gson;
import com.magorasystems.mafmodule.security.network.RefreshTokenApiClient;
import com.magorasystems.mafmodules.authmodule.utils.JsonStub;
import com.magorasystems.mafmodules.network.config.ServerEndpoint;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.protocolapi.auth.dto.request.RefreshTokenRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.http.Body;
import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockRefreshTokenRestClient implements RefreshTokenApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockRefreshTokenRestClient.class);

    @Inject
    public MockRefreshTokenRestClient(ServerEndpoint serverEndpoint, Gson gson, OkHttpClient client) {
        LOGGER.debug("{}\n{}\n{}", serverEndpoint, gson, client);
    }

    @Override
    public Observable<SimpleStringAuthSuccessResponse> refreshToken(@Body RefreshTokenRequest refreshToken) {
        final SimpleStringAuthSuccessResponse response = JsonStub.generateAuthSuccessResponse();
        return Observable.just(response).delay(1000L, TimeUnit.MILLISECONDS);
    }
}
