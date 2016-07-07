package com.magorasystems.mafmodules.provider.social;

import com.magorasystems.mafmodule.security.store.AuthPreferencesStorage;
import com.magorasystems.mafmodule.security.store.TokenPreferencesStorage;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.dagger.component.SocialComponent;
import com.magorasystems.mafmodules.model.social.RxCommonSocial;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.request.SocialRequest;
import com.magorasystems.mafmodules.network.request.SocialRequestMeta;
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
    public Observable<StringAuthInfo> authorization(RxCommonSocial rxCommonSocial) {
        final SocialRequest.Builder<SocialRequestMeta> builder = new SocialRequest.Builder<>();
        return rxCommonSocial.login()
                .compose(SchedulersUtils.applySchedulers(scheduler))
                .flatMap(authResult -> {
                    builder.token(authResult.getToken())
                            .type(authResult.getSocialType().name());
                    return rxCommonSocial.profile();
                })
                .map(profile -> builder.meta(
                        new SocialRequestMeta
                                .Builder()
                                .firstName(profile.getSocialUser().getFirstName())
                                .lastName(profile.getSocialUser().getLastName())
                                .nickName(profile.getSocialUser().getName())
                                .build()).build())
                .flatMap(socialRequest -> restApiClientWrapper.authorization(socialRequest)
                        .compose(converter())
                        .doOnNext(response -> saveToken(new SimpleTokenConfig(response.getAccessToken(), response.getRefreshToken())))
                        .map(this::receiveData))
                .doOnNext(this::saveUser);
    }
}
