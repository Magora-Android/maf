package com.magorasystems.android.module.social.interactor;

import com.magorasystems.mafmodules.common.mvp.interactor.BaseInteractor;
import com.magorasystems.android.module.social.model.RxCommonSocial;
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
