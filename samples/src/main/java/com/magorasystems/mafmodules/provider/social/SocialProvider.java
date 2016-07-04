package com.magorasystems.mafmodules.provider.social;

import com.magorasystems.mafmodules.common.mvp.provider.BaseProvider;
import com.magorasystems.mafmodules.model.social.RxCommonSocial;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import java.io.Serializable;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface SocialProvider<ID extends Serializable,M  extends AuthInfo<ID>> extends BaseProvider<M> {

    Observable<M> authorization(RxCommonSocial rxCommonSocial);
}
