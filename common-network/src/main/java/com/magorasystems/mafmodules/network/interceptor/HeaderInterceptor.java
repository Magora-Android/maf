package com.magorasystems.mafmodules.network.interceptor;

import com.magorasystems.mafmodules.network.config.TokenConfig;
import com.magorasystems.mafmodules.network.store.ApiTokenStorable;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class HeaderInterceptor implements Interceptor {

    private final ApiTokenStorable<?> storable;
    private final String tokenHeaderName;

    public HeaderInterceptor(final String tokenName, final ApiTokenStorable<?> storable) {
        this.storable = storable;
        this.tokenHeaderName = tokenName;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        final TokenConfig token = storable.restoreObject(tokenHeaderName);
        if (null != token && StringUtils.isNotBlank(token.getAccessToken())) {
            request = request.newBuilder()
                    .addHeader(tokenHeaderName, token.getAccessToken())
                    .build();
        }
        return chain.proceed(request);
    }
}
