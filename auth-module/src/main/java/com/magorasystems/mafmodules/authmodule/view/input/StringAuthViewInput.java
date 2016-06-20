package com.magorasystems.mafmodules.authmodule.view.input;

import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class StringAuthViewInput implements ViewInput<StringAuthView, AuthInteractiveView> {

    private StringAuthView passiveView;
    private AuthInteractiveView interactiveView;

    public StringAuthViewInput(StringAuthView passiveView, AuthInteractiveView interactiveView) {
        this.passiveView = passiveView;
        this.interactiveView = interactiveView;
    }

    @Override
    public StringAuthView getPassiveView() {
        return passiveView;
    }

    @Override
    public AuthInteractiveView getInteractiveView() {
        return interactiveView;
    }
}
