package com.magorasystems.mafmodules.provider.registration;

import com.magorasystems.mafmodule.security.store.AuthPreferencesStorage;
import com.magorasystems.mafmodule.security.store.SimplePreferencesTokenStorage;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.store.PreferencesStorable;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.network.SimpleRegistrationApiClientWrapper;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.registration.provider.RestRegistrationProvider;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationProviderImpl extends RestRegistrationProvider<SampleComponent,
        String, StringAuthInfo, SimpleRegistrationApiClientWrapper, SimpleTokenConfig>
        implements SimpleRegistrationProvider {

    public SimpleRegistrationProviderImpl(SampleComponent sampleComponent,
                                          SchedulersUtils.CoreScheduler scheduler,
                                          SimpleRegistrationApiClientWrapper restApiClientWrapper,
                                          SimplePreferencesTokenStorage tokenStorage,
                                          AuthPreferencesStorage authPreferencesStorage) {
        super(sampleComponent, scheduler, restApiClientWrapper, tokenStorage, authPreferencesStorage);
    }

    @Override
    public void inject(SampleComponent sampleComponent) {
        sampleComponent.inject(this);
    }

    @Override
    public Observable<StringAuthInfo> registration(AuthRequest request) {
        return restApiClientWrapper.registration(request)
                .compose(converter())
                .doOnNext(response -> saveToken(SimpleTokenConfig.HEADER_FIELD_NAME, new SimpleTokenConfig(response.getAccessToken(), response.getRefreshToken())))
                .map(data -> data != null ? data.getAuthInfo() : null)
                .doOnNext(m -> saveUser(PreferencesStorable.PREFERENCE_MY, m));
    }

    @Override
    protected SimpleTokenConfig getToken(AuthResponseData<? extends StringAuthInfo> responseData) {
        return new SimpleTokenConfig(responseData.getAccessToken(), responseData.getRefreshToken());
    }
}
