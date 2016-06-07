package com.magorasystems.mafmodules.common.dagger.module;

import android.content.Context;
import android.location.LocationManager;

import com.magorasystems.mafmodules.common.application.ComponentApplication;

import dagger.Module;
import dagger.Provides;

/**
 * @author Valentin S. Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
@Module
public class ApplicationModule implements BaseModule {

    private final ComponentApplication<?> application;

    public ApplicationModule(final ComponentApplication<?> application) {
        this.application = application;
    }

    @Provides
    public Context providerContext() {
        return this.application.getContext();
    }

    @Provides
    public ComponentApplication<?> providerComponentApplication() {
        return application;
    }

    @Provides
    public LocationManager providerLocationManager(Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }
}
