package com.magorasystems.mafmodules.common.application;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ApplicationSettings {

    int getMaxRequestCount();

    String getApiHost();

    String getApiPath();

    String getApiVersion();

    int getNetworkRetryCount();

    long getNetworkDelayAttempt();

}
