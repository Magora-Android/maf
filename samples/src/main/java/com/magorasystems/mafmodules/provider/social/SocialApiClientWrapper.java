package com.magorasystems.mafmodules.provider.social;

import com.magorasystems.mafmodules.network.request.SocialRequest;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;
import com.magorasystems.protocolapi.auth.dto.response.AuthSuccessResponse;

import java.io.Serializable;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SocialApiClientWrapper<META, ID extends Serializable> {

    Observable<? extends AuthSuccessResponse<? extends AuthResponseData<? extends AuthInfo<ID>>>>
    authorization(SocialRequest<META> request);

}
