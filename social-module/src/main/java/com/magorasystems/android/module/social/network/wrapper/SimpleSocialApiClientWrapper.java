package com.magorasystems.android.module.social.network.wrapper;

import com.magorasystems.android.module.social.network.request.SocialRequest;
import com.magorasystems.android.module.social.network.request.SocialRequestMeta;
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
