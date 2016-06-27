package com.magorasystems.mafmodules.provider.impl;

import com.magorasystems.mafmodule.security.network.RefreshTokenApiClient;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.exception.RestApiException;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.mafmodules.protocolapi.auth.response.StringAuthResponseData;
import com.magorasystems.protocolapi.auth.dto.request.RefreshTokenRequest;
import com.magorasystems.protocolapi.dto.response.SuccessResponse;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class BaseRestProfileDataProvider<COMPONENT, W, T> extends
        AbstractRestProfileDataProvider<COMPONENT, W, T, RefreshTokenApiClient, SimpleTokenConfig, StringApiTokenStorage> {

    public BaseRestProfileDataProvider(COMPONENT component, SchedulersUtils.CoreScheduler scheduler, W restApiClientWrapper, RefreshTokenApiClient refreshTokenApiClient, StringApiTokenStorage storable) {
        super(component, scheduler, restApiClientWrapper, refreshTokenApiClient, storable);
    }


    @Override
    public Observable<StringAuthResponseData> refreshToken() {
        final SimpleTokenConfig tokenConfig = getTokenConfig();
        if (tokenConfig == null) {
            return Observable.error(new RestApiException("token config not set "));
        }
        Observable<SimpleStringAuthSuccessResponse> observable = refreshTokenApiClient.refreshToken(
                new RefreshTokenRequest(tokenConfig.getRefreshToken()))
                .onBackpressureDrop().subscribeOn(scheduler.backgroundThread());
        return observable.map(SuccessResponse::getData);
    };

    @Override
    protected SimpleTokenConfig getTokenConfig() {
        return tokenStorage.restoreObject(SimpleTokenConfig.HEADER_FIELD_NAME);
    }

    protected abstract Observable<? extends SuccessResponse<? super T>> callMyProvider();

};
