package com.magorasystems.mafmodules.provider.registration;

import com.magorasystems.mafmodule.security.store.AuthPreferencesStorage;
import com.magorasystems.mafmodule.security.store.SimplePreferencesTokenStorage;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.store.PreferencesStorable;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.network.SimpleRegistrationApiClientWrapper;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.protocolapi.auth.request.RegistrationRequest;
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
    protected SimpleTokenConfig getTokenConfig(String key) {
        return tokenStorage.restoreObject(SimpleTokenConfig.HEADER_FIELD_NAME);
    }

    @Override
    public void inject(SampleComponent sampleComponent) {

    }

    @Override
    public Observable<StringAuthInfo> registration(RegistrationRequest<?> request) {
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
