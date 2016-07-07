package com.magorasystems.mafmodules.provider.social;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.store.PreferencesStorable;
import com.magorasystems.mafmodules.network.config.SimpleTokenConfig;
import com.magorasystems.mafmodules.network.config.TokenConfig;
import com.magorasystems.mafmodules.network.provider.RestBaseDataProvider;
import com.magorasystems.mafmodules.network.store.ApiTokenStorable;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;

import java.io.Serializable;

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

    protected final M receiveData(AuthResponseData<M> responseData) {
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
}
