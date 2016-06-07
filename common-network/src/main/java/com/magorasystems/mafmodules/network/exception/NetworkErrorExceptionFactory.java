package com.magorasystems.mafmodules.network.exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.magorasystems.protocolapi.dto.response.ErrorResponse;

import java.io.IOException;
import java.nio.charset.Charset;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class NetworkErrorExceptionFactory {

    private NetworkErrorExceptionFactory() {

    }

    public static NetworkErrorException fromHttpException(final HttpException httpException) {
        try {
            final String jsonString = new String(httpException.response().errorBody().bytes(),
                    Charset.defaultCharset());
            final Gson gson = new GsonBuilder().create();
            final ErrorResponse errorResponse = gson.fromJson(jsonString, ErrorResponse.class);
            return new NetworkErrorException(httpException.code(), errorResponse, "business logic error on server ", httpException);
        } catch (JsonSyntaxException | NullPointerException | IOException e) {
            return new NetworkErrorException(httpException.code(), null, "network error ", httpException);
        }
    }
}
