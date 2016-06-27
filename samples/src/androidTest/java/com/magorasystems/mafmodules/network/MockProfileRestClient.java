package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.network.response.ProfileSuccessResponse;
import com.magorasystems.mafmodules.utils.JsonStub;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class MockProfileRestClient implements ProfileApiClient {

    @Override
    public Observable<ProfileSuccessResponse> getMyProfile() {
        final ProfileSuccessResponse response = JsonStub.generateProfileSuccessResponse();
        return Observable.just(response).delay(1000L, TimeUnit.MILLISECONDS);
    }
}
