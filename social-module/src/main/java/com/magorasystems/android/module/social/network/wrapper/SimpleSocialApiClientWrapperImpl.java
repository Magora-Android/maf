package com.magorasystems.android.module.social.network.wrapper;

import com.magorasystems.mafmodules.network.GenericApiClientWrapper;
import com.magorasystems.android.module.social.network.SocialApiClient;
import com.magorasystems.android.module.social.network.request.SocialRequest;
import com.magorasystems.android.module.social.network.request.SocialRequestMeta;
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
