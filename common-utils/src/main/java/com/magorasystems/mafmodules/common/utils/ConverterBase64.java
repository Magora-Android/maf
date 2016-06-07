package com.magorasystems.mafmodules.common.utils;

import android.util.Base64;

import java.nio.charset.Charset;

/**
 * @author Valentin S. Bolkonsky
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public final class ConverterBase64 {

    private ConverterBase64() {

    }

    public static String toBase64(final String source) {
        return toBase64(source, Base64.DEFAULT);
    }

    public static String toBase64(final String source, int flag) {
        final Charset utf8 = Charset.forName("utf-8");
        final byte[] src = source.getBytes(utf8);
        final byte[] dstr = Base64.encode(src, flag);
        return new String(dstr, 0, dstr.length - 1, utf8);
    }

    public static String fromBase64(final String distinct) {
        return fromBase64(distinct, Base64.DEFAULT);
    }

    public static String fromBase64(final String distinct, int flag) {
        final byte[] dstr = Base64.decode(distinct, flag);
        return new String(dstr, Charset.forName("utf-8"));
    }
}
