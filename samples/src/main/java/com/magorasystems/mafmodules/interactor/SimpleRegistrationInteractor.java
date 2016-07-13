package com.magorasystems.mafmodules.interactor;

import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import rx.Subscriber;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleRegistrationInteractor extends RegistrationInteractor<String, StringAuthInfo> {

    @Override
    void executeRegistration(AuthRequest request, Subscriber<StringAuthInfo> subscriber);
}