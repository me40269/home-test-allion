package com.allion.issuetracker.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class WeekNumberUtil {
    final static SimpleDateFormat YEAR_WEEK_FORMAT = new SimpleDateFormat("yyyyww");
    final static SimpleDateFormat OUT_SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getFromYearWeek(String yearWeek) {
        String[] split = yearWeek.toUpperCase().split("W");
        String yearWeekCompound = Arrays.stream(split).collect(Collectors.joining(""));
        Date date;
        try {

            YEAR_WEEK_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
            date = YEAR_WEEK_FORMAT.parse(yearWeekCompound);
            OUT_SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));

            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;

        }

    }

    public static Date getDate(String strDate) {
        try {
            OUT_SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
            return OUT_SIMPLE_DATE_FORMAT.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;

        }

    }


    public static String getWeekNumber(String dateStr) {
        OUT_SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        Date date;
        try {
            date = OUT_SIMPLE_DATE_FORMAT.parse(dateStr);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getWeekYear() + "W" + String.format("%02d", calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }

}
