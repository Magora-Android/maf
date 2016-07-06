package com.magorasystems.mafmodules.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.component.SocialComponent;
import com.magorasystems.mafmodules.dagger.module.social.SocialsModule;
import com.magorasystems.mafmodules.model.social.RxCommonSocial;
import com.magorasystems.mafmodules.module.input.SocialInteractiveView;
import com.mgrmobi.sdk.social.android.AndroidBaseSocialNetwork;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleSocialInteractiveView implements SocialInteractiveView {

    @Inject
    @Named(SocialsModule.VK)
    protected AndroidBaseSocialNetwork vk;

    private final CompositeSubscription subscription = new CompositeSubscription();
    private PublishSubject<RxCommonSocial> publisher;
    private RxCommonSocial currentSocial;

    public SimpleSocialInteractiveView(Activity activity, View vkView) {
        SocialComponent component = (SocialComponent) ((HasComponent<?>) activity.getApplication())
                .getComponent(SocialComponent.class.getSimpleName());
        component.inject(this);
        subscription.add(RxView.clicks(vkView)
                .subscribe(aVoid -> {
                    if (publisher != null) {
                        currentSocial = RxCommonSocial.create(vk, activity);
                        publisher.onNext(currentSocial);
                    }
                }));
    }

    @Override
    public Observable<RxCommonSocial> model() {
        if (publisher == null) {
            publisher = PublishSubject.create();
        }
        return publisher;
    }

    @Override
    public void destroy() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription.clear();
        }
        if (publisher != null) {
            publisher.onCompleted();
            publisher = null;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (currentSocial != null) {
            currentSocial.onActivityResult(requestCode, resultCode, intent);
        }
    }
}
