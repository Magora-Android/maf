package com.magorasystems.mafmodules.authmodule.network;

import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthApiClient {

    String RESOURCE_TOKEN = "auth/token";

    @POST(RESOURCE_TOKEN)
    Observable<SimpleStringAuthSuccessResponse> authToken(
            final @Body AuthRequest authorizationData);

}
