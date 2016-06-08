package com.magorasystems.mafmodules;

import com.magorasystems.mafmodules.authmodule.provider.impl.SimpleAuthProvider;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.network.AuthApiClientWrapper;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.provider.RestBaseDataProvider;
import com.magorasystems.mafmodules.network.store.SimpleMemoryTokenStorable;
import com.magorasystems.mafmodules.protocolapi.auth.response.StringAuthResponseData;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
import com.magorasystems.protocolapi.auth.dto.request.RefreshTokenRequest;

import javax.inject.Inject;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthRestProvider extends RestBaseDataProvider<AuthApiClientWrapper, SampleComponent> implements SimpleAuthProvider {

    @Inject
    protected SimpleMemoryTokenStorable memoryTokenStorable;

    public AuthRestProvider(HasComponent<SampleComponent> hasComponent, SchedulersUtils.CoreScheduler scheduler, AuthApiClientWrapper restApiClientWrapper) {
        super(hasComponent, scheduler, restApiClientWrapper);
    }

    @Override
    public void inject(HasComponent<? extends SampleComponent> hasComponent) {
        hasComponent.getComponent().inject(this);
    }

    @Override
    public Observable<StringAuthResponseData> authToken(AuthRequest authorization) {
        return restApiClientWrapper.getClient().authToken(authorization)
                .compose(converter());
    }

    @Override
    public Observable<StringAuthResponseData> refreshToken() {
        return restApiClientWrapper.getClient().refreshToken(
                new RefreshTokenRequest(memoryTokenStorable.restoreObject(SimpleTokenConfig.HEADER_FIELD_NAME)
                        .getRefreshToken()))
                .compose(converter())
                .doOnNext(model -> {
                    memoryTokenStorable.remove(SimpleTokenConfig.HEADER_FIELD_NAME);
                    memoryTokenStorable.storeObject(SimpleTokenConfig.HEADER_FIELD_NAME, new SimpleTokenConfig(model.getAccessToken(), model.getRefreshToken()));
                });
    }
}
