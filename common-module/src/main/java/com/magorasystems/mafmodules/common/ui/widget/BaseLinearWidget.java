package com.magorasystems.mafmodules.common.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.magorasystems.mafmodules.common.mvp.model.BaseViewModel;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author karpenko
 */
public abstract class BaseLinearWidget<M extends BaseViewModel, T> extends LinearLayout implements BaseWidget<M, T> {

    private Unbinder unbinder;

    protected T model;

    private WidgetAttributes widgetAttributes;

    public BaseLinearWidget(Context context, @LayoutRes int layoutId) {
        super(context);
        createView(layoutId);

    }

    public BaseLinearWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttributes(context, attrs);
    }

    public BaseLinearWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttributes(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseLinearWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        readAttributes(context, attrs);
    }

    protected void readAttributes(Context context, AttributeSet attributeSet) {
        widgetAttributes = WidgetAttributesReader.read(context, attributeSet);
        createView(widgetAttributes.getLayoutId());
    }


    protected void onViewCreated(final View view) {

    }

    protected final void createView(@LayoutRes int layout) {
        final View view = LayoutInflater.from(getContext()).inflate(layout, this, true);
        if (!isInEditMode()) {
            unbinder = ButterKnife.bind(this, view);
            onViewCreated(view);
        }
    }

    @Override
    public void destroyWidget() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void update(T model) {
        this.model = model;
    }
}