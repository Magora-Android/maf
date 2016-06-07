package com.magorasystems.mafmodules.network.config;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class BearerTokenConfig extends SimpleTokenConfig {

    @Override
    public String getAccessToken() {
        return "Bearer " + super.getAccessToken();
    }
}
