package com.magorasystems.mafmodules.network;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthApiClientWrapperImpl extends GenericApiClientWrapper<AuthApiClient> implements AuthApiClientWrapper {

    @Inject
    public AuthApiClientWrapperImpl(AuthApiClient client) {
        super(client);
    }
}
