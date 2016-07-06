package com.magorasystems.mafmodules.provider.social;

import com.magorasystems.mafmodules.network.request.SocialRequest;
import com.magorasystems.mafmodules.network.request.SocialRequestMeta;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SimpleSocialApiClientWrapper extends SocialApiClientWrapper<SocialRequestMeta, String> {

    Observable<SimpleStringAuthSuccessResponse> authorization(SocialRequest<SocialRequestMeta> request);
}
