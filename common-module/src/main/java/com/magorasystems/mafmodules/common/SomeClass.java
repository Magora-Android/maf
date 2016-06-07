package com.magorasystems.mafmodules.common;

import android.location.LocationManager;

import com.magorasystems.mafmodules.common.dagger.component.CommonModuleComponent;

import javax.inject.Inject;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class SomeClass {

    @Inject
    protected LocationManager locationManager;

    public SomeClass(CommonModuleComponent component) {
        component.inject(this);
    }

    public String getString() {
        return "test";
    }
}
