package com.magorasystems.mafmodules.authmodule.interactor;

import com.magorasystems.mafmodules.authmodule.provider.AuthProvider;
import com.magorasystems.mafmodules.common.mvp.interactor.CommonInteractor;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.protocolapi.auth.dto.request.MetaAuthRequest;

import rx.Subscriber;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class BaseAuthInteractor<COMPONENT, R> extends CommonInteractor<COMPONENT, R> implements AuthInteractor<R> {

    protected BaseAuthInteractor(HasComponent<? extends COMPONENT> hasComponent, SchedulersUtils.CoreScheduler scheduler) {
        super(hasComponent, scheduler);
    }

    @Override
    public void executeAuthToken(MetaAuthRequest<?> authorization, Subscriber<R> subscriber) {
        execute(getAuthProvider().authToken(authorization), subscriber);
    }

    @Override
    public void executeRefreshToken(Subscriber<R> subscriber) {
        execute(getAuthProvider().refreshToken(), subscriber);
    }

    protected abstract AuthProvider<R> getAuthProvider();
}
