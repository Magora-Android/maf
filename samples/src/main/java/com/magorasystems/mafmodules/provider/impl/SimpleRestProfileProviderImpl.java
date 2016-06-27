package com.magorasystems.mafmodules.provider.impl;

import android.content.Context;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.magorasystems.mafmodule.security.network.RefreshTokenApiClient;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.component.ProfileComponent;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.network.UserProfileApiClientWrapper;
import com.magorasystems.mafmodules.network.response.ProfileSuccessResponse;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;
import com.magorasystems.mafmodules.provider.SimpleRestProfileProvider;

import javax.inject.Inject;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRestProfileProviderImpl extends BaseRestProfileDataProvider<ProfileComponent,
        UserProfileApiClientWrapper, UserProfile> implements SimpleRestProfileProvider {

    @Inject
    public SimpleRestProfileProviderImpl(Context context, SchedulersUtils.CoreScheduler scheduler,
                                         UserProfileApiClientWrapper restApiClientWrapper,
                                         RefreshTokenApiClient refreshTokenApiClient,
                                         StringApiTokenStorage storable) {
        super((ProfileComponent) ((HasComponent<?>) context).getComponent(ProfileComponent.class.getSimpleName()),
                scheduler, restApiClientWrapper, refreshTokenApiClient, storable);
    }

    @Override
    public void inject(ProfileComponent component) {
        component.inject(this);
    }

    @Override
    @RxLogObservable
    public Observable<UserProfile> getMyProfile() {
        if (refreshTokenApiClient == null) {
            return callMyProvider().compose(converter());
        } else {
            return callMyProvider().compose(
                    commonTransformer(callMyProvider(),
                            refreshToken()));

        }
    }

    @Override
    @RxLogObservable
    protected Observable<ProfileSuccessResponse> callMyProvider() {
        return Observable.defer(restApiClientWrapper::getMyProfile);
    }
}
