package com.magorasystems.mafmodules.interactor;

import com.magorasystems.mafmodules.model.social.RxCommonSocial;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import rx.Subscriber;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleSocialInteractor extends SocialInteractor<String, StringAuthInfo> {

    @Override
    void executeSocialAuthorization(RxCommonSocial social, Subscriber<StringAuthInfo> subscriber);
}
