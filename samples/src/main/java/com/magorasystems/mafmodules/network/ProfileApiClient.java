package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.network.response.ProfileSuccessResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ProfileApiClient {

    String RESOURCE_PROFILE = "profile";
    String RESOURCE_PROFILE_AVATAR = "profile/avatar";

    @GET(RESOURCE_PROFILE)
    public Observable<ProfileSuccessResponse> getMyProfile();
}
