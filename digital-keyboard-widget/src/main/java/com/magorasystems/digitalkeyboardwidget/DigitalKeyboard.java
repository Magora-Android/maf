package com.magorasystems.digitalkeyboardwidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.magorasystems.digitalkeyboardwidget.base.BaseItemWidget;

import rx.Observable;
import rx.subjects.PublishSubject;


/**
 * Created by Serega on 24.08.2015.
 */
public class DigitalKeyboard extends AdapterView<BaseAdapter> {
    private static final int DEFAULT_KEYS_IN_ROW = 3;

    private BaseAdapter adapter;

    private int keysInRow;
    private int keysInColumn;

    private int marginsBetweenItems;

    private PublishSubject<DefaultKey> keyPublisher;

    public DigitalKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            initWithAttrs(context, attrs, 0, 0);
        }
    }

    public DigitalKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            initWithAttrs(context, attrs, defStyleAttr, 0);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DigitalKeyboard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!isInEditMode()) {
            initWithAttrs(context, attrs, defStyleAttr, defStyleRes);
        }
    }

    private void initWithAttrs(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DigitalKeyboard, defStyleAttr, defStyleRes);
        marginsBetweenItems = (int) array.getDimension(R.styleable.DigitalKeyboard_marginsBetweenItems, 0);
        keysInRow = array.getInt(R.styleable.DigitalKeyboard_keysInOneRow, DEFAULT_KEYS_IN_ROW);
        array.recycle();
        initDefault();
    }

    @Override
    public BaseAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void setAdapter(@NonNull BaseAdapter adapter) {
        this.adapter = adapter;
        keysInColumn = adapter.getCount() / keysInRow;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (adapter == null) {
            return;
        }
        addAllItems(left, right);
        positionItems();
    }

    private void addAllItems(int left, int right) {
        if (getChildCount() == 0) {
            for (int i = 0; i < adapter.getCount(); i++) {
                View v = adapter.getView(i, null, this);
                addAndMeasureChild(v, right - left);
            }
        }
    }

    private void addAndMeasureChild(View view, int w) {
        LayoutParams params = view.getLayoutParams();
        addViewInLayout(view, -1, params, true);
        int itemWidth = (w - marginsBetweenItems - marginsBetweenItems - marginsBetweenItems * (keysInRow - 1)) / keysInRow;
        int itemHeight = (getHeight() - marginsBetweenItems - marginsBetweenItems - marginsBetweenItems * (keysInColumn - 1)) / keysInColumn;

        view.measure(MeasureSpec.EXACTLY | itemWidth, MeasureSpec.EXACTLY | itemHeight);
    }

    private void positionItems() {
        int itemLeft = marginsBetweenItems;
        int itemTop = marginsBetweenItems;
        for (int i = 0; i < getChildCount(); ++i) {
            View v = getChildAt(i);
            int itemWidth = v.getMeasuredWidth();
            int itemHeight = v.getMeasuredHeight();
            if (i != 0 && i % keysInRow == 0) {
                itemLeft = marginsBetweenItems;
                itemTop += itemHeight + marginsBetweenItems;
            } else if (i != 0) {
                itemLeft += itemWidth + marginsBetweenItems;
            }

            v.layout(itemLeft, itemTop, itemLeft + itemWidth, itemTop + itemHeight);
        }
    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int position) {

    }

    public void swapLayout() {
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (v instanceof BaseItemWidget) {
                ((BaseItemWidget) v).swapData();
            }
        }
    }

    public Observable<DefaultKey> keyClicks() {
        if (keyPublisher == null) {
            keyPublisher = PublishSubject.create();
        }
        return keyPublisher;
    }

    private void initDefault() {
        DefaultKeyboardAdapter defaultAdapter = new DefaultKeyboardAdapter(
                DefaultItemsGenerator.generateItems(), this::onNext);
        setAdapter(defaultAdapter);
    }

    public void unsubscribe() {
        keyPublisher.onCompleted();
        keyPublisher = null;

    }

    private void onNext(DefaultKey key) {
        if (keyPublisher != null) {
            keyPublisher.onNext(key);
        }
    }
}
