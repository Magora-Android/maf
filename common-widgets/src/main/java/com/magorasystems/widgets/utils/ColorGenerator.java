package com.magorasystems.widgets.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author amulya
 * @datetime 14 Oct 2014, 5:20 PM
 */
public class ColorGenerator {

    public static ColorGenerator DEFAULT;

    public static ColorGenerator MATERIAL;

    static {
        DEFAULT = create(Arrays.asList(
                0xfff16364,
                0xfff58559,
                0xfff9a43e,
                0xffe4c62e,
                0xff67bf74,
                0xff59a2be,
                0xff2093cd,
                0xffad62a7,
                0xff805781
        ));
        MATERIAL = create(Arrays.asList(
                0xb2e57373,
                0xb2f06292,
                0xb2ba68c8,
                0xb29575cd,
                0xb27986cb,
                0xb264b5f6,
                0xb24fc3f7,
                0xb24dd0e1,
                0xb24db6ac,
                0xb281c784,
                0xb2aed581,
                0xb2ff8a65,
                0xb2d4e157,
                0xb2ffd54f,
                0xb2ffb74d,
                0xb2a1887f,
                0xb290a4ae
        ));
    }

    private final List<Integer> mColors;
    private final Random mRandom;

    public static ColorGenerator create(List<Integer> colorList) {
        return new ColorGenerator(colorList);
    }

    private ColorGenerator(List<Integer> colorList) {
        mColors = colorList;
        mRandom = new Random(System.currentTimeMillis());
    }

    public int getRandomColor() {
        return mColors.get(mRandom.nextInt(mColors.size()));
    }

    public int getColor(Object key) {
        // abs(Integer.MIN_VALUE) = Integer.MIN_VALUE;
        int hashCode = key.hashCode();
        if (hashCode == Integer.MIN_VALUE) {
            hashCode = Integer.MAX_VALUE;
        }
        return mColors.get(Math.abs(hashCode) % mColors.size());
    }
}
