package com.magorasystems.mafmodules.common.module.output;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class AbstractViewOutput<M> implements ViewOutput<M> {

    private M model;

    protected AbstractViewOutput(M model) {
        this.model = model;
    }

    @Override
    public M getModel() {
        return model;
    }
}
