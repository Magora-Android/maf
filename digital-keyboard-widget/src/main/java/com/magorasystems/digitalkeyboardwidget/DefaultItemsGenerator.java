package com.magorasystems.digitalkeyboardwidget;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serega on 24.08.2015.
 */
public class DefaultItemsGenerator {
    public static final int KEY_CHANGE_LAYOUT = -1;
    public static final int KEY_BACKSPACE = -2;
    public static final int KEY_PAUSE = -3;
    public static final int KEY_WAIT = -4;
    public static final int KEY_STAR = -5;
    public static final int KEY_DASH = -6;
    public static final int KEY_PLUS = -7;

    private static final String PRIMARY_1_BTN_1 = "1";
    private static final String PRIMARY_1_BTN_2 = "2";
    private static final String PRIMARY_1_BTN_3 = "3";
    private static final String PRIMARY_1_BTN_4 = "4";
    private static final String PRIMARY_1_BTN_5 = "5";
    private static final String PRIMARY_1_BTN_6 = "6";
    private static final String PRIMARY_1_BTN_7 = "7";
    private static final String PRIMARY_1_BTN_8 = "8";
    private static final String PRIMARY_1_BTN_9 = "9";
    private static final String PRIMARY_1_BTN_10 = "+*#";
    private static final String PRIMARY_1_BTN_11 = "0";
    private static final String PRIMARY_1_BTN_12 = null;

    private static final String PRIMARY_2_BTN_1 = "";
    private static final String PRIMARY_2_BTN_2 = "ABC";
    private static final String PRIMARY_2_BTN_3 = "DEF";
    private static final String PRIMARY_2_BTN_4 = "GHI";
    private static final String PRIMARY_2_BTN_5 = "JKL";
    private static final String PRIMARY_2_BTN_6 = "MNO";
    private static final String PRIMARY_2_BTN_7 = "PQRS";
    private static final String PRIMARY_2_BTN_8 = "TUV";
    private static final String PRIMARY_2_BTN_9 = "WXYZ";
    private static final String PRIMARY_2_BTN_10 = null;
    private static final String PRIMARY_2_BTN_11 = null;
    private static final String PRIMARY_2_BTN_12 = null;

    private static final String SECONDARY_1_BTN_1 = "1";
    private static final String SECONDARY_1_BTN_2 = "2";
    private static final String SECONDARY_1_BTN_3 = "3";
    private static final String SECONDARY_1_BTN_4 = "pause";
    private static final String SECONDARY_1_BTN_5 = "5";
    private static final String SECONDARY_1_BTN_6 = "wait";
    private static final String SECONDARY_1_BTN_7 = "*";
    private static final String SECONDARY_1_BTN_8 = "8";
    private static final String SECONDARY_1_BTN_9 = "#";
    private static final String SECONDARY_1_BTN_10 = "123";
    private static final String SECONDARY_1_BTN_11 = "+";
    private static final String SECONDARY_1_BTN_12 = null;

    private static final String SECONDARY_2_BTN_1 = PRIMARY_2_BTN_1;
    private static final String SECONDARY_2_BTN_2 = PRIMARY_2_BTN_2;
    private static final String SECONDARY_2_BTN_3 = PRIMARY_2_BTN_3;
    private static final String SECONDARY_2_BTN_4 = null;
    private static final String SECONDARY_2_BTN_5 = PRIMARY_2_BTN_5;
    private static final String SECONDARY_2_BTN_6 = null;
    private static final String SECONDARY_2_BTN_7 = null;
    private static final String SECONDARY_2_BTN_8 = PRIMARY_2_BTN_8;
    private static final String SECONDARY_2_BTN_9 = null;
    private static final String SECONDARY_2_BTN_10 = null;
    private static final String SECONDARY_2_BTN_11 = null;
    private static final String SECONDARY_2_BTN_12 = null;

    public static List<DefaultKey> generateItems() {
        List<DefaultKey> result = new ArrayList<>(12);

        result.add(new DefaultKey(1, 1, PRIMARY_1_BTN_1, PRIMARY_2_BTN_1, SECONDARY_1_BTN_1, SECONDARY_2_BTN_1)
                .setSecondaryColor(Color.GRAY));
        result.add(new DefaultKey(2, 2, PRIMARY_1_BTN_2, PRIMARY_2_BTN_2, SECONDARY_1_BTN_2, SECONDARY_2_BTN_2)
                .setSecondaryColor(Color.GRAY));
        result.add(new DefaultKey(3, 3, PRIMARY_1_BTN_3, PRIMARY_2_BTN_3, SECONDARY_1_BTN_3, SECONDARY_2_BTN_3)
                .setSecondaryColor(Color.GRAY));

        result.add(new DefaultKey(4, KEY_PAUSE, PRIMARY_1_BTN_4, PRIMARY_2_BTN_4, SECONDARY_1_BTN_4, SECONDARY_2_BTN_4)
                .setSecondaryColor(Color.BLACK).setActiveInSecondaryState(true));
        result.add(new DefaultKey(5, 5, PRIMARY_1_BTN_5, PRIMARY_2_BTN_5, SECONDARY_1_BTN_5, SECONDARY_2_BTN_5)
                .setSecondaryColor(Color.GRAY));
        result.add(new DefaultKey(6, KEY_WAIT, PRIMARY_1_BTN_6, PRIMARY_2_BTN_6, SECONDARY_1_BTN_6, SECONDARY_2_BTN_6)
                .setSecondaryColor(Color.BLACK).setActiveInSecondaryState(true));

        result.add(new DefaultKey(7, KEY_STAR, PRIMARY_1_BTN_7, PRIMARY_2_BTN_7, SECONDARY_1_BTN_7, SECONDARY_2_BTN_7)
                .setSecondaryColor(Color.BLACK).setActiveInSecondaryState(true));
        result.add(new DefaultKey(8, 8, PRIMARY_1_BTN_8, PRIMARY_2_BTN_8, SECONDARY_1_BTN_8, SECONDARY_2_BTN_8)
                .setSecondaryColor(Color.GRAY));
        result.add(new DefaultKey(9, KEY_DASH, PRIMARY_1_BTN_9, PRIMARY_2_BTN_9, SECONDARY_1_BTN_9, SECONDARY_2_BTN_9)
                .setSecondaryColor(Color.BLACK).setActiveInSecondaryState(true));

        result.add(new DefaultKey(KEY_CHANGE_LAYOUT, KEY_CHANGE_LAYOUT, PRIMARY_1_BTN_10, PRIMARY_2_BTN_10, SECONDARY_1_BTN_10, SECONDARY_2_BTN_10)
                .setActiveInSecondaryState(true).setKeyBackgroundColor(0xb3b3b3));
        result.add(new DefaultKey(0, KEY_PLUS, PRIMARY_1_BTN_11, PRIMARY_2_BTN_11, SECONDARY_1_BTN_11, SECONDARY_2_BTN_11)
                .setActiveInSecondaryState(true));
        result.add(new DefaultKey(KEY_BACKSPACE, KEY_BACKSPACE, PRIMARY_1_BTN_12, PRIMARY_2_BTN_12, SECONDARY_1_BTN_12, SECONDARY_2_BTN_12)
                .setActiveInSecondaryState(true).setDrawableRes(R.drawable.ic_backspace_white_24dp)
                .setKeyBackgroundColor(0xb3b3b3));

        return result;
    }

}
