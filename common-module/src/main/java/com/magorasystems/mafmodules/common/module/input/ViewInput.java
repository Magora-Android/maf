package com.magorasystems.mafmodules.common.module.input;

import com.magorasystems.mafmodules.common.mvp.view.BaseView;

/**
 * @param <P> param for {@code passive view} that must be extends {@link BaseView}
 * @param <I> param for {@code interactive view} that must be extends {@link InteractiveView}
 * @author Valentin S.Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public interface ViewInput<P extends BaseView, I extends InteractiveView<?>> {

    P getPassiveView();

    I getInteractiveView();
}
