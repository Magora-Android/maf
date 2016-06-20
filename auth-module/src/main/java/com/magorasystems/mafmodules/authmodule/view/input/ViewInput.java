package com.magorasystems.mafmodules.authmodule.view.input;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ViewInput<P,I> {

    P getPassiveView();

    I getInteractiveView();
}
