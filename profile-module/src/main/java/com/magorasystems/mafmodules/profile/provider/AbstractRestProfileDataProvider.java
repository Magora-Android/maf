package com.magorasystems.mafmodules.profile.provider;

import com.magorasystems.mafmodule.security.provider.GenericRestRefreshTokenProvider;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.network.config.TokenConfig;
import com.magorasystems.mafmodules.network.store.ApiTokenStorable;
import com.magorasystems.mafmodules.protocolapi.auth.response.StringAuthResponseData;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractRestProfileDataProvider<C, W, T, R, TOKEN extends TokenConfig, S extends ApiTokenStorable<TOKEN>>
        extends GenericRestRefreshTokenProvider<W, C, TOKEN, R, StringAuthResponseData> implements ProfileDataProvider<T> {

    /**
     * @param c                     - dagger component
     * @param scheduler             - background worker
     * @param restApiClientWrapper  - wrapper for profile api
     * @param refreshTokenApiClient - client for refresh token
     * @param tokenStorage          - storage for tokens
     */
    public AbstractRestProfileDataProvider(C c, SchedulersUtils.CoreScheduler scheduler,
                                           W restApiClientWrapper,
                                           R refreshTokenApiClient,
                                           S tokenStorage) {
        super(c, scheduler, restApiClientWrapper, refreshTokenApiClient, tokenStorage);
    }


}
