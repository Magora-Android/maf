package com.magorasystems.mafmodules.common.utils.component;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface Injectable<COMPONENT> {

    void inject(HasComponent<? extends COMPONENT> hasComponent);
}
