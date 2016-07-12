package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.mafmodules.utils.JsonStub;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;

import java.util.concurrent.TimeUnit;

import retrofit2.http.Body;
import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockRegistrationRestClient implements RegistrationApiClient {

    @Override
    public Observable<SimpleStringAuthSuccessResponse> registration(@Body AuthRequest request) {
        final SimpleStringAuthSuccessResponse response = JsonStub.generateAuthSuccessResponse();
        return Observable.just(response).delay(5000L, TimeUnit.MILLISECONDS);
    }
}
