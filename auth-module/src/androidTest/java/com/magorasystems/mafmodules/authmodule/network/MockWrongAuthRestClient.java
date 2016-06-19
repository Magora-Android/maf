package com.magorasystems.mafmodules.authmodule.network;

import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;

import java.net.UnknownHostException;

import retrofit2.http.Body;
import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockWrongAuthRestClient implements AuthApiClient {

    @Override
    public Observable<SimpleStringAuthSuccessResponse> authToken(@Body AuthRequest authorizationData) {
        return Observable.error(new UnknownHostException("Unable to resolve host \"test.ru \": No address associated with hostname"));
    }
}
