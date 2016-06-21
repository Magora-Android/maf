package com.magorasystems.mafmodules.authmodule.view.input;

import com.magorasystems.mafmodules.common.mvp.view.BaseView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ViewInput<P extends BaseView,I> {

    P getPassiveView();

    I getInteractiveView();
}
