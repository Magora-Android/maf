package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.protocolapi.auth.request.RegistrationRequest;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationApiClient {

    String RESOURCE_REGISTRATION = "registration/internal/principals";

    @POST(RESOURCE_REGISTRATION)
    Observable<SimpleStringAuthSuccessResponse> registration(@Body RegistrationRequest<?> request);
}
