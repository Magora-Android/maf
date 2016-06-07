package com.magorasystems.mafmodules.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S. Bolkonsky
 */
public final class DateTimeUtils {

    public static final String FORMAT_HH_MM = "HH:mm";

    private DateTimeUtils() {

    }

    public static Date parseTimeString(final String src, final String format) {
        try {
            DateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
            return formatter.parse(src);
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatTime(final Date date, final String format) {
        try {
            DateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
            return formatter.format(date);
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isTimeBetween(Date start, Date end) {
        return isTimeBetween(new Date(), start, end);
    }

    public static boolean isTimeBetween(Date current, Date start, Date end) {
        if (start == null || end == null) {
            return false;
        }
        if (start.after(end)) {
            return false;
        }
        final Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(current);
        final Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        final Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        final Calendar stubCalendar = Calendar.getInstance();
        stubCalendar.setTime(start);
        stubCalendar.set(Calendar.HOUR, currentCalendar.get(Calendar.HOUR));
        stubCalendar.set(Calendar.MINUTE, currentCalendar.get(Calendar.MINUTE));
        stubCalendar.set(Calendar.AM_PM, currentCalendar.get(Calendar.AM_PM));
        final Date actual = stubCalendar.getTime();
        return start.before(actual) && end.after(actual);
    }


}
