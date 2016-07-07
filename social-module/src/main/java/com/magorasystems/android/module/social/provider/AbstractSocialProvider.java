package com.magorasystems.android.module.social.provider;

import com.magorasystems.android.module.social.model.RxCommonSocial;
import com.magorasystems.android.module.social.network.request.SocialRequest;
import com.magorasystems.android.module.social.network.request.SocialRequestMeta;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.store.PreferencesStorable;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.config.TokenConfig;
import com.magorasystems.mafmodules.network.provider.RestBaseDataProvider;
import com.magorasystems.mafmodules.network.store.ApiTokenStorable;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;
import com.magorasystems.protocolapi.dto.response.SuccessResponse;
import com.mgrmobi.sdk.social.android.model.SocialUser;

import java.io.Serializable;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractSocialProvider<COMPONENT, WRAPPER, ID extends Serializable, M extends AuthInfo<ID>, TOKEN extends TokenConfig> extends RestBaseDataProvider<WRAPPER, COMPONENT>
        implements SocialProvider<ID, M> {

    protected PreferencesStorable<String, M> authPreferencesStorage;
    protected ApiTokenStorable<TOKEN> preferencesTokenStorage;

    public AbstractSocialProvider(COMPONENT component, SchedulersUtils.CoreScheduler scheduler,
                                  WRAPPER restApiClientWrapper, PreferencesStorable<String, M> authPreferencesStorage,
                                  ApiTokenStorable<TOKEN> preferencesTokenStorage) {
        super(component, scheduler, restApiClientWrapper);
        this.authPreferencesStorage = authPreferencesStorage;
        this.preferencesTokenStorage = preferencesTokenStorage;
    }

    @Override
    public Observable<M> authorization(RxCommonSocial rxCommonSocial) {
        final SocialRequest.Builder<SocialRequestMeta> builder = new SocialRequest.Builder<>();
        return rxCommonSocial.login()
                .compose(SchedulersUtils.applySchedulers(scheduler))
                .flatMap(authResult -> {
                    builder.token(authResult.getToken())
                            .type(authResult.getSocialType().name());
                    return rxCommonSocial.profile();
                })
                .map(profile -> builder.meta(
                        getMeta(profile.getSocialUser())).build())
                .flatMap(socialRequest -> authorization(socialRequest)
                        .compose(converter())
                        .doOnNext(response -> saveToken(getToken(response)))
                        .map(this::receiveData))
                .doOnNext(this::saveUser);
    }

    protected final M receiveData(AuthResponseData<? extends M> responseData) {
        if (responseData != null) {
            return responseData.getAuthInfo();
        }
        return null;
    }

    protected final void saveUser(M userInfo) {
        authPreferencesStorage.storeObject(PreferencesStorable.PREFERENCE_MY, userInfo);
    }

    protected final void saveToken(TOKEN token) {
        preferencesTokenStorage.storeObject(SimpleTokenConfig.HEADER_FIELD_NAME, token);
    }

    protected abstract Observable<? extends SuccessResponse<? extends AuthResponseData<? extends M>>>
    authorization(SocialRequest<SocialRequestMeta> request);

    protected abstract TOKEN getToken(AuthResponseData<? extends M> responseData);

    protected SocialRequestMeta getMeta(SocialUser profile) {
        return new SocialRequestMeta
                .Builder()
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .nickName(profile.getName())
                .build();
    }
}
