package com.magorasystems.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.magorasystems.widgets.model.BaseViewModel;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author karpenko
 */
public abstract class BaseFrameWidget<INPUT, RESULT extends BaseViewModel> extends FrameLayout implements BaseWidget<INPUT, RESULT> {

    private Unbinder unbinder;

    private INPUT model;

    private WidgetAttributes widgetAttributes;

    private ViewGroup rootView;

    protected abstract WidgetAttributes readWidgetAttributes(Context context, AttributeSet attributeSet);


    public BaseFrameWidget(Context context, @LayoutRes int layoutId) {
        super(context);
        createView(layoutId);

    }

    public BaseFrameWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttributes(context, attrs);
    }

    public BaseFrameWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttributes(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseFrameWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        readAttributes(context, attrs);
    }

    public ViewGroup getView() {
        return rootView;
    }

    protected final INPUT getModel() {
        return model;
    }

    protected void readAttributes(Context context, AttributeSet attributeSet) {
        widgetAttributes = readWidgetAttributes(context, attributeSet);
        createView(widgetAttributes.getLayoutId());
    }

    @Override
    public final WidgetAttributes getWidgetAttributes() {
        return widgetAttributes;
    }

    protected void onViewCreated(final View view) {

    }

    protected final void createView(@LayoutRes int layout) {
        rootView = (ViewGroup) LayoutInflater.from(getContext()).inflate(layout, null);
        addView(rootView, 0);
        if (!isInEditMode()) {
            unbinder = ButterKnife.bind(this, rootView);
            onViewCreated(rootView);
        }
    }

    @Override
    public void destroyWidget() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void update(INPUT model) {
        this.model = model;
    }

}
