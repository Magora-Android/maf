package com.magorasystems.mafmodules.common.mvp.view;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public interface BaseView {

    void detachView();

    void showError(Throwable e);
}
