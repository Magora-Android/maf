package com.magorasystems.mafmodules.profile.network;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractProfileApiClientWrapper<CLIENT, P> implements ProfileApiClientWrapper<P> {

    private CLIENT apiClient;

    protected AbstractProfileApiClientWrapper(CLIENT client) {
        apiClient = client;
    }

    protected CLIENT getClient() {
        return apiClient;
    }
}
