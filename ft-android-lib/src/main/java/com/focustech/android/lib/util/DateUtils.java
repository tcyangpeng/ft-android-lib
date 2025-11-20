package com.focustech.android.lib.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * <日期转换工具类>
 * 提供日期格式化、转换、计算等常用功能
 *
 * @author focustech
 * @version [版本号, 2024-11-20]
 * @see [相关类/方法]
 * @since [V1]
 */
public final class DateUtils {

    // 常用日期格式
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";
    public static final String FORMAT_HH_MM = "HH:mm";
    public static final String FORMAT_YYYY_MM_DD_CN = "yyyy年MM月dd日";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS_CN = "yyyy年MM月dd日 HH:mm:ss";

    private DateUtils() {
        throw new UnsupportedOperationException("DateUtils cannot be instantiated");
    }

    /**
     * 将日期转换为指定格式的字符串
     *
     * @param date   日期对象
     * @param format 格式化字符串
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date date, String format) {
        if (date == null || GeneralUtils.isNullOrEmpty(format)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * 将日期转换为默认格式的字符串 (yyyy-MM-dd HH:mm:ss)
     *
     * @param date 日期对象
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date date) {
        return formatDate(date, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 将时间戳转换为指定格式的字符串
     *
     * @param timestamp 时间戳（毫秒）
     * @param format    格式化字符串
     * @return 格式化后的日期字符串
     */
    public static String formatTimestamp(long timestamp, String format) {
        return formatDate(new Date(timestamp), format);
    }

    /**
     * 将时间戳转换为默认格式的字符串 (yyyy-MM-dd HH:mm:ss)
     *
     * @param timestamp 时间戳（毫秒）
     * @return 格式化后的日期字符串
     */
    public static String formatTimestamp(long timestamp) {
        return formatTimestamp(timestamp, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 将字符串解析为日期对象
     *
     * @param dateString 日期字符串
     * @param format     格式化字符串
     * @return 日期对象，解析失败返回null
     */
    public static Date parseDate(String dateString, String format) {
        if (GeneralUtils.isNullOrEmpty(dateString) || GeneralUtils.isNullOrEmpty(format)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将字符串解析为日期对象，使用默认格式 (yyyy-MM-dd HH:mm:ss)
     *
     * @param dateString 日期字符串
     * @return 日期对象，解析失败返回null
     */
    public static Date parseDate(String dateString) {
        return parseDate(dateString, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间的Date对象
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取当前时间戳（毫秒）
     *
     * @return 当前时间戳
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间的格式化字符串
     *
     * @param format 格式化字符串
     * @return 格式化后的当前时间字符串
     */
    public static String getCurrentDateString(String format) {
        return formatDate(getCurrentDate(), format);
    }

    /**
     * 获取当前时间的格式化字符串，使用默认格式 (yyyy-MM-dd HH:mm:ss)
     *
     * @return 格式化后的当前时间字符串
     */
    public static String getCurrentDateString() {
        return getCurrentDateString(FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 相差的天数
     */
    public static long getDaysBetween(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        long diff = endDate.getTime() - startDate.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }

    /**
     * 在指定日期上增加或减少天数
     *
     * @param date 原始日期
     * @param days 要增加的天数（负数表示减少）
     * @return 计算后的日期
     */
    public static Date addDays(Date date, int days) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 在指定日期上增加或减少月份
     *
     * @param date   原始日期
     * @param months 要增加的月份（负数表示减少）
     * @return 计算后的日期
     */
    public static Date addMonths(Date date, int months) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /**
     * 在指定日期上增加或减少年份
     *
     * @param date  原始日期
     * @param years 要增加的年份（负数表示减少）
     * @return 计算后的日期
     */
    public static Date addYears(Date date, int years) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的年份
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取指定日期的月份（1-12）
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定日期的天（1-31）
     *
     * @param date 日期
     * @return 天
     */
    public static int getDay(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期是星期几（1-7，1表示星期日）
     *
     * @param date 日期
     * @return 星期几
     */
    public static int getWeekDay(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 判断是否是闰年
     *
     * @param year 年份
     * @return true表示闰年，false表示平年
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * 判断指定日期是否是今天
     *
     * @param date 日期
     * @return true表示是今天，false表示不是
     */
    public static boolean isToday(Date date) {
        if (date == null) {
            return false;
        }
        String today = formatDate(getCurrentDate(), FORMAT_YYYY_MM_DD);
        String target = formatDate(date, FORMAT_YYYY_MM_DD);
        return today.equals(target);
    }

    /**
     * 判断指定日期是否是昨天
     *
     * @param date 日期
     * @return true表示是昨天，false表示不是
     */
    public static boolean isYesterday(Date date) {
        if (date == null) {
            return false;
        }
        String yesterday = formatDate(addDays(getCurrentDate(), -1), FORMAT_YYYY_MM_DD);
        String target = formatDate(date, FORMAT_YYYY_MM_DD);
        return yesterday.equals(target);
    }

    /**
     * 获取指定日期所在月份的第一天
     *
     * @param date 日期
     * @return 月份第一天的日期
     */
    public static Date getFirstDayOfMonth(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在月份的最后一天
     *
     * @param date 日期
     * @return 月份最后一天的日期
     */
    public static Date getLastDayOfMonth(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取两个日期中较早的日期
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 较早的日期
     */
    public static Date min(Date date1, Date date2) {
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        return date1.before(date2) ? date1 : date2;
    }

    /**
     * 获取两个日期中较晚的日期
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 较晚的日期
     */
    public static Date max(Date date1, Date date2) {
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        return date1.after(date2) ? date1 : date2;
    }
}
