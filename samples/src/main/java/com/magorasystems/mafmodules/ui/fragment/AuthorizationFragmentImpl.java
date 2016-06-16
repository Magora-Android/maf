package com.magorasystems.mafmodules.ui.fragment;

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

    @Override
    public void showError(Throwable e) {
        LOGGER.error("some error ", e);
    }

    @Override
    public void inject(HasComponent<? extends SampleComponent> hasComponent) {
        hasComponent.getComponent().inject(this);
    }
}
