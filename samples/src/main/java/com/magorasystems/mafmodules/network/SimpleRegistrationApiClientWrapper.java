package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.protocolapi.auth.request.RegistrationRequest;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.mafmodules.protocolapi.auth.response.StringAuthResponseData;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleRegistrationApiClientWrapper extends RegistrationApiClientWrapper<String, StringAuthResponseData> {

    @Override
    Observable<SimpleStringAuthSuccessResponse> registration(RegistrationRequest<?> request);
}
