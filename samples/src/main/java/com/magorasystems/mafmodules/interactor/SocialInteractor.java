package com.magorasystems.mafmodules.interactor;

import com.magorasystems.mafmodules.common.mvp.interactor.BaseInteractor;
import com.magorasystems.mafmodules.model.social.RxCommonSocial;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

import rx.Subscriber;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SocialInteractor<ID extends Serializable, M extends AuthInfo<ID>>
        extends BaseInteractor<M> {

    void executeSocialAuthorization(RxCommonSocial social, Subscriber<M> subscriber);
}
