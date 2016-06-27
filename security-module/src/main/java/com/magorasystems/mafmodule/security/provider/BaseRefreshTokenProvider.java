package com.magorasystems.mafmodule.security.provider;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface BaseRefreshTokenProvider<R> {

    Observable<R> refreshToken();
}
