package com.magorasystems.mafmodules.registration.network;

import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;
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

    Observable<? extends SuccessResponse<? super M>> registration(AuthRequest request);
}
