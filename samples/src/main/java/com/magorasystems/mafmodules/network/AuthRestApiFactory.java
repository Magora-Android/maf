package com.magorasystems.mafmodules.network;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthRestApiFactory extends GenericRestApiFactory<AuthApiClient> {

    public AuthRestApiFactory(Builder<AuthApiClient, ? extends RestApiFactory<AuthApiClient>> builder) {
        super(builder);
    }

}
