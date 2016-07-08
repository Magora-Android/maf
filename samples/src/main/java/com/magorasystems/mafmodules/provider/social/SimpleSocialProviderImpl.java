package com.magorasystems.mafmodules.provider.social;

import com.magorasystems.android.module.social.network.request.SocialRequest;
import com.magorasystems.android.module.social.network.request.SocialRequestMeta;
import com.magorasystems.android.module.social.network.wrapper.SimpleSocialApiClientWrapper;
import com.magorasystems.android.module.social.provider.AbstractSocialProvider;
import com.magorasystems.android.module.social.provider.SimpleSocialProvider;
import com.magorasystems.mafmodule.security.store.AuthPreferencesStorage;
import com.magorasystems.mafmodule.security.store.TokenPreferencesStorage;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.dagger.component.SocialComponent;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import javax.inject.Inject;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialProviderImpl extends AbstractSocialProvider<SocialComponent, SimpleSocialApiClientWrapper, String, StringAuthInfo, SimpleTokenConfig>
        implements SimpleSocialProvider {

    @Inject
    public SimpleSocialProviderImpl(SocialComponent socialComponent,
                                    SchedulersUtils.CoreScheduler scheduler,
                                    SimpleSocialApiClientWrapper restApiClientWrapper,
                                    AuthPreferencesStorage authPreferencesStorage,
                                    TokenPreferencesStorage<SimpleTokenConfig> simplePreferencesTokenStorage) {
        super(socialComponent, scheduler, restApiClientWrapper, authPreferencesStorage, simplePreferencesTokenStorage);
    }

    @Override
    public void inject(SocialComponent socialComponent) {
        socialComponent.inject(this);
    }


    @Override
    protected Observable<SimpleStringAuthSuccessResponse> authorization(SocialRequest<SocialRequestMeta> request) {
        return restApiClientWrapper.authorization(request);
    }

    @Override
    protected SimpleTokenConfig getToken(AuthResponseData<? extends StringAuthInfo> response) {
        return new SimpleTokenConfig(response.getAccessToken(), response.getRefreshToken());
    }
}
