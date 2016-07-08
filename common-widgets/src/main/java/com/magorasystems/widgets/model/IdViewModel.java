package com.magorasystems.widgets.model;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface IdViewModel<ID extends Serializable> extends BaseViewModel {

    ID getId();

    boolean hasId();
}
