package com.magorasystems.mafmodules.network;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class RefreshTokenRestApiFactory extends GenericRestApiFactory<RefreshTokenApiClient> {

    protected RefreshTokenRestApiFactory(Builder<RefreshTokenApiClient, ? extends RestApiFactory<RefreshTokenApiClient>> builder) {
        super(builder);
    }
}
