package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;
import com.magorasystems.mafmodules.protocolapi.auth.response.StringAuthResponseData;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleRegistrationApiClientWrapper extends RegistrationApiClientWrapper<String, StringAuthResponseData> {

    @Override
    Observable<SimpleStringAuthSuccessResponse> registration(AuthRequest request);
}
