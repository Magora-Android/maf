package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.network.response.ProfileSuccessResponse;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserProfileApiClientWrapper extends AbstractProfileApiClientWrapper<ProfileApiClient, UserProfile> {

    public UserProfileApiClientWrapper(ProfileApiClient profileApiClient) {
        super(profileApiClient);
    }

    @Override
    public Observable<ProfileSuccessResponse> getMyProfile() {
        return getClient().getMyProfile();
    }
}
