package com.magorasystems.digitalkeyboardwidget;

import android.content.Context;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.magorasystems.digitalkeyboardwidget.impl.WidgetKeyItem;

import java.util.List;

import codetail.graphics.drawables.DrawableHotspotTouch;
import codetail.graphics.drawables.LollipopDrawable;
import codetail.graphics.drawables.LollipopDrawablesCompat;

/**
 * Created by Serega on 24.08.2015.
 */
public class DefaultKeyboardAdapter extends BaseAdapter {
    private final List<DefaultKey> data;
    private final OnKeyClickListener listener;
    private boolean useRipple;

    public interface OnKeyClickListener {
        void onItemClicked(DefaultKey key);
    }

    static final class Holder{
        final WidgetKeyItem widgetKeyItem;

        Holder(WidgetKeyItem widgetKeyItem) {
            this.widgetKeyItem = widgetKeyItem;
        }
    }

    public DefaultKeyboardAdapter(List<DefaultKey> data, @Nullable OnKeyClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public DefaultKey getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_key, parent, false);
            WidgetKeyItem root = (WidgetKeyItem) convertView;
            holder = new Holder(root);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        final DefaultKey item = data.get(position);
        holder.widgetKeyItem.bindKeyItem(item);
        if (listener != null) {
            holder.widgetKeyItem.setOnClickListener(v -> {
                if (item.enabledInCurrentState()) {
                    listener.onItemClicked(item);
                }
            });
        }

        if(useRipple) {
            Drawable drawable = getDrawable2(parent.getContext());
            if(item.getKeyBackgroundColor() != DefaultKey.EMPTY){
                applyColorFilter(drawable, item.getKeyBackgroundColor());
            }

            holder.widgetKeyItem.setBackground(drawable);
            holder.widgetKeyItem.setClickable(true);
            holder.widgetKeyItem.setOnTouchListener(new DrawableHotspotTouch((LollipopDrawable) holder.widgetKeyItem.getBackground()));
        }else{
            if(item.getKeyBackgroundColor() != DefaultKey.EMPTY){
                holder.widgetKeyItem.setBackgroundColor(item.getKeyBackgroundColor());
            }
        }

        return convertView;
    }

    private static Drawable getDrawable2(Context context) {
        return LollipopDrawablesCompat.getDrawable(context.getResources(), R.drawable.ripple, context.getTheme());
    }

    private static void applyColorFilter(Drawable drawable, int color){
        drawable.setColorFilter(new LightingColorFilter(0xFF000000, color));
    }

    public void setUseRipple(boolean value){
        useRipple = value;
    }
}
