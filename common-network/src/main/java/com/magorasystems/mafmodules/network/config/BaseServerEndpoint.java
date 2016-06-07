package com.magorasystems.mafmodules.network.config;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Valentin S. Bolkonsky.
 *         Proud to Code for Magora Systems/magora-systems.com/magora-systems.ru
 */
public abstract class BaseServerEndpoint implements ServerEndpoint {

    private final String host;
    private final String path;
    private final String api;

    protected BaseServerEndpoint(final String host, final String path, final String api) {
        this.host = host;
        this.path = path;
        this.api = api;
    }

    @Override
    public String getUrl() {
        final StringBuilder builder = new StringBuilder();
        builder.append(host);
        if (StringUtils.isNotBlank(path)) {
            builder.append("/").append(path);
        }
        if (StringUtils.isNotBlank(api)) {
            builder.append("/").append(api);
        }
        builder.append('/');
        return String.valueOf(builder);
    }

    @Override
    public String toString() {
        return "BaseServerEndpoint{" +
                "url=" + getUrl() +
                "}";
    }


}
