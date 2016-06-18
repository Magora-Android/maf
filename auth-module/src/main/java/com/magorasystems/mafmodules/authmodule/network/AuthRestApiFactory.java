package com.magorasystems.mafmodules.authmodule.network;

import com.magorasystems.mafmodules.network.GenericRestApiFactory;
import com.magorasystems.mafmodules.network.RestApiFactory;

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
