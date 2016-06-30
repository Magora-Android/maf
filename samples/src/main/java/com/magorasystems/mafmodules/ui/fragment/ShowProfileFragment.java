package com.magorasystems.mafmodules.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.dagger.component.ProfileComponent;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.mafmodules.module.UserProfileModuleInputImpl;
import com.magorasystems.mafmodules.module.UserProfilePresenterModule;
import com.magorasystems.mafmodules.module.input.impl.UserProfileViewInput;
import com.magorasystems.mafmodules.profile.fragment.GenericProfileFragment;
import com.magorasystems.mafmodules.view.impl.UserProfileInteractiveView;
import com.magorasystems.mafmodules.view.impl.UserProfileLceView;
import com.magorasystems.mafmodules.view.impl.UserProfileLceViewImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.BindView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class ShowProfileFragment extends GenericProfileFragment<ProfileComponent, UserProfilePresenterModule, UserProfile> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowProfileFragment.class);

    @BindView(R.id.progress_view)
    protected View progressView;
    @BindView(R.id.coordinator_layout)
    protected View content;
    @BindView(R.id.button_edit_profile)
    protected View buttonEditProfile;
    @BindView(R.id.collapsing_toolbar)
    protected CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.drawee_avatar)
    protected SimpleDraweeView draweeAvatar;

    private UserProfileLceView profileLceView;
    private UserProfileInteractiveView profileInteractiveView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectComponent(getActivity(), ProfileComponent.class);
    }

    @Override
    public void inject(ProfileComponent profileComponent) {
        profileComponent.inject(this);
    }


    @Override
    protected View getProgressView() {
        return progressView;
    }

    @Override
    protected void initialization() {
        collapsingToolbar.setTitle(getTitle());
        profileLceView = new UserProfileLceViewImpl(progressView, content, draweeAvatar, collapsingToolbar);
        profileInteractiveView = new UserProfileInteractiveView(null, buttonEditProfile);
    }

    @Override
    protected void startModule(UserProfilePresenterModule module) {
        module.input(new UserProfileModuleInputImpl(
                new UserProfileViewInput(getPassiveView(),
                        getInteractiveView()),
                this));
        module.start();
    }

    @Override
    protected UserProfilePresenterModule getModulePresenter() {
        return (UserProfilePresenterModule) super.getModulePresenter();
    }

    @Override
    protected UserProfileLceView getPassiveView() {
        return profileLceView;
    }

    @Override
    protected UserProfileInteractiveView getInteractiveView() {
        return profileInteractiveView;
    }
}
