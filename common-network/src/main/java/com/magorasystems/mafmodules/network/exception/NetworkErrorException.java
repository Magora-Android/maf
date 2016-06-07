package com.magorasystems.mafmodules.network.exception;

import com.magorasystems.protocolapi.dto.response.ErrorResponse;
import com.magorasystems.protocolapi.dto.response.HttpCodes;


/**
 * @author Valentin S. Bolkonsky.
 *         Proud to Code for Magora Systems/magora-systems.com/magora-systems.ru
 */
public class NetworkErrorException extends RuntimeException {

    private final ErrorResponse errors;
    private final int httpCode;

    public NetworkErrorException(int httpCode, ErrorResponse errors) {
        this(httpCode, errors, errors.getMessage(), null);
    }

    public NetworkErrorException(ErrorResponse errors, String detailMessage) {
        this(HttpCodes.HTTP_ERROR_INTERNAL_ERROR, errors, detailMessage, null);
    }

    public NetworkErrorException(int httpCode, ErrorResponse errors, Throwable throwable) {
        this(httpCode, errors, errors.getMessage(), throwable);
    }

    public NetworkErrorException(int httpCode, ErrorResponse errors, String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        this.errors = errors;
        this.httpCode = httpCode;
    }

    public boolean isTokenExpired() {
        return httpCode == HttpCodes.HTTP_SECURITY_FORBIDDEN;
    }

    public String getCode() {
        return errors.getCode();
    }


}
