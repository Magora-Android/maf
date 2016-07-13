package com.magorasystems.mafmodules.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.View;

import com.google.common.collect.Lists;
import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.common.module.base.ModulePresenter;
import com.magorasystems.mafmodules.common.utils.PatternsUtils;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;
import com.magorasystems.mafmodules.module.SimpleRegistrationModuleInputImpl;
import com.magorasystems.mafmodules.module.SimpleRegistrationPresenterModule;
import com.magorasystems.mafmodules.module.input.SimpleRegistrationViewInput;
import com.magorasystems.mafmodules.module.input.impl.SimpleRegistrationViewInputImpl;
import com.magorasystems.mafmodules.router.SimpleRegistrationRouter;
import com.magorasystems.mafmodules.ui.widget.SimpleRegistrationWidget;
import com.magorasystems.mafmodules.view.SimpleRegistrationInteractiveView;
import com.magorasystems.mafmodules.view.impl.SimpleRegistrationInteractiveViewImpl;
import com.magorasystems.mafmodules.view.impl.SimpleRegistrationLceView;
import com.magorasystems.mafmodules.view.impl.SimpleRegistrationLceViewImpl;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;
import com.magorasystems.widgets.ValidationTextRule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

import butterknife.BindView;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationFragment extends GenericPresenterModuleFragment<SampleComponent,
        StringAuthInfo, SimpleRegistrationLceView,
        SimpleRegistrationViewInput, SimpleRegistrationPresenterModule>
        implements SimpleRegistrationRouter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleRegistrationFragment.class);

    public static SimpleRegistrationFragment makeFragment() {
        final SimpleRegistrationFragment fragment = new SimpleRegistrationFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.progress_view)
    protected View progressView;
    @BindView(R.id.content_layout)
    protected View contentView;
    @BindView(R.id.widget_registration)
    protected SimpleRegistrationWidget registrationWidget;
    @BindView(R.id.button_sign_up)
    protected View actionRegistrationView;
    @BindView(R.id.button_go_sign_in)
    protected View actionGoToSignIn;


    private SimpleRegistrationInteractiveView interactiveView;
    private SimpleRegistrationLceView passiveView;

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_registration;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectComponent(getActivity(), SampleComponent.class);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization();
    }

    @Override
    protected void initialization() {
        registrationWidget.updateRules(getRules());
        interactiveView = new SimpleRegistrationInteractiveViewImpl(actionRegistrationView, actionGoToSignIn, registrationWidget);
        passiveView = new SimpleRegistrationLceViewImpl(getProgressView(), getContentView());
    }


    @Override
    protected void startModule(SimpleRegistrationPresenterModule module) {
        setModulePresenter(module);
        module.input(new SimpleRegistrationModuleInputImpl(
                new SimpleRegistrationViewInputImpl(getPassiveView(), getInteractiveView()),
                this));
        module.start();
    }

    @Override
    protected SimpleRegistrationPresenterModule getModulePresenter() {
        final ModulePresenter presenter = super.getModulePresenter();
        if (presenter != null) {
            if (presenter instanceof SimpleRegistrationPresenterModule) {
                return (SimpleRegistrationPresenterModule) presenter;
            }
        }
        return null;
    }

    @Override
    protected SimpleRegistrationLceView getPassiveView() {
        return passiveView;
    }

    @Override
    protected SimpleRegistrationInteractiveView getInteractiveView() {
        return interactiveView;
    }


    @Override
    protected View getProgressView() {
        return progressView;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public void inject(SampleComponent sampleComponent) {
        sampleComponent.inject(this);
    }

    @Override
    public void onAfterRegistration(StringAuthInfo info) {
        LOGGER.debug("onAfterRegistration {} ", info);
    }

    @Override
    public void onGoToAuthorization() {
        LOGGER.debug("onGoToAuthorization");
    }

    protected View getContentView() {
        return contentView;
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
                .errorMessage(getString(R.string.error_password_no_valid,
                        getResources().getInteger(R.integer.auth_password_min_length)))
                .minLength(getResources().getInteger(R.integer.auth_password_min_length))
                .pattern(PatternsUtils.patternPassword.pattern())
                .build());
        rules.add(new ValidationTextRule.Builder()
                .isShow(true)
                .resourceId(R.id.text_name)
                .errorMessage(getString(R.string.error_field_empty))
                .minLength(1)
                .build());
        rules.add(new ValidationTextRule.Builder()
                .isShow(true)
                .resourceId(R.id.text_phone)
                .errorMessage(getString(R.string.error_phone_no_valid))
                .minLength(10)
                .pattern(Patterns.PHONE.pattern())
                .build());
        return rules;
    }
}

