package com.magorasystems.mafmodules.common.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.magorasystems.mafmodules.common.application.ComponentApplication;
import com.magorasystems.mafmodules.common.ui.fragment.BaseFragmentImpl;
import com.magorasystems.mafmodules.common.ui.fragment.BaseSupportFragmentImpl;
import com.magorasystems.mafmodules.common.utils.component.HasComponent;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public abstract class BaseActivity<COMPONENT> extends AppCompatActivity implements HasComponent<COMPONENT> {

    private COMPONENT component;

    protected abstract int getResourceLayout();

    protected abstract void setFragment(final BaseFragmentImpl fragment);

    protected abstract void setSupportFragment(final BaseSupportFragmentImpl fragment);

    /**
     * {@link #requestWindowFeature(int)} with int = {@link Window#FEATURE_NO_TITLE}, <br>
     * that turning off the title at the top of the screen. <br>
     * Also {@link #setContentView(int)}
     * @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getResourceLayout());
    }

    protected final void setComponent(COMPONENT component) {
        this.component = component;
    }

    @Override
    public final COMPONENT getComponent() {
        return component;
    }

    @Override
    public Object getComponent(String key) {
        return ((ComponentApplication<?>) getApplication()).getComponent(key);
    }
}
