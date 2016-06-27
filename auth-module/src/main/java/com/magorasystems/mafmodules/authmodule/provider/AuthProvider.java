package com.magorasystems.mafmodules.authmodule.provider;

import com.magorasystems.mafmodule.security.provider.BaseRefreshTokenProvider;
import com.magorasystems.mafmodules.common.mvp.provider.BaseProvider;
import com.magorasystems.protocolapi.auth.dto.request.AuthRequest;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface AuthProvider<R> extends BaseProvider<R>, BaseRefreshTokenProvider<R> {

    Observable<R> authToken(final AuthRequest authorization);
}
