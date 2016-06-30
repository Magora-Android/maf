package com.magorasystems.mafmodules.profile.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.magorasystems.mafmodules.common.module.base.ModuleInput;
import com.magorasystems.mafmodules.common.module.input.ViewInput;
import com.magorasystems.mafmodules.common.ui.fragment.GenericModuleFragment;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;
import com.magorasystems.mafmodules.profile.R;
import com.magorasystems.mafmodules.profile.module.ProfilePresenterModule;
import com.magorasystems.mafmodules.profile.module.input.ProfileInteractiveView;
import com.magorasystems.mafmodules.profile.module.input.ProfileViewInput;
import com.magorasystems.mafmodules.profile.module.output.ProfileViewOutput;
import com.magorasystems.mafmodules.profile.router.ProfileRouter;
import com.magorasystems.mafmodules.profile.view.ProfileView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class GenericProfileFragment<COMPONENT,
        MODULE extends ProfilePresenterModule<P, ? extends ProfileViewInput<P, ?, ?>, ? extends ProfileViewOutput<P>, ? extends ModuleInput<?, ProfileRouter<P>>>, P>
        extends GenericModuleFragment implements Injectable<COMPONENT>, ProfileRouter<P> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericProfileFragment.class);


    @Inject
    protected Observable<MODULE> moduleObservable;

    private ProfilePresenterModule<P, ? extends ProfileViewInput<P, ?, ?>, ? extends ProfileViewOutput<P>, ? extends ModuleInput<? extends ViewInput<?, ?>, ProfileRouter<P>>> modulePresenter;


    protected abstract void initialization();

    protected abstract void startModule(MODULE module);

    protected abstract ProfileView<P> getPassiveView();

    protected abstract ProfileInteractiveView<?> getInteractiveView();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization();
    }

    @Override
    protected ProfilePresenterModule<P, ? extends ProfileViewInput<P, ?, ?>, ? extends ProfileViewOutput<P>, ? extends ModuleInput<?, ProfileRouter<P>>> getModulePresenter() {
        return modulePresenter;
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
    public void onEditProfile(P model) {
        LOGGER.debug("onEditProfile {} ", model);
    }

    @Override
    public void onUpdateProfile(P model) {
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
    public String getTitle() {
        return "";
    }

    @Override
    public void detachView() {
        getPassiveView().detachView();
        getInteractiveView().destroy();
    }

    @Override
    public void showError(Throwable e) {
        LOGGER.error("showError ", e);
    }

    @SuppressWarnings("unchecked")
    protected final void injectComponent(Context context, Class<COMPONENT> clazz) {
        if (context instanceof HasComponent<?>) {
            final Object component = ((HasComponent<?>) context).getComponent(clazz.getSimpleName());
            if (component != null) {
                try {
                    inject((COMPONENT) component);
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Context has not implement \"" + clazz.getSimpleName() + "\" component");
                }
                return;
            }
        }
        throw new IllegalArgumentException("Context has not implement \"" + clazz.getSimpleName() + "\" component");
    }
}
