package com.magorasystems.mafmodules.authmodule.interactor;

import com.magorasystems.protocolapi.auth.dto.request.MetaAuthRequest;

import rx.Subscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthInteractor<R> {

    void executeAuthToken(MetaAuthRequest<?> authorization, Subscriber<R> subscriber);

    void executeRefreshToken(Subscriber<R> subscriber);

}
