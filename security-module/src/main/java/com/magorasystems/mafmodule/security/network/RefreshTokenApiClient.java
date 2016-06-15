package com.magorasystems.mafmodule.security.network;

import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.protocolapi.auth.dto.request.RefreshTokenRequest;

import retrofit2.http.Body;
import retrofit2.http.PUT;
import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RefreshTokenApiClient {

    String RESOURCE_TOKEN = "auth/token";

    @PUT(RESOURCE_TOKEN)
    Observable<SimpleStringAuthSuccessResponse> refreshToken(@Body RefreshTokenRequest refreshToken);
}
