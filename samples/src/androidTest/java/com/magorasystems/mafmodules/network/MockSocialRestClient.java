package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.network.request.SocialRequest;
import com.magorasystems.mafmodules.network.request.SocialRequestMeta;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.mafmodules.utils.JsonStub;

import java.util.concurrent.TimeUnit;

import retrofit2.http.Body;
import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockSocialRestClient implements SocialApiClient {

    @Override
    public Observable<SimpleStringAuthSuccessResponse> authToken(@Body SocialRequest<SocialRequestMeta> request) {
        final SimpleStringAuthSuccessResponse response = JsonStub.generateAuthSuccessResponse();
        return Observable.just(response).delay(5000L, TimeUnit.MILLISECONDS);
    }
}
