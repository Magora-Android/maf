package com.magorasystems.widgets.model;

import java.io.Serializable;

/**
 * Developed 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserViewModel<ID extends Serializable> implements IdViewModel<ID> {

    private ID id;

    protected final void setId(ID id) {
        this.id = id;
    }

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public boolean hasId() {
        return id != null;
    }

    @Override
    public String toString() {
        return "UserViewModel{" +
                "id=" + id +
                '}';
    }
}
