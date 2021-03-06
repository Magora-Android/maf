package com.magorasystems.mafmodules.authmodule.fragment;

import android.os.Bundle;
import android.view.View;

import com.magorasystems.mafmodules.authmodule.R;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.module.impl.AuthModuleInput;
import com.magorasystems.mafmodules.authmodule.module.impl.AuthModulePresenter;
import com.magorasystems.mafmodules.authmodule.module.input.AuthInteractiveView;
import com.magorasystems.mafmodules.authmodule.module.input.AuthInteractiveViewImpl;
import com.magorasystems.mafmodules.authmodule.module.input.StringAuthViewInput;
import com.magorasystems.mafmodules.authmodule.performance.AuthViewModel;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthViewImpl;
import com.magorasystems.mafmodules.authmodule.widget.AuthWidget;
import com.magorasystems.mafmodules.common.mvp.view.BaseModelView;
import com.magorasystems.mafmodules.common.ui.fragment.GenericModuleSupportFragment;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;
import com.magorasystems.widgets.ValidationTextRule;
import com.magorasystems.widgets.WidgetUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.subjects.PublishSubject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AuthorizationModuleSupportFragment extends GenericModuleSupportFragment<AuthComponent> implements AuthRouter, BaseModelView<StringAuthInfo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationModuleSupportFragment.class);
    @Inject
    protected Observable<AuthModulePresenter> modulePresenterObservable;
    private AuthModulePresenter modulePresenter;
    private Subscription subscription;
    private AuthInteractiveView authInteractiveView;
    private StringAuthView authPassiveView;

    protected abstract AuthWidget getAuthWidget();

    protected abstract View getSignInView();

    protected abstract View getRecoverPassword();

    protected abstract Collection<ValidationTextRule> getRules();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectComponent(getActivity(), AuthComponent.class);
    }

    @Override
    protected void initialization() {
        final PublishSubject<AuthViewModel> subject = PublishSubject.create();
        getAuthWidget().updateRules(getRules());
        authInteractiveView = new AuthInteractiveViewImpl(
                getAuthWidget(),
                getRecoverPassword(),
                getSignInView(),
                subject);
        subscription = subject
                .doOnNext(authViewModel -> WidgetUtils.hideSoftKeyboard(getActivity()))
                .subscribe(model -> {
                }, this::showError);
        authPassiveView = new StringAuthViewImpl(getActivity().getWindow().getDecorView(),
                getAuthWidget(), this);
    }

    @Override
    public void setModel(StringAuthInfo model) {
        LOGGER.debug("setModel: {} ", model);
    }

    @Override
    public void onStart() {
        onSuperStart();
        if (modulePresenter != null) {
            modulePresenter.start();
        } else {
            modulePresenterObservable.subscribe(authModulePresenter -> {
                modulePresenter = authModulePresenter;
                modulePresenter.input(new AuthModuleInput(
                        new StringAuthViewInput(authPassiveView, authInteractiveView),
                        AuthorizationModuleSupportFragment.this));
                if (!modulePresenter.checkIfAuthorization()) {
                    LOGGER.debug("user already authorization");
                }
                modulePresenter.start();
            });
        }
    }

    @Override
    public void onStop() {
        onSuperStop();
        if (modulePresenter != null) {
            modulePresenter.stop();
        }
    }

    @Override
    public String getTitle() {
        return getString(R.string.title_sign_in);
    }

    @Override
    public void detachView() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        if (authInteractiveView != null) {
            authInteractiveView.destroy();
            authInteractiveView = null;
        }
        if (authPassiveView != null) {
            authPassiveView.detachView();
            authPassiveView = null;
        }
    }

    @Override
    public void inject(AuthComponent component) {
        component.inject(this);
    }

    @Override
    protected AuthModulePresenter getModulePresenter() {
        return modulePresenter;
    }
}
