package com.magorasystems.mafmodules.provider.social;

import com.magorasystems.mafmodules.network.GenericApiClientWrapper;
import com.magorasystems.mafmodules.network.SocialApiClient;
import com.magorasystems.mafmodules.network.request.SocialRequest;
import com.magorasystems.mafmodules.network.request.SocialRequestMeta;
import com.magorasystems.mafmodules.protocolapi.auth.response.SimpleStringAuthSuccessResponse;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialApiClientWrapper extends GenericApiClientWrapper<SocialApiClient>
        implements SocialApiClientWrapper<SocialRequestMeta, String> {

    public SimpleSocialApiClientWrapper(SocialApiClient client) {
        super(client);
    }

    @Override
    public Observable<SimpleStringAuthSuccessResponse> authorization(SocialRequest<SocialRequestMeta> request) {
        return getClient().authToken(request);
    }
}
