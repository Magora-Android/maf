package com.magorasystems.mafmodules.network.provider;

import com.magorasystems.mafmodules.authmodule.provider.impl.SimpleAuthProvider;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.store.SimpleMemoryTokenStorable;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
import com.magorasystems.protocolapi.auth.dto.request.RefreshTokenRequest;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthRestProvider extends RestBaseDataProvider<AuthApiClientWrapper, SampleComponent> implements SimpleAuthProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthRestProvider.class);

    @Inject
    protected SimpleMemoryTokenStorable memoryTokenStorable;

    @Inject
    public AuthRestProvider(HasComponent<SampleComponent> hasComponent, SchedulersUtils.CoreScheduler scheduler, AuthApiClientWrapper restApiClientWrapper) {
        super(hasComponent, scheduler, restApiClientWrapper);
        LOGGER.debug("TokenStorage: {}", memoryTokenStorable);
    }

    @Override
    public void inject(HasComponent<? extends SampleComponent> hasComponent) {
        hasComponent.getComponent().inject(this);
    }

    @Override
    public Observable<StringAuthInfo> authToken(AuthRequest authorization) {
        return restApiClientWrapper.getClient().authToken(authorization)
                .compose(converter())
                .doOnNext(saveTokensToStorage)
                .map(data -> data != null ? data.getAuthInfo() : null);
    }

    @Override
    public Observable<StringAuthInfo> refreshToken() {
        return restApiClientWrapper.getClient().refreshToken(
                new RefreshTokenRequest(memoryTokenStorable.restoreObject(SimpleTokenConfig.HEADER_FIELD_NAME)
                        .getRefreshToken()))
                .compose(converter())
                .doOnNext(saveTokensToStorage)
                .map(data -> data != null ? data.getAuthInfo() : null);
    }

    private final Action1<AuthResponseData<?>> saveTokensToStorage = new Action1<AuthResponseData<?>>() {

        @Override
        public void call(AuthResponseData<?> model) {
            if (model != null) {
                memoryTokenStorable.storeObject(SimpleTokenConfig.HEADER_FIELD_NAME, new SimpleTokenConfig(model.getAccessToken(), model.getRefreshToken()));
            }
        }
    };
}
