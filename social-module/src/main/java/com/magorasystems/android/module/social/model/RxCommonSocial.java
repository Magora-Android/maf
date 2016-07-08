package com.magorasystems.android.module.social.model;

import android.app.Activity;
import android.content.Intent;

import com.magorasystems.android.module.social.model.exception.RxSocialCancelException;
import com.magorasystems.android.module.social.model.exception.RxSocialException;
import com.magorasystems.android.module.social.model.model.RxSocialAuthResult;
import com.magorasystems.android.module.social.model.model.RxSocialProfileResult;
import com.mgrmobi.sdk.social.android.AndroidBaseSocialNetwork;
import com.mgrmobi.sdk.social.base.SocialAuthorizationListener;
import com.mgrmobi.sdk.social.base.SocialCancelReason;
import com.mgrmobi.sdk.social.base.SocialNetwork;
import com.mgrmobi.sdk.social.base.SocialProfileListener;
import com.mgrmobi.sdk.social.base.SocialType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class RxCommonSocial {

    private static final Logger LOGGER = LoggerFactory.getLogger(RxCommonSocial.class);

    private AndroidBaseSocialNetwork socialNetwork;
    private Activity activity;

    private RxCommonSocial() {

    }

    public SocialType getType() {
        return socialNetwork.getSocialType();
    }

    public static RxCommonSocial create(AndroidBaseSocialNetwork socialNetwork, Activity activity) {
        RxCommonSocial commonSocial = new RxCommonSocial();
        commonSocial.socialNetwork = socialNetwork;
        commonSocial.socialNetwork.setLogger(LOGGER::debug);
        commonSocial.activity = activity;
        return commonSocial;
    }

    public Observable<RxSocialAuthResult<String>> login() {
        return Observable.create((Observable.OnSubscribe<RxSocialAuthResult<String>>) subscriber -> {
            this.socialNetwork.setAuthorizationListener(new SocialAuthorizationListener() {
                @Override
                public void onAuthorizationSuccess(SocialNetwork network) {
                    if (subscriber.isUnsubscribed()) {
                        subscriber.onCompleted();
                        return;
                    }
                    subscriber.onNext(RxSocialAuthResult.<String>
                            builder()
                            .socialType(network.getSocialType())
                            .token(network.getAccessToken())
                            .build());
                }

                @Override
                public void onAuthorizationFail(SocialNetwork network, Throwable throwable) {
                    if (subscriber.isUnsubscribed()) {
                        subscriber.onCompleted();
                        return;
                    }
                    subscriber.onError(
                            Exceptions.propagate(
                                    new RxSocialException(network.getSocialType(),
                                            "authorization." + network.getSocialType().name() + ".fail ",
                                            throwable)));
                }

                @Override
                public void onAuthorizationCancel(SocialNetwork network, SocialCancelReason reason) {
                    if (subscriber.isUnsubscribed()) {
                        subscriber.onCompleted();
                        return;
                    }
                    subscriber.onError(
                            Exceptions.propagate(
                                    new RxSocialCancelException(network.getSocialType(), reason)));
                }
            });
            this.socialNetwork.login(activity);
        });
    }

    public Observable<RxSocialProfileResult> profile() {
        return Observable.create(new Observable.OnSubscribe<RxSocialProfileResult>() {
            @Override
            public void call(Subscriber<? super RxSocialProfileResult> subscriber) {
                socialNetwork.setProfileListener(new SocialProfileListener() {
                    @Override
                    public void onProfileSuccess(SocialNetwork network) {
                        if (subscriber.isUnsubscribed()) {
                            subscriber.onCompleted();
                            return;
                        }
                        subscriber.onNext(RxSocialProfileResult.builder()
                                .socialUser(((AndroidBaseSocialNetwork) network).getProfile())
                                .type(network.getSocialType())
                                .build());
                    }

                    @Override
                    public void onProfileFailed(SocialNetwork network, Throwable throwable) {
                        if (subscriber.isUnsubscribed()) {
                            subscriber.onCompleted();
                            return;
                        }
                        subscriber.onError(Exceptions.propagate(new RxSocialException(network.getSocialType(),
                                "profile." + network.getSocialType().name() + ".fail ", throwable)));
                    }
                });
                socialNetwork.profile();

            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (socialNetwork != null) {
            socialNetwork.onActivityResult(requestCode, resultCode, intent);
        }
    }
}
