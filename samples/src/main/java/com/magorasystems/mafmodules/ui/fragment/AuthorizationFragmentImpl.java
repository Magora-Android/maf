package com.magorasystems.mafmodules.ui.fragment;

import android.os.Bundle;

import com.magorasystems.mafmodules.common.utils.ColorUtils;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;
import com.magorasystems.mafmodules.dagger.component.SampleComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class AuthorizationFragmentImpl extends AuthorizationFragment<SampleComponent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFragmentImpl.class);

    public static AuthorizationFragmentImpl makeFragment() {
        final AuthorizationFragmentImpl fragment = new AuthorizationFragmentImpl();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showError(Throwable e) {
        LOGGER.error("some error ", e);
        showErrorDialog(e.getMessage(), (v, i) -> {

        });
    }

    @Override
    public void inject(HasComponent<? extends SampleComponent> hasComponent) {
        hasComponent.getComponent().inject(this);
    }

    @Override
    public void showProgress() {
        updateDecorView(ColorUtils.getSaturatedPaint(0.5f));
        super.showProgress();
    }

    @Override
    public void showContent() {
        updateDecorView(null);
        super.showContent();
    }
}
