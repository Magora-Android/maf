package com.magorasystems.mafmodule.security.provider;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.store.PreferencesStorable;
import com.magorasystems.mafmodules.network.config.TokenConfig;
import com.magorasystems.mafmodules.network.store.ApiTokenStorable;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericRestAuthTokenProvider<W, COMPONENT, TOKEN extends TokenConfig, AUTH, P extends PreferencesStorable<String, AUTH>>
        extends GenericRestTokenProvider<W, COMPONENT, TOKEN> {

    protected P authPreferencesStorage;

    public GenericRestAuthTokenProvider(COMPONENT component, SchedulersUtils.CoreScheduler scheduler,
                                        W restApiClientWrapper, ApiTokenStorable<TOKEN> tokenStorage, P authPreferencesStorage) {
        super(component, scheduler, restApiClientWrapper, tokenStorage);
        this.authPreferencesStorage = authPreferencesStorage;
    }

    protected final void saveUser(String key, AUTH userInfo) {
        authPreferencesStorage.storeObject(key, userInfo);
    }

    protected abstract TOKEN getToken(AuthResponseData<? extends AUTH> responseData);

}
