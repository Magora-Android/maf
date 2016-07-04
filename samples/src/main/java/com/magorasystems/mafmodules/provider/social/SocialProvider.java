package com.magorasystems.mafmodules.provider.social;

import com.magorasystems.mafmodules.common.mvp.provider.BaseProvider;
import com.magorasystems.mafmodules.model.social.RxCommonSocial;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;
import com.magorasystems.protocolapi.auth.dto.response.AuthResponseData;

import java.io.Serializable;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SocialProvider<ID extends Serializable> extends BaseProvider<AuthInfo<ID>> {

    Observable<? extends AuthResponseData<? extends AuthInfo<ID>>> authorization(RxCommonSocial rxCommonSocial);
}
