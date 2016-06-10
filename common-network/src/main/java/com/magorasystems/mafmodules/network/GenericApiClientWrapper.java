package com.magorasystems.mafmodules.network;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class GenericApiClientWrapper<A> implements ApiClientWrapper<A> {

    private final A client;

    public GenericApiClientWrapper(A client) {
        this.client = client;
    }

    @Override
    public A getClient() {
        return client;
    }
}
