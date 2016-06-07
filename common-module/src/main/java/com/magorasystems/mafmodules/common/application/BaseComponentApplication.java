package com.magorasystems.mafmodules.common.application;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class BaseComponentApplication<COMPONENT> extends Application
        implements ComponentApplication<COMPONENT> {

    private COMPONENT component;

    public static <COMPONENT> ComponentApplication<COMPONENT> get(final Context context) {
        return (ComponentApplication<COMPONENT>) context.getApplicationContext();
    }

    @Override
    public final COMPONENT getComponent() {
        return component;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    /**
     * Only for Mock test
     *
     * @param component extends BroCodexComponent
     */
    public final void setComponent(COMPONENT component) {
        this.component = component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        buildGraphAndInject();
    }

    protected abstract void buildGraphAndInject();
}
