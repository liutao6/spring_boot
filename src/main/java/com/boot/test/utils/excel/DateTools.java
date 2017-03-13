package com.boot.test.utils.excel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA. User: tao Date: 13-6-20 Time: 上午11:52 To change
 * this template use File | Settings | File Templates. 日期公共类
 */
public class DateTools {

    /**
     * Expanded ISO 8601 Date format yyyy-MM-dd i.e., 2002-12-25 for the 25th
     * day of December in the year 2002
     */
    public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * yyyy-MM-dd hh:mm:ss 12小时制
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

    /**
     * yyyy-MM-dd HH:mm:ss 24小时制
     */
    public static final String DATETIME_PATTERN_TWO = "yyyy-MM-dd HH:mm";
    /**
     * yyyy-MM-dd HH:mm:ss 24小时制
     */
    public static final String DATETIME_PATTERN_THREE = "HH:mm:ss";

    /**
     * 计算俩个日期相隔分钟
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static Integer dateDiff(Date startTime, Date endTime) {
        Calendar dateOne = Calendar.getInstance(), dateTwo = Calendar.getInstance();
        dateOne.setTime(startTime);
        dateTwo.setTime(endTime);
        long timeOne = dateOne.getTimeInMillis();
        long timeTwo = dateTwo.getTimeInMillis();
        long minute = (timeOne - timeTwo) / (1000 * 60);//转化minute
        return Integer.valueOf(String.valueOf(minute));
    }

    /**
     * 计算俩个日期相隔分钟
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static Integer dateDiffDay(Date startTime, Date endTime) {
        Calendar dateOne = Calendar.getInstance(), dateTwo = Calendar.getInstance();
        dateOne.setTime(startTime);
        dateTwo.setTime(endTime);
        long timeOne = dateOne.getTimeInMillis();
        long timeTwo = dateTwo.getTimeInMillis();
        long minute = (timeOne - timeTwo) / (1000 * 60 * 60 * 24) + 1;//转化minute
        return Integer.valueOf(String.valueOf(minute));
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateText 字符串
     * @param format   日期格式
     * @return Date
     */
    public static Date strToDate(String dateText, String format) {
        if (dateText == null) {
            return null;
        }
        DateFormat df = null;
        try {
            if (format == null) {
                df = new SimpleDateFormat();
            } else {
                df = new SimpleDateFormat(format);
            }
            df.setLenient(false);
            return df.parse(dateText);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将Date类型转换为字符串
     *
     * @param date    日期类型
     * @param pattern 字符串格式
     * @return 日期字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "null";
        }
        if (pattern == null || pattern.equals("") || pattern.equals("null")) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将Date类型转换为字符串
     *
     * @param date 日期类型
     * @return 日期字符串
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    /**
     * 根据时间变量返回时间字符串
     *
     * @param pattern 时间字符串样式
     * @param date    时间变量
     * @return 返回时间字符串
     */
    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        try {
            SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
            // sfDate.setLenient(false);
            return sfDate.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据时间变量返回时间字符串 yyyy-MM-dd HH:mm
     *
     * @param date
     * @return String
     */
    public static String dateToString(Date date) {
        return dateToString(date, DATETIME_PATTERN_TWO);
    }

    /**
     * 根据时间变量返回时间字符串 yyyy-MM-dd HH:mm
     *
     * @param date
     * @return String
     */
    public static String dateToStringTwo(Date date) {
        return dateToString(date, ISO_EXPANDED_DATE_FORMAT);
    }


    /**
     * 在天数加1
     *
     * @param currentDate
     * @return
     */
    public static Date nextDay(Date currentDate) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(currentDate);
        cal.add(GregorianCalendar.DAY_OF_MONTH, 1);// 在天数加1
        return cal.getTime();
    }

    /**
     * 24小时制字符串转日期
     *
     * @param dateString
     * @return
     */
    public static Date strToDate(String dateString) {
        return strToDate(dateString, DATETIME_PATTERN_TWO);
    }


    /**
     * 24小时制字符串转日期
     *
     * @param dateString
     * @return
     */
    public static Date strToDateTwo(String dateString) {
        return strToDate(dateString, ISO_EXPANDED_DATE_FORMAT);
    }

    /**
     * 当前时间是星期几(weekday=1，当天是周日；weekday=2，当天是周一；...;weekday=7，当天是周六。)
     */
    public static String getWeekday(Date date) {
        Integer weekday = getWeekdayNum(date);
        if (weekday == 1) {
            return "日";
        } else if (weekday == 2) {
            return "一";
        } else if (weekday == 3) {
            return "二";
        } else if (weekday == 4) {
            return "三";
        } else if (weekday == 5) {
            return "四";
        } else if (weekday == 6) {
            return "五";
        } else if (weekday == 7) {
            return "六";
        }
        return "";
    }

    public static Integer getWeekdayNum(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        return weekday;
    }

    public static Integer getDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.DATE);
        return weekday;
    }

    public static Integer getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.MONTH);
        return weekday;
    }

    public static Integer getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.YEAR);
        return weekday;
    }

    public static Date getStrDateByYearMonthDay(int year, int month, int day) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        return c.getTime();
    }


    public static List<String> getDateByYear(Integer year) {//日期查询
        Calendar calendar = new GregorianCalendar();//定义一个日历，变量作为年初
        Calendar calendarEnd = new GregorianCalendar();//定义一个日历，变量作为年末
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置年初的日期为1月1日
        calendarEnd.set(Calendar.YEAR, year);
        calendarEnd.set(Calendar.MONTH, 11);
        calendarEnd.set(Calendar.DAY_OF_MONTH, 31);//设置年末的日期为12月31日

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dateList = new ArrayList<>();
        while (calendar.getTime().getTime() <= calendarEnd.getTime().getTime()) {//用一整年的日期循环
            dateList.add(sf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);//日期+1
        }
        return dateList;
    }

    public static void main(String[] args) {
        System.out.print(dateDiffDay(DateTools.strToDate("2016-03-25 00:00:00"), DateTools.strToDate("2016-02-26 00:00:00")));
    }
}