package com.magorasystems.mafmodules.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.common.ui.fragment.GenericModuleFragment;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;
import com.magorasystems.mafmodules.dagger.component.ProfileComponent;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.module.UserProfileModuleInputImpl;
import com.magorasystems.mafmodules.module.UserProfilePresenterModule;
import com.magorasystems.mafmodules.module.input.impl.UserProfileViewInput;
import com.magorasystems.mafmodules.router.ProfileRouter;
import com.magorasystems.mafmodules.view.impl.UserProfileInteractiveView;
import com.magorasystems.mafmodules.view.impl.UserProfileLceView;
import com.magorasystems.mafmodules.view.impl.UserProfileLceViewImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class ShowProfileFragment extends GenericModuleFragment implements Injectable<ProfileComponent>, ProfileRouter<UserProfile> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowProfileFragment.class);

    @BindView(R.id.progress_view)
    protected View progressView;
    @BindView(R.id.coordinator_layout)
    protected View content;
    @BindView(R.id.button_edit_profile)
    protected View buttonEditProfile;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Inject
    protected Observable<UserProfilePresenterModule> moduleObservable;

    private UserProfilePresenterModule modulePresenter;

    private UserProfileLceView profileLceView;
    private UserProfileInteractiveView profileInteractiveView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(getTitle());
        profileLceView = new UserProfileLceViewImpl(progressView, content, content);
        profileInteractiveView = new UserProfileInteractiveView(null, buttonEditProfile);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject((ProfileComponent) ((HasComponent<?>) getActivity().getApplication()).getComponent(ProfileComponent.class.getSimpleName()));
    }

    @Override
    public void inject(ProfileComponent profileComponent) {
        profileComponent.inject(this);
    }

    @Override
    protected UserProfilePresenterModule getModulePresenter() {
        return modulePresenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (modulePresenter != null) {
            modulePresenter.start();
        } else {
            moduleObservable.subscribe(module -> {
                modulePresenter = module;
                final UserProfileViewInput viewInput = new UserProfileViewInput(profileLceView, profileInteractiveView);
                modulePresenter.input(new UserProfileModuleInputImpl(viewInput, this));
                modulePresenter.start();
            });
        }
    }

    @Override
    public void onEditProfile(UserProfile model) {
        LOGGER.debug("onEditProfile {} ", model);
    }

    @Override
    public void onUpdateProfile(UserProfile model) {
        LOGGER.debug("onUpdateProfile {} ", model);
    }

    @Override
    public void onBack() {
        if (!isActivityDetached()) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void onShowError(Throwable throwable) {
        if (!isActivityDetached()) {
            LOGGER.error("something wrong ", throwable);
            showErrorDialog(throwable.getMessage(), (v, i) -> {
            });
        }
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_show_my_profile;
    }

    @Override
    protected View getProgressView() {
        return progressView;
    }

    @Override
    public String getTitle() {
        return "Valentin S. Bolkonsky";
    }

    @Override
    public void detachView() {
        profileLceView.detachView();
        profileInteractiveView.destroy();
    }

    @Override
    public void showError(Throwable e) {
        LOGGER.error("showError ", e);
    }
}
