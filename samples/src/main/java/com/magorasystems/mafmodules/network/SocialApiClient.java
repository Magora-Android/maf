package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.network.request.SocialRequest;
import com.magorasystems.mafmodules.network.request.SocialRequestMeta;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SocialApiClient {

    String RESOURCE_TOKEN = "auth/token";

    @POST(RESOURCE_TOKEN)
    Observable<SimpleStringAuthSuccessResponse> authToken(
            final @Body SocialRequest<SocialRequestMeta> request);
}
