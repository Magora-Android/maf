package com.magorasystems.mafmodules.interactor.impl;

import com.magorasystems.mafmodules.common.mvp.interactor.CommonInteractor;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.interactor.RegistrationInteractor;
import com.magorasystems.mafmodules.provider.registration.RegistrationProvider;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

import rx.Subscriber;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractRegistrationInteractor<M extends AuthInfo<? extends Serializable>> extends CommonInteractor<M> implements RegistrationInteractor<M> {

    protected AbstractRegistrationInteractor(SchedulersUtils.CoreScheduler scheduler) {
        super(scheduler);
    }

    @Override
    public void executeRegistration(AuthRequest request, Subscriber<M> subscriber) {
        execute(getProfileProvider().registration(request), subscriber);
    }

    protected abstract RegistrationProvider<M> getProfileProvider();
}
