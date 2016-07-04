package com.magorasystems.mafmodules.network;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SocialRestApiFactory extends GenericRestApiFactory<SocialApiClient> {

    public SocialRestApiFactory(Builder<SocialApiClient, ? extends RestApiFactory<SocialApiClient>> builder) {
        super(builder);
    }
}
