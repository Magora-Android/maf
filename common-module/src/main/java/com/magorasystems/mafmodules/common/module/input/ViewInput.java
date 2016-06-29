package com.magorasystems.mafmodules.common.module.input;

import com.magorasystems.mafmodules.common.mvp.view.BaseView;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ViewInput<P extends BaseView,I extends InteractiveView<?>> {

    P getPassiveView();

    I getInteractiveView();
}
