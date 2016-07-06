package com.magorasystems.mafmodules.provider.social;

import com.magorasystems.mafmodules.network.GenericApiClientWrapper;
import com.magorasystems.mafmodules.network.SocialApiClient;
import com.magorasystems.mafmodules.network.request.SocialRequest;
import com.magorasystems.mafmodules.network.request.SocialRequestMeta;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;

import javax.inject.Inject;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialApiClientWrapperImpl extends GenericApiClientWrapper<SocialApiClient>
        implements SimpleSocialApiClientWrapper {

    @Inject
    public SimpleSocialApiClientWrapperImpl(SocialApiClient client) {
        super(client);
    }

    @Override
    public Observable<SimpleStringAuthSuccessResponse> authorization(SocialRequest<SocialRequestMeta> request) {
        return getClient().authToken(request);
    }
}
