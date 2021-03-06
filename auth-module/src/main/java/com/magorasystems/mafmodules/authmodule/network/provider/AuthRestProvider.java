package com.magorasystems.mafmodules.authmodule.network.provider;

import com.magorasystems.mafmodule.security.network.RefreshTokenApiClient;
import com.magorasystems.mafmodule.security.store.AuthPreferencesStorage;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.authmodule.provider.impl.SimpleAuthProvider;
import com.magorasystems.mafmodule.security.store.SimplePreferencesTokenStorage;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.store.PreferencesStorable;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.exception.RestApiException;
import com.magorasystems.mafmodules.network.provider.RestBaseDataProvider;
import com.magorasystems.mafmodules.network.store.StringApiTokenStorage;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
import com.magorasystems.protocolapi.auth.dto.request.RefreshTokenRequest;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthRestProvider extends RestBaseDataProvider<AuthApiClientWrapper, AuthComponent> implements SimpleAuthProvider {

    @Inject
    protected StringApiTokenStorage tokenStorage;

    @Inject
    protected SimplePreferencesTokenStorage preferencesTokenStorage;

    @Inject
    protected AuthPreferencesStorage authPreferencesStorage;

    @Inject
    protected RefreshTokenApiClient refreshTokenApiClient;

    @Inject
    public AuthRestProvider(AuthComponent component,
                            SchedulersUtils.CoreScheduler scheduler,
                            AuthApiClientWrapper restApiClientWrapper) {
        super(component, scheduler, restApiClientWrapper);
    }

    @Override
    public void inject(AuthComponent component) {
        component.inject(this);
    }

    @Override
    public Observable<StringAuthInfo> authToken(AuthRequest authorization) {
        return restApiClientWrapper.getClient().authToken(authorization)
                .compose(converter())
                .doOnNext(response -> preferencesTokenStorage.storeObject(SimpleTokenConfig.HEADER_FIELD_NAME,
                        new SimpleTokenConfig(response.getAccessToken(), response.getRefreshToken())))
                .doOnNext(saveTokensToStorage)
                .map(data -> data != null ? data.getAuthInfo() : null)
                .doOnNext(user -> authPreferencesStorage.storeObject(PreferencesStorable.PREFERENCE_MY, user));
    }

    @Override
    public Observable<StringAuthInfo> refreshToken() {
        final SimpleTokenConfig tokenConfig = tokenStorage.restoreObject(SimpleTokenConfig.HEADER_FIELD_NAME);
        if (tokenConfig == null) {
            return Observable.error(new RestApiException("token config not set "));
        }
        return refreshTokenApiClient.refreshToken(
                new RefreshTokenRequest(tokenStorage.restoreObject(SimpleTokenConfig.HEADER_FIELD_NAME)
                        .getRefreshToken()))
                .compose(converter())
                .doOnNext(response -> preferencesTokenStorage.storeObject(SimpleTokenConfig.HEADER_FIELD_NAME,
                        new SimpleTokenConfig(response.getAccessToken(), response.getRefreshToken())))
                .doOnNext(saveTokensToStorage)
                .map(data -> data != null ? data.getAuthInfo() : null)
                .doOnNext(user -> authPreferencesStorage.storeObject(PreferencesStorable.PREFERENCE_MY, user));
    }

    private final Action1<AuthResponseData<?>> saveTokensToStorage = new Action1<AuthResponseData<?>>() {

        @Override
        public void call(AuthResponseData<?> model) {
            if (tokenStorage == null) {
                return;
            }
            if (model != null) {
                tokenStorage.storeObject(SimpleTokenConfig.HEADER_FIELD_NAME, new SimpleTokenConfig(model.getAccessToken(), model.getRefreshToken()));
            }
        }
    };
}
