package com.magorasystems.mafmodules.common.module.output;

/**
 * @param <M> type of your output model
 *
 * @author Valentin S.Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public abstract class AbstractViewOutput<M> implements ViewOutput<M> {

    private M model;

    /**
     * Setting model
     * @param model your model
     */
    protected AbstractViewOutput(M model) {
        this.model = model;
    }

    /**
     * @return model
     */
    @Override
    public M getModel() {
        return model;
    }
}
