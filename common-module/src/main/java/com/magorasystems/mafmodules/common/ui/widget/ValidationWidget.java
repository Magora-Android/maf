package com.magorasystems.mafmodules.common.ui.widget;

import rx.Observable;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public interface ValidationWidget {

    Observable<Boolean> validation();
}
