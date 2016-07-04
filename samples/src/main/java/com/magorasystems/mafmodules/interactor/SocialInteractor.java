package com.magorasystems.mafmodules.interactor;

import com.magorasystems.mafmodules.common.mvp.interactor.BaseInteractor;
import com.magorasystems.mafmodules.model.social.RxCommonSocial;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;

import java.io.Serializable;

import rx.Subscriber;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SocialInteractor<ID extends Serializable>
        extends BaseInteractor<AuthResponseData<? extends AuthInfo<ID>>> {

    void executeSocialAuthorization(RxCommonSocial social, Subscriber<AuthResponseData<? extends AuthInfo<ID>>> subscriber);
}
