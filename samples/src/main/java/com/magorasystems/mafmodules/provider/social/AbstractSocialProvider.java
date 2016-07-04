package com.magorasystems.mafmodules.provider.social;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.provider.RestBaseDataProvider;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractSocialProvider<COMPONENT, WRAPPER, ID extends Serializable, M  extends AuthInfo<ID>> extends RestBaseDataProvider<WRAPPER, COMPONENT>
        implements SocialProvider<ID,M> {


    public AbstractSocialProvider(COMPONENT component, SchedulersUtils.CoreScheduler scheduler,
                                  WRAPPER restApiClientWrapper) {
        super(component, scheduler, restApiClientWrapper);
    }

    protected final  M receiveData(AuthResponseData<M> responseData) {
        if (responseData != null) {
            return responseData.getAuthInfo();
        }
        return null;
    }
}
