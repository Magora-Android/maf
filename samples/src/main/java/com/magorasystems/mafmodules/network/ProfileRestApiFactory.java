package com.magorasystems.mafmodules.network;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class ProfileRestApiFactory extends GenericRestApiFactory<ProfileApiClient> {

    public ProfileRestApiFactory(Builder<ProfileApiClient, ? extends RestApiFactory<ProfileApiClient>> builder) {
        super(builder);
    }
}
