package com.magorasystems.mafmodules.module.input;

import com.magorasystems.mafmodules.common.module.input.AbstractViewInput;
import com.magorasystems.mafmodules.view.ProfileLceView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class ProfileViewInput<M, P extends ProfileLceView<? super M>, I extends ProfileInteractiveView<?>> extends AbstractViewInput<P,I> {

    protected ProfileViewInput(P passiveView, I interactiveView) {
        super(passiveView, interactiveView);
    }
}
