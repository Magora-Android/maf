package com.magorasystems.mafmodules.network.exception;

/**
 * @author Valentin S. Bolkonsky.
 *         Proud to Code for Magora Systems/magora-systems.com/magora-systems.ru
 */
public class RestApiException extends RuntimeException {

    public RestApiException(String detailMessage) {
        super(detailMessage);
    }

    public RestApiException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
