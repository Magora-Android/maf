package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.network.response.ProfileSuccessResponse;
import com.magorasystems.mafmodules.profile.network.AbstractProfileApiClientWrapper;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserProfileApiClientWrapperImpl extends AbstractProfileApiClientWrapper<ProfileApiClient, UserProfile>
        implements UserProfileApiClientWrapper {

    public UserProfileApiClientWrapperImpl(ProfileApiClient profileApiClient) {
        super(profileApiClient);
    }

    @Override
    public Observable<ProfileSuccessResponse> getMyProfile() {
        return getClient().getMyProfile();
    }
}
