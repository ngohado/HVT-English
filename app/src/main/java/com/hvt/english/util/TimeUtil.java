package com.hvt.english.util;

import java.util.Calendar;

/**
 * Created by Hado on 7/19/17.
 */

public class TimeUtil {
    public static long generateStringTime() {
        Calendar today = Calendar.getInstance();
        return Long.valueOf(new StringBuilder()
                .append(today.get(Calendar.YEAR))
                .append(today.get(Calendar.MONTH) + 1)
                .append(today.get(Calendar.DATE))
                .toString());
    }
}
