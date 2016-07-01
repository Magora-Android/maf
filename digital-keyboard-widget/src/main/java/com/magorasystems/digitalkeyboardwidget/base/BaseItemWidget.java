package com.magorasystems.digitalkeyboardwidget.base;

import android.support.annotation.NonNull;

import com.magorasystems.digitalkeyboardwidget.DefaultKey;


/**
 * Created by Serega on 24.08.2015.
 */
public interface BaseItemWidget {

    void bindKeyItem(@NonNull DefaultKey key);

    void swapData();
}
