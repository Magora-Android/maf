package com.magorasystems.mafmodules.network.model;

import com.magorasystems.mafmodules.network.GenericRestApiFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SampleRestApiFactory extends GenericRestApiFactory<SampleApiClient> {

    public SampleRestApiFactory() {
        super(null);
    }

    public SampleRestApiFactory(Builder<SampleApiClient, SampleRestApiFactory> builder) {
        super(builder);
    }

    @Override
    public SampleApiClient create() {
        return super.create();
    }
}