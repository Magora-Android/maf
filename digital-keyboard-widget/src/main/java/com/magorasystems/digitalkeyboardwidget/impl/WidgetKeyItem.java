package com.magorasystems.digitalkeyboardwidget.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.magorasystems.digitalkeyboardwidget.DefaultKey;
import com.magorasystems.digitalkeyboardwidget.R;
import com.magorasystems.digitalkeyboardwidget.base.BaseItemWidget;


/**
 * Created by Serega on 24.08.2015.
 */
public class WidgetKeyItem extends FrameLayout implements BaseItemWidget {
    private TextView textPrimaryOne;
    private TextView textPrimaryTwo;
    private TextView textSecondaryOne;
    private TextView textSecondaryTwo;
    private ImageView icon;
    private View layoutPrimary;
    private View layoutSecondary;
    private DefaultKey currentKey;

    public WidgetKeyItem(Context context) {
        super(context);
    }

    public WidgetKeyItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetKeyItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WidgetKeyItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        layoutPrimary = findViewById(R.id.layout_primary);
        layoutSecondary = findViewById(R.id.layout_secondary);

        textPrimaryOne = (TextView) findViewById(R.id.text_primary_1);
        textPrimaryTwo = (TextView) findViewById(R.id.text_primary_2);

        textSecondaryOne = (TextView) findViewById(R.id.text_secondary_1);
        textSecondaryTwo = (TextView) findViewById(R.id.text_secondary_2);

        icon = (ImageView) findViewById(R.id.key_icon);
    }

    @Override
    public void bindKeyItem(@NonNull DefaultKey key) {
        currentKey = key;
        if(key.getDrawableRes() != DefaultKey.EMPTY){
            bindIcon(key);
        }else {
            bindText(key);
            bindColor(key);
        }
    }

    private void bindIcon(@NonNull DefaultKey key){
        layoutPrimary.setVisibility(GONE);
        layoutSecondary.setVisibility(GONE);
        icon.setVisibility(VISIBLE);
        icon.setImageResource(key.getDrawableRes());
    }

    private void bindText(@NonNull DefaultKey key){
        if(key.primaryOne == null){
            textPrimaryOne.setVisibility(GONE);
        }else{
            textPrimaryOne.setText(key.primaryOne);
        }

        if(key.primaryTwo == null){
            textPrimaryTwo.setVisibility(GONE);
        }else{
            textPrimaryTwo.setText(key.primaryTwo);
        }

        if(key.secondaryOne == null){
            textSecondaryOne.setVisibility(GONE);
        }else{
            textSecondaryOne.setText(key.secondaryOne);
        }

        if(key.secondaryTwo == null){
            textSecondaryTwo.setVisibility(GONE);
        }else {
            textSecondaryTwo.setText(key.secondaryTwo);
        }
    }

    private void bindColor(@NonNull DefaultKey key){
        if(key.getPrimaryColor() != DefaultKey.EMPTY){
            textPrimaryOne.setTextColor(key.getPrimaryColor());
            textPrimaryTwo.setTextColor(key.getPrimaryColor());
        }

        if(key.getSecondaryColor() != DefaultKey.EMPTY){
            textSecondaryOne.setTextColor(key.getSecondaryColor());
            textSecondaryTwo.setTextColor(key.getSecondaryColor());
        }
    }

    @Override
    public void swapData(){
        currentKey.switchState();
        if(icon.getVisibility() == VISIBLE){
            return;
        }
        if(layoutPrimary.getVisibility() == VISIBLE){
            layoutPrimary.setVisibility(INVISIBLE);
            layoutSecondary.setVisibility(VISIBLE);
        }else{
            layoutSecondary.setVisibility(INVISIBLE);
            layoutPrimary.setVisibility(VISIBLE);
        }
    }
}
