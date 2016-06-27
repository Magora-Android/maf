package com.magorasystems.mafmodules.provider.impl;

import com.magorasystems.mafmodule.security.network.RefreshTokenApiClient;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.dagger.component.ProfileComponent;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.network.UserProfileApiClientWrapper;
import com.magorasystems.mafmodules.network.response.ProfileSuccessResponse;
import com.magorasystems.mafmodules.network.store.SimpleMemoryTokenStorable;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRestProfileProvider extends BaseRestProfileDataProvider<ProfileComponent, UserProfileApiClientWrapper, UserProfile> {

    public SimpleRestProfileProvider(ProfileComponent component, SchedulersUtils.CoreScheduler scheduler,
                                     UserProfileApiClientWrapper restApiClientWrapper,
                                     RefreshTokenApiClient refreshTokenApiClient,
                                     SimpleMemoryTokenStorable storable) {
        super(component, scheduler, restApiClientWrapper, refreshTokenApiClient, storable);
    }

    @Override
    public void inject(ProfileComponent component) {
        component.inject(this);
    }

    @Override
    public Observable<UserProfile> getMyProfile() {
        Observable<ProfileSuccessResponse> observable = callMyProvider();
        if (refreshTokenApiClient == null) {
            return observable.compose(converter());
        } else {
            return observable.onBackpressureDrop().subscribeOn(scheduler.backgroundThread())
                    .compose(commonTransformer(Observable.defer(this::callMyProvider), refreshToken()));

        }
    }

    @Override
    protected Observable<ProfileSuccessResponse> callMyProvider() {
        return restApiClientWrapper.getMyProfile();
    }
}
