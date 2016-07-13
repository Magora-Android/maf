package com.magorasystems.mafmodules.provider.registration;

import com.magorasystems.mafmodule.security.provider.GenericRestAuthTokenProvider;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.store.PreferencesStorable;
import com.magorasystems.mafmodules.network.RegistrationApiClientWrapper;
import com.magorasystems.mafmodules.network.config.TokenConfig;
import com.magorasystems.mafmodules.network.store.ApiTokenStorable;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class RestRegistrationProvider<COMPONENT, ID extends Serializable, M extends AuthInfo<ID>,
        WRAPPER extends RegistrationApiClientWrapper<ID, ? extends AuthResponseData<M>>, TOKEN extends TokenConfig>
        extends GenericRestAuthTokenProvider<WRAPPER, COMPONENT, TOKEN, M,PreferencesStorable<String, M>>
        implements RegistrationProvider<M> {

    protected RestRegistrationProvider(COMPONENT component, SchedulersUtils.CoreScheduler scheduler, WRAPPER restApiClientWrapper,
                                       ApiTokenStorable<TOKEN> tokenStorage, PreferencesStorable<String, M> authPreferencesStorage) {
        super(component, scheduler, restApiClientWrapper, tokenStorage, authPreferencesStorage);
    }
}
