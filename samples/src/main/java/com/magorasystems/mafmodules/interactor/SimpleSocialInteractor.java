package com.magorasystems.mafmodules.interactor;

import com.magorasystems.mafmodules.model.social.RxCommonSocial;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;

import rx.Subscriber;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleSocialInteractor extends SocialInteractor<String> {

    @Override
    void executeSocialAuthorization(RxCommonSocial social, Subscriber<AuthResponseData<? extends AuthInfo<String>>> subscriber);
}
