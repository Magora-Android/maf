package com.magorasystems.mafmodules.provider.social;

import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.model.social.RxCommonSocial;
import com.magorasystems.mafmodules.network.request.SocialRequest;
import com.magorasystems.mafmodules.network.request.SocialRequestMeta;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialProviderImpl extends AbstractSocialProvider<SampleComponent, SimpleSocialApiClientWrapper, String, StringAuthInfo>
        implements SimpleSocialProvider {

    public SimpleSocialProviderImpl(SampleComponent sampleComponent, SchedulersUtils.CoreScheduler scheduler, SimpleSocialApiClientWrapper restApiClientWrapper) {
        super(sampleComponent, scheduler, restApiClientWrapper);
    }

    @Override
    public void inject(SampleComponent sampleComponent) {
        sampleComponent.inject(this);
    }

    @Override
    public Observable<StringAuthInfo> authorization(RxCommonSocial rxCommonSocial) {
        final SocialRequest.Builder<SocialRequestMeta> builder = new SocialRequest.Builder<>();
        return rxCommonSocial.login()
                .compose(SchedulersUtils.applySchedulers(scheduler))
                .flatMap(authResult -> {
                    builder.token(authResult.getToken())
                            .type(authResult.getSocialType().name());
                    return rxCommonSocial.profile();
                })
                .map(profile -> builder.meta(
                        new SocialRequestMeta
                                .Builder()
                                .firstName(profile.getSocialUser().getFirstName())
                                .lastName(profile.getSocialUser().getLastName())
                                .nickName(profile.getSocialUser().getName())
                                .build()).build())
                .flatMap(socialRequest -> restApiClientWrapper.authorization(socialRequest)
                        .compose(converter()).map(this::receiveData));
    }
}
