package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.network.response.ProfileSuccessResponse;
import com.magorasystems.mafmodules.profile.network.ProfileApiClientWrapper;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface UserProfileApiClientWrapper extends ProfileApiClientWrapper<UserProfile> {

    @Override
    Observable<ProfileSuccessResponse> getMyProfile();
}
