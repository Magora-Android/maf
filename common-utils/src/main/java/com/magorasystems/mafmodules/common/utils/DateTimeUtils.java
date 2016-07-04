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

    /**
     * Parsing date from string with default Locale on device (JVM)
     *
     * @param src    string for parsing
     * @param format the format (pattern) describing the date and time format <br> <br>
     *               "yyyy.MM.dd" (uppercase of MM is important) <br>
     *               "hh:mm a" returned your time, but date is default = 01.01.1970 <br>
     *               for more examples see:  http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
     * @return instance of Date with parsed parameters or <b>null</b> if throws exception
     * @throws ParseException       if the beginning of the {@code src} cannot be parsed.
     * @throws NullPointerException if the given {@code format} is null
     */
    public static Date parseTimeString(final String src, final String format) {
        try {
            DateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
            return formatter.parse(src);
        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param date   your Date with time
     * @param format the format (pattern) describing the returned date and/or time format <br> <br>
     *               "yyyy.MM.dd" (uppercase of MM is important) <br>
     *               "hh:mm a" returned your time, but date is default = 01.01.1970 <br>
     *               for more examples see:  http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
     * @return the formatted time string or <b>null</b> if throws exception
     * @throws ParseException       if the beginning of the {@code src} cannot be parsed.
     * @throws NullPointerException if the given {@code format} is null
     */
    public static String formatTime(final Date date, final String format) {
        try {
            DateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
            return formatter.format(date);
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param start instance of Date with start date
     * @param end   instance of Date with end date
     * @return <b>true</b> if time between start and end, <b>false</b> otherwise
     */
    public static boolean isTimeBetween(Date start, Date end) {
        return isTimeBetween(new Date(), start, end);
    }

    /**
     * @param current current Date
     * @param start   start Date
     * @param end     end Date
     * @return <b>false</b> if {@code start} after {@code end} <br>
     * <b>false</b> if {@code start} or {@code end} is null <br>
     * <b>correct answer</b> otherwise
     */
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
