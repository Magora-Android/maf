package com.magorasystems.mafmodules.network;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class ApiClientWrapper<A> {

    private final A client;

    public ApiClientWrapper(A client) {
        this.client = client;
    }

    public A getClient() {
        return client;
    }
}
