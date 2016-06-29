package com.magorasystems.mafmodules.common.module.input;

import com.magorasystems.mafmodules.common.mvp.view.BaseView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractViewInput<P extends BaseView,I extends InteractiveView<?>> implements ViewInput<P, I> {

    private P passiveView;
    private I interactiveView;

    protected AbstractViewInput(P passiveView, I interactiveView) {
        this.passiveView = passiveView;
        this.interactiveView = interactiveView;
    }

    @Override
    public P getPassiveView() {
        return passiveView;
    }

    @Override
    public I getInteractiveView() {
        return interactiveView;
    }
}
