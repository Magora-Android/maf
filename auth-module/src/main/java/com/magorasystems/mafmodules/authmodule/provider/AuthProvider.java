package com.magorasystems.mafmodules.authmodule.provider;

import com.magorasystems.mafmodules.common.mvp.provider.BaseProvider;
import com.magorasystems.protocolapi.auth.dto.request.MetaAuthRequest;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthProvider<R> extends BaseProvider<R> {

    Observable<R> authToken(final MetaAuthRequest<?> authorization);

    Observable<R> refreshToken();
}
