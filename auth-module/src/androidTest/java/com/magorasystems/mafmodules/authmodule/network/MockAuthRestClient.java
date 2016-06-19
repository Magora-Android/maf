package com.magorasystems.mafmodules.authmodule.network;

import com.magorasystems.mafmodules.authmodule.utils.JsonStub;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;

import java.util.concurrent.TimeUnit;

import retrofit2.http.Body;
import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockAuthRestClient implements AuthApiClient {

    @Override
    public Observable<SimpleStringAuthSuccessResponse> authToken(@Body AuthRequest authorizationData) {
        final SimpleStringAuthSuccessResponse response = JsonStub.generateAuthSuccessResponse();
        return Observable.just(response).delay(1000L, TimeUnit.MILLISECONDS);
    }
}
