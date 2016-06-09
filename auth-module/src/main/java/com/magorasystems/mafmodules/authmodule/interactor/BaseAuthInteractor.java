package com.magorasystems.mafmodules.authmodule.interactor;

import com.magorasystems.mafmodules.authmodule.provider.AuthProvider;
import com.magorasystems.mafmodules.common.mvp.interactor.CommonInteractor;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;

import rx.Subscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class BaseAuthInteractor<R> extends CommonInteractor<R> implements AuthInteractor<R> {

    protected BaseAuthInteractor( SchedulersUtils.CoreScheduler scheduler) {
        super(scheduler);
    }

    @Override
    public void executeAuthToken(AuthRequest authorization, Subscriber<R> subscriber) {
        execute(getAuthProvider().authToken(authorization), subscriber);
    }

    @Override
    public void executeRefreshToken(Subscriber<R> subscriber) {
        execute(getAuthProvider().refreshToken(), subscriber);
    }

    protected abstract AuthProvider<? extends R> getAuthProvider();
}
