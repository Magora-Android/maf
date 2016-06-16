package com.magorasystems.mafmodule.security.network;

import com.magorasystems.mafmodules.network.GenericRestApiFactory;
import com.magorasystems.mafmodules.network.RestApiFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class RefreshTokenRestApiFactory extends GenericRestApiFactory<RefreshTokenApiClient> {

    public RefreshTokenRestApiFactory(Builder<RefreshTokenApiClient, ? extends RestApiFactory<RefreshTokenApiClient>> builder) {
        super(builder);
    }
}
