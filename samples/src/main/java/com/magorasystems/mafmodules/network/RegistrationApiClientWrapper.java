package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.protocolapi.auth.request.RegistrationRequest;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;
import com.magorasystems.protocolapi.dto.response.SuccessResponse;

import java.io.Serializable;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface RegistrationApiClientWrapper<ID extends Serializable, M extends AuthResponseData<? extends AuthInfo<ID>>> {

    Observable<? extends SuccessResponse<? super M>> registration(RegistrationRequest<?> request);
}
