package com.magorasystems.mafmodules.module.input;

import com.magorasystems.mafmodules.common.module.input.InteractiveView;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ProfileInteractiveView<P> extends InteractiveView<P> {

    Observable<Void> edit();

}
