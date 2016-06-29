package com.magorasystems.mafmodules.authmodule.module.input;

import com.magorasystems.mafmodules.authmodule.view.impl.StringAuthView;
import com.magorasystems.mafmodules.common.module.input.AbstractViewInput;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class StringAuthViewInput extends AbstractViewInput<StringAuthView, AuthInteractiveView> {

    public StringAuthViewInput(StringAuthView passiveView, AuthInteractiveView interactiveView) {
        super(passiveView, interactiveView);
    }
}
