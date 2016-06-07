package com.magorasystems.mafmodules.network.config;

/**
 * @author Valentin S. Bolkonsky.
 *         Proud to Code for Magora Systems/magora-systems.com/magora-systems.ru
 */
public interface TokenConfig {

    String getAccessToken();

    boolean isRefreshTokenSet();

    void setAccessToken(final String accessToken);

    void setRefreshToken(final String refreshToken);


}
