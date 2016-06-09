package com.magorasystems.mafmodules.authmodule.interactor;

import com.magorasystems.mafmodules.common.mvp.interactor.BaseInteractor;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;

import rx.Subscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthInteractor<R> extends BaseInteractor<R>{

    void executeAuthToken(AuthRequest authorization, Subscriber<R> subscriber);

    void executeRefreshToken(Subscriber<R> subscriber);

}
