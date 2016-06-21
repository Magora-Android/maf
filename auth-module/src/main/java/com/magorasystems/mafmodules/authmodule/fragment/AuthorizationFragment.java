package com.magorasystems.mafmodules.authmodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;

import com.google.common.collect.Lists;
import com.magorasystems.mafmodules.authmodule.R;
import com.magorasystems.mafmodules.authmodule.dagger.component.AuthComponent;
import com.magorasystems.mafmodules.authmodule.performance.AuthViewModel;
import com.magorasystems.mafmodules.authmodule.presenter.SimpleAuthPresenter;
import com.magorasystems.mafmodules.authmodule.router.AuthRouter;
import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.authmodule.view.input.AuthInteractiveView;
import com.magorasystems.mafmodules.authmodule.view.input.AuthInteractiveViewImpl;
import com.magorasystems.mafmodules.authmodule.widget.AuthWidget;
import com.magorasystems.mafmodules.common.ui.fragment.GenericFragment;
import com.magorasystems.mafmodules.common.ui.widget.ValidationTextRule;
import com.magorasystems.mafmodules.common.ui.widget.WidgetUtils;
import com.magorasystems.mafmodules.common.utils.PatternsUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.common.utils.component.Injectable;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.subjects.PublishSubject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public abstract class AuthorizationFragment extends GenericFragment<AuthRouter> implements StringAuthView, Injectable<AuthComponent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFragment.class);

    @Inject
    protected SimpleAuthPresenter presenter;

    protected abstract AuthWidget getAuthWidget();

    protected abstract View getSignInView();

    protected abstract View getRecoverPassword();

    private AuthInteractiveView authInteractiveView;

    private Subscription subscription;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject((AuthComponent) ((HasComponent<?>) getActivity().getApplication()).getComponent(AuthComponent.class.getSimpleName()));
        getPresenter().attachView(this);
        getPresenter().setRouter(router);
        final PublishSubject<AuthViewModel> subject = PublishSubject.create();
        getAuthWidget().updateRules(getRules());
        authInteractiveView = new AuthInteractiveViewImpl(
                getAuthWidget(),
                getRecoverPassword(),
                getSignInView(),
                subject);
        subscription = subject.doOnNext(authViewModel -> getSignInView().setEnabled(false))
                .doOnNext(authViewModel -> WidgetUtils.hideSoftKeyboard(getActivity()))
                .subscribe(authViewModel -> {
                    LOGGER.debug("viewModel: {}", authViewModel);
                });
        authInteractiveView.model()
                .subscribe(getPresenter()::authorization);
        authInteractiveView.passwordRecover()
                .subscribe(v -> LOGGER.debug("onPasswordRecover"));
    }

    @Override
    public void inject(AuthComponent component) {
        component.inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAuthWidget().updateRules(getRules());
        getAuthWidget().validation()
                .subscribe(getSignInView()::setEnabled);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_authorization;
    }

    @Override
    public void showProgress() {
        getProgressView().setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent() {
        getSignInView().setEnabled(true);
        getAuthWidget().setEnabled(true);
        getProgressView().setVisibility(View.GONE);
    }

    @Override
    public void setModel(StringAuthInfo model) {
        LOGGER.debug("setModel: user is not null {}", model != null);
    }

    @Override
    public void detachView() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        getAuthWidget().destroyWidget();
    }

    @Override
    public String getTitle() {
        return getString(R.string.title_sign_in);
    }


    @Override
    protected SimpleAuthPresenter getPresenter() {
        return presenter;
    }

    protected Collection<ValidationTextRule> getRules() {
        final List<ValidationTextRule> rules = Lists.newArrayList();
        rules.add(new ValidationTextRule.Builder()
                .isShow(true)
                .resourceId(R.id.text_email)
                .errorMessage(getString(R.string.error_email_no_valid))
                .pattern(Patterns.EMAIL_ADDRESS.pattern())
                .build());
        rules.add(new ValidationTextRule.Builder()
                .isShow(true)
                .resourceId(R.id.text_password)
                .errorMessage(getString(R.string.error_password_no_valid, getResources().getInteger(R.integer.auth_password_min_length)))
                .minLength(getResources().getInteger(R.integer.auth_password_min_length))
                .pattern(PatternsUtils.patternPassword.pattern())
                .build());
        return rules;
    }
}
