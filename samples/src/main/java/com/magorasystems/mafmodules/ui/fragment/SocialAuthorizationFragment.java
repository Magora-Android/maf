package com.magorasystems.mafmodules.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.common.module.output.ViewOutput;
import com.magorasystems.mafmodules.common.mvp.presenter.BasePresenter;
import com.magorasystems.mafmodules.common.mvp.view.BaseView;
import com.magorasystems.mafmodules.common.ui.fragment.GenericFragment;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.model.social.RxCommonSocial;
import com.magorasystems.mafmodules.model.social.model.RxSocialAuthResult;
import com.magorasystems.mafmodules.router.SocialRouter;
import com.mgrmobi.sdk.social.android.AndroidBaseSocialNetwork;
import com.mgrmobi.sdk.social.base.SocialNetworkManager;
import com.mgrmobi.sdk.social.base.SocialType;
import com.mgrmobi.sdk.social.vk.VKSocialNetwork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SocialAuthorizationFragment extends GenericFragment<SocialRouter> implements Injectable<SampleComponent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialAuthorizationFragment.class);

    public static SocialAuthorizationFragment makeFragment() {
        final SocialAuthorizationFragment fragment = new SocialAuthorizationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.progress_view)
    protected View progressView;

    @Inject
    protected SchedulersUtils.CoreScheduler scheduler;

    private RxCommonSocial rxCommonSocial;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject((SampleComponent) ((HasComponent<?>) getActivity().getApplication()).getComponent());
    }

    @OnClick(R.id.button_connect_vk)
    protected void onVKClick() {
        getProgressView().setVisibility(View.VISIBLE);
        final AndroidBaseSocialNetwork socialNetwork;
        if (SocialNetworkManager.isRegistered(SocialType.VK)) {
            socialNetwork = (AndroidBaseSocialNetwork) SocialNetworkManager.getNetwork(SocialType.VK);
        } else {
            socialNetwork = new VKSocialNetwork.Builder()
                    .setContext(getActivity())
                    .setScope(new String[]{"photos", "wall", "email", "status", "friends"})
                    .create();
        }
        rxCommonSocial = RxCommonSocial.create(socialNetwork);
        rxCommonSocial.login(getActivity())
                .compose(SchedulersUtils.applySchedulers(scheduler))
                .subscribe(this::socialAuthSuccess, this::socialFailed);
    }

    private void socialAuthSuccess(RxSocialAuthResult<String> result) {
        LOGGER.debug("onAuthorizationSuccess " + result.getSocialType() + ", token: " + result.getToken());
        if (rxCommonSocial != null) {
            rxCommonSocial.profile()
                    .compose(SchedulersUtils.applySchedulers(scheduler))
                    .subscribe(rxSocialProfileResult -> {
                        LOGGER.debug(String.valueOf(rxSocialProfileResult));
                    }, this::socialFailed);
        }
    }

    private void socialFailed(Throwable throwable) {
        LOGGER.error("social failed ", throwable);
    }

    @Override
    protected BasePresenter<? extends BaseView, SocialRouter, ?, ? extends ViewOutput<?>> getPresenter() {
        return null;
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_social_authorization;
    }

    @Override
    protected View getProgressView() {
        return progressView;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void inject(SampleComponent sampleComponent) {
        sampleComponent.inject(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getProgressView().setVisibility(View.GONE);
        if (rxCommonSocial != null) {
            rxCommonSocial.onActivityResult(requestCode, requestCode, data);
        }
    }
}
