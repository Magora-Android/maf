package com.magorasystems.mafmodules.common.utils.helpers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author S.A.Bobrischev
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
public class TextHelper {
    public static final String HASH_TAG = "#";
    public static final String NICK_NAME = "@";

    public static String generateNickName() {
        return "sh" + new Random().nextInt(Integer.MAX_VALUE);
    }

    public static String formatDate(Date d) {
        return SimpleDateFormat.getDateInstance().format(d);
    }

    @Nullable
    public static String concatenateNonNull(@Nullable CharSequence firstName, @Nullable CharSequence lastName, @NonNull String divider) {
        boolean firstNameIsEmpty = TextUtils.isEmpty(firstName);
        boolean lastNameIsEmpty = TextUtils.isEmpty(lastName);

        if (!firstNameIsEmpty && !lastNameIsEmpty) {
            return firstName + divider + lastName;
        } else if (!firstNameIsEmpty) {
            return firstName + "";
        } else if (!lastNameIsEmpty) {
            return lastName + "";
        }
        return null;
    }

    @Nullable
    public static List<String> splitToList(String toSplit, @Nullable String splitBy) {
        if (TextUtils.isEmpty(toSplit)) {
            return null;
        }
        if (TextUtils.isEmpty(splitBy)) {
            splitBy = " ";
        }
        List<String> splitted = Arrays.asList(toSplit.split(splitBy));
        List<String> result = new ArrayList<>(splitted.size());
        for (String s : splitted) {
            result.add(s.trim().replace(",", ""));
        }
        return result;
    }

    public static int findAppropriateEventDescriptionEnd(String description, int maxLength) {
        if (isDividerCharacter(description.charAt(maxLength))) {
            return maxLength;
        }
        for (int i = maxLength; i < description.length(); ++i) {
            if (isDividerCharacter(description.charAt(i))) {
                return i;
            }
        }
        return description.length();
    }

    private static boolean isDividerCharacter(char ch) {
        return ch == ' ' || ch == '.' || ch == ',' || ch == ';' || ch == ':' || ch == '!' || ch == '?';
    }

    public static boolean isHashTag(String s) {
        return s != null && s.length() > 1 && s.startsWith(HASH_TAG);
    }

    public static boolean isHashTag(char c) {
        return c == HASH_TAG.charAt(0);
    }

    public static boolean isNickName(String s) {
        return s != null && !s.isEmpty() && s.startsWith(NICK_NAME);
    }

    @NonNull
    public static String getFirstLetter(@Nullable String... items) {
        if (items == null) {
            return "";
        }
        for (int i = 0, size = items.length; i < size; ++i) {
            String text = items[i];
            if (!TextUtils.isEmpty(text)) {
                return Character.toString(text.charAt(0));
            }
        }
        return "";
    }

    public static boolean equals(@Nullable String str1, @Nullable String str2, boolean ignoreCase) {
        if (ignoreCase) {
            return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
        } else {
            return str1 == null ? str2 == null : str1.equals(str2);
        }
    }

    public static boolean allEmpty(@Nullable CharSequence... str) {
        if (str == null) {
            return true;
        }
        for (CharSequence s : str) {
            if (!TextUtils.isEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    public static boolean allNotEmpty(@Nullable CharSequence... str) {
        if (str == null) {
            return false;
        }
        for (CharSequence s : str) {
            if (TextUtils.isEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns null if all strings empty
     *
     * @param strings strings to check for emptiness
     * @return null if all strings is empty, first non-empty string otherwise
     */
    @Nullable
    public static String firstNotEmpty(String... strings) {
        if (strings != null) {
            for (String s : strings) {
                if (!TextUtils.isEmpty(s)) {
                    return s;
                }
            }
        }
        return null;
    }

    @Nullable
    public static String toStringOrNull(@Nullable Object object) {
        return object == null ? null : object.toString();
    }

    public static int strLength(@Nullable String s) {
        return TextUtils.isEmpty(s) ? 0 : s.length();
    }

    public static String filterHashTagSymbol(@Nullable String query) {
        if (query != null && !query.isEmpty() && query.startsWith(HASH_TAG)) {
            return query.substring(1);
        }
        return query;
    }

    public static String filterDiscoverSearchQuery(@Nullable String query) {
        if (query == null) {
            return null;
        }
        query = query.trim();
        if (!query.isEmpty() && (query.startsWith(HASH_TAG) || query.startsWith(NICK_NAME))) {
            return query.substring(1);
        }
        return query;
    }

    @Nullable
    public static String joinToString(Iterable c, String separator) {
        if (c != null) {
            StringBuilder sb = new StringBuilder();
            for (Object o : c) {
                sb.append(o.toString()).append(separator);
            }
            return sb.toString();
        }
        return null;
    }
}
