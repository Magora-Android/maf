package com.magorasystems.mafmodules.module.input.impl;

import com.magorasystems.mafmodules.module.input.SimpleRegistrationInputView;
import com.magorasystems.mafmodules.view.SimpleRegistrationInteractiveView;
import com.magorasystems.mafmodules.view.impl.SimpleRegistrationLceView;
import com.magorasystems.protocolapi.auth.dto.response.StringAuthInfo;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SimpleRegistrationInputViewImpl extends AbstractRegistrationViewInput<StringAuthInfo,
        SimpleRegistrationLceView, SimpleRegistrationInteractiveView>
        implements SimpleRegistrationInputView {

    public SimpleRegistrationInputViewImpl(SimpleRegistrationLceView passiveView, SimpleRegistrationInteractiveView interactiveView) {
        super(passiveView, interactiveView);
    }
}
