package com.magorasystems.mafmodules.network;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class RegistrationRestApiFactory extends GenericRestApiFactory<RegistrationApiClient> {

    public RegistrationRestApiFactory(Builder<RegistrationApiClient, ? extends RestApiFactory<RegistrationApiClient>> builder) {
        super(builder);
    }
}
