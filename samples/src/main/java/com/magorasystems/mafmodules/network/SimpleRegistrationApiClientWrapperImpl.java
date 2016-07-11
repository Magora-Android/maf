package com.magorasystems.mafmodules.network;

import com.magorasystems.mafmodules.protocolapi.auth.request.RegistrationRequest;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationApiClientWrapperImpl extends GenericApiClientWrapper<RegistrationApiClient> implements SimpleRegistrationApiClientWrapper {

    public SimpleRegistrationApiClientWrapperImpl(RegistrationApiClient client) {
        super(client);
    }

    @Override
    public Observable<SimpleStringAuthSuccessResponse> registration(RegistrationRequest<?> request) {
        return getClient().registration(request);
    }
}
