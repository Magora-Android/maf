package com.magorasystems.mafmodules.network.config;

import com.magorasystems.mafmodules.common.utils.ConverterBase64;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class BasicTokenConfig extends SimpleTokenConfig {

    public void createAccessToken(String login, String password) {
        setAccessToken(ConverterBase64.toBase64(login + "@" + password));
    }

    @Override
    public String getAccessToken() {
        return "Basic " + super.getAccessToken();
    }
}
