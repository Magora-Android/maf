package com.magorasystems.mafmodules.common.module.input;

import com.magorasystems.mafmodules.common.mvp.view.BaseView;

/**
 * @param <P> type of passive view
 * @param <I> type of interactive view
 * @author Valentin S.Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public abstract class AbstractViewInput<P extends BaseView, I extends InteractiveView<?>> implements ViewInput<P, I> {

    private P passiveView;
    private I interactiveView;

    /**
     * Setting passive and interactive views
     * @param passiveView passive view
     * @param interactiveView interactive view
     */
    protected AbstractViewInput(P passiveView, I interactiveView) {
        this.passiveView = passiveView;
        this.interactiveView = interactiveView;
    }

    /**
     * @return passive view
     */
    @Override
    public P getPassiveView() {
        return passiveView;
    }

    /**
     * @return interactive view
     */
    @Override
    public I getInteractiveView() {
        return interactiveView;
    }
}
