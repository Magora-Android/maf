package com.magorasystems.mafmodules.network.config;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: Valentin S. Bolkonsky
 * Developed by Magora Team (magora-systems.com). 2015.
 */
public class SimpleTokenConfig implements TokenConfig {

    public static final String HEADER_FIELD_NAME = "Authorization";

    private String accessToken;
    private String refreshToken;

    public SimpleTokenConfig() {
    }

    public SimpleTokenConfig(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public boolean isRefreshTokenSet() {
        return StringUtils.isNotBlank(refreshToken);
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    @Override
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "SimpleTokenConfig{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken is set='" + isRefreshTokenSet() + '\'' +
                '}';
    }
}
