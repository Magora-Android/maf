package com.magorasystems.mafmodules.interactor;

import com.magorasystems.mafmodules.common.mvp.interactor.BaseInteractor;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

import rx.Subscriber;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationInteractor<M extends AuthInfo<? extends Serializable>> extends BaseInteractor<M> {

    void executeRegistration(AuthRequest request, Subscriber<M> subscriber);
}
