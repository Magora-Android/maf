package com.magorasystems.mafmodules.common.application;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.google.common.collect.Maps;

import java.util.Map;

import io.fabric.sdk.android.Fabric;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public abstract class BaseComponentApplication<COMPONENT> extends Application
        implements ComponentApplication<COMPONENT> {

    private COMPONENT component;
    private ApplicationSettings settings;
    protected Map<String, Object> subcomponents;

    public static <COMPONENT> ComponentApplication<COMPONENT> get(final Context context) {
        return (ComponentApplication<COMPONENT>) context.getApplicationContext();
    }

    /**
     * @return yout component
     */
    @Override
    public final COMPONENT getComponent() {
        return component;
    }

    /**
     * @return settings for application
     */
    @Override
    public ApplicationSettings getSettings() {
        return settings;
    }

    /**
     * @return {@link #getApplicationContext()}
     */
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

    /**
     * init Crashlytics <br>
     * init mutable, empty hashMap for {@code subcomponent} <br>
     * call {@link #buildApplicationSettings()} and set it to {@code settings}<br>
     * call {@link #buildGraphAndInject()} <br>
     */
    @Override
    public void onCreate() {
        super.onCreate();
        subcomponents = Maps.newHashMap();
        Fabric.with(this, new Crashlytics());
        settings = buildApplicationSettings();
        buildGraphAndInject();
    }

    protected abstract void buildGraphAndInject();

    protected abstract ApplicationSettings buildApplicationSettings();
}
