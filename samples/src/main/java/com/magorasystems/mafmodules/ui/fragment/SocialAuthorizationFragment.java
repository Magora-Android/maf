package com.magorasystems.mafmodules.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.common.ui.fragment.GenericModuleFragment;
import com.magorasystems.mafmodules.dagger.component.SocialComponent;
import com.magorasystems.mafmodules.module.SimpleSocialModuleInputImpl;
import com.magorasystems.mafmodules.module.SimpleSocialPresenterModule;
import com.magorasystems.mafmodules.module.input.SocialInteractiveView;
import com.magorasystems.mafmodules.module.input.impl.SimpleSocialViewInputImpl;
import com.magorasystems.mafmodules.router.SocialRouter;
import com.magorasystems.mafmodules.view.impl.SimpleSocialInteractiveView;
import com.magorasystems.mafmodules.view.impl.SimpleSocialLceView;
import com.magorasystems.protocolapi.auth.dto.response.AuthInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SocialAuthorizationFragment extends GenericModuleFragment<SocialComponent> implements SocialRouter<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialAuthorizationFragment.class);

    public static SocialAuthorizationFragment makeFragment() {
        final SocialAuthorizationFragment fragment = new SocialAuthorizationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.progress_view)
    protected View progressView;
    @BindView(R.id.button_connect_vk)
    protected View buttonVK;
    @BindView(R.id.content_layout)
    protected View contentView;

    @Inject
    protected Observable<SimpleSocialPresenterModule> moduleObservable;

    private SimpleSocialPresenterModule modulePresenter;

    private StringAuthView passiveView;
    private SocialInteractiveView interactiveView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectComponent(getActivity(), SocialComponent.class);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization();
    }

    @Override
    protected void initialization() {
        interactiveView = new SimpleSocialInteractiveView(getActivity(), buttonVK);
        passiveView = new SimpleSocialLceView(getProgressView(), getContentView());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (modulePresenter != null) {
            modulePresenter.start();
        } else {
            moduleObservable.subscribe(this::startModule);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (modulePresenter != null) {
            modulePresenter.stop();
            modulePresenter = null;
        }
    }

    @Override
    public void onAfterSocialAuth(AuthInfo<String> authInfo) {
        LOGGER.debug("onAfterSocialAuth {} ", authInfo);
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_social_authorization;
    }

    @Override
    protected View getProgressView() {
        return progressView;
    }

    protected View getContentView() {
        return contentView;
    }

    @Override
    public String getTitle() {
        return "";
    }


    @Override
    public void inject(SocialComponent socialComponent) {
        socialComponent.inject(this);
    }

    @Override
    protected SimpleSocialPresenterModule getModulePresenter() {
        return modulePresenter;
    }

    protected void startModule(SimpleSocialPresenterModule module) {
        modulePresenter = module;
        module.input(new SimpleSocialModuleInputImpl(
                new SimpleSocialViewInputImpl(getPassiveView(), getInteractiveView()),
                this));
        module.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getProgressView().setVisibility(View.GONE);
        if (interactiveView != null) {
            ((SimpleSocialInteractiveView) interactiveView).onActivityResult(requestCode, resultCode, data);
        }
    }

    protected StringAuthView getPassiveView() {
        return passiveView;
    }

    protected SocialInteractiveView getInteractiveView() {
        return interactiveView;
    }
}


