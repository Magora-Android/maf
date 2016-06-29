package com.magorasystems.mafmodules.common.mvp.router;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public interface BaseRouter {

    void onBack();

    void onShowError(Throwable throwable);
}
