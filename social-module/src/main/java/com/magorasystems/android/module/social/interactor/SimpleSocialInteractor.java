package com.magorasystems.android.module.social.interactor;

import com.magorasystems.android.module.social.model.RxCommonSocial;
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
