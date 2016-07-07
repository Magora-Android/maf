package com.magorasystems.android.module.social.network;

import com.magorasystems.mafmodules.network.GenericRestApiFactory;
import com.magorasystems.mafmodules.network.RestApiFactory;

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
