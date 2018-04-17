package com.magict.magic.util;



import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZP
 * @date 2018/4/13
 */
public class DateUtil {
    /**
     * 时间戳无分隔符号格式
     */
    public static final String DATETIME_DENSE = "yyyyMMddHHmmss";

    /**
     * 时间戳类型格式
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期类型格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 时间类型的格式
     */
    public static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * 中文时间戳类型格式
     */
    public static final String DATETIME_FORMAT_CN = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * 中文日期类型格式
     */
    public static final String DATE_FORMAT_CN = "yyyy年MM月dd日";

    /**
     * 中文时间类型的格式
     */
    public static final String TIME_FORMAT_CN = "HH时mm分ss秒";

    //禁止实例化
    private DateUtil() {}

    //注意SimpleDateFormat不是线程安全的
    private static ThreadLocal<SimpleDateFormat> ThreadDateTime = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> ThreadDate = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> ThreadTime = new ThreadLocal<SimpleDateFormat>();

    private static SimpleDateFormat DateTimeInstance() {
        SimpleDateFormat df = ThreadDateTime.get();
        if (df == null) {
            df = new SimpleDateFormat(DATETIME_FORMAT);
            ThreadDateTime.set(df);
        }
        return df;
    }

    private static SimpleDateFormat DateInstance() {
        SimpleDateFormat df = ThreadDate.get();
        if (df == null) {
            df = new SimpleDateFormat(DATE_FORMAT);
            ThreadDate.set(df);
        }
        return df;
    }

    private static SimpleDateFormat TimeInstance() {
        SimpleDateFormat df = ThreadTime.get();
        if (df == null) {
            df = new SimpleDateFormat(TIME_FORMAT);
            ThreadTime.set(df);
        }
        return df;
    }

    /**
     * 格式化当前日期按照指定规则
     *
     * @param pattern 格式
     * @return 指定格式的日期
     */
    public static String formatCurrentDate(String pattern) {
        return formatDate(new Date(),pattern);
    }

    /**
     * 格式化日期按照指定规则
     *
     * @param date 日期
     * @param pattern 格式
     * @return 指定格式的日期
     */
    public static String formatDate(Date date,String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 获取当前时间戳
     *
     * @return 返回当前时间戳的字符串值
     */
    public static String currentDateTime() {
        return dateTime(new Date());
    }

    /**
     * 将指定的Date格式化成时间戳字符串
     *
     * @param date 日期
     * @return
     */
    public static String dateTime(Date date) {
        return DateTimeInstance().format(date);
    }

    /**
     * 将指定的字符串解析为时间戳类型Date
     *
     * @param dateStr 日期字符串
     * @return
     * @throws ParseException
     */
    public static Date dateTime(String dateStr) throws ParseException {
        return DateTimeInstance().parse(dateStr);
    }

    /**
     * 获取当前的日期
     *
     * @return
     */
    public static String currentDate() {
        return date(new Date());
    }

    /**
     * 将指定的Date格式化成日期类型字符串
     *
     * @param date 日期
     * @return
     */
    public static String date(Date date) {
        return DateInstance().format(date);
    }

    /**
     * 将指定的字符串解析为日期类型Date
     *
     * @param dateStr 日期字符串
     * @return
     * @throws ParseException
     */
    public static Date date(String dateStr) throws ParseException {
        return DateInstance().parse(dateStr);
    }

    /**
     * 获取当前的时间
     *
     * @return
     */
    public static String currentTime() {
        return time(new Date());
    }

    /**
     * 将指定的Date格式化成时间类型字符串
     *
     * @param date 日期
     * @return
     */
    public static String time(Date date) {
        return TimeInstance().format(date);
    }

    /**
     * 将指定的字符串解析为时间类型Date
     *
     * @param dateStr 日期字符串
     * @return
     * @throws ParseException
     */
    public static Date time(String dateStr) throws ParseException {
        return TimeInstance().parse(dateStr);
    }

    /**
     * 返回年份
     *
     * @param date 日期
     * @return 返回年份
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回月份
     *
     * @param date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回在月份中的第几天
     *
     * @param date 日期
     * @return 日
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回分钟
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param dateStr 日期字符串
     * @return true or false
     */
    public static boolean isDate(String dateStr) {
        try {
            dateTime(dateStr);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 时间差-单位秒
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 秒
     */
    public static int subtractSecond(Date date1, Date date2) {
        long cha = (date1.getTime() - date2.getTime()) / 1000;
        return (int)cha;
    }

    /**
     * 时间差-单位秒
     *
     * @param dateStr1 日期字符串1
     * @param dateStr2 日期字符串2
     * @return 秒
     * @throws ParseException
     */
    public static long subtractSecond(String dateStr1, String dateStr2) throws ParseException {
        return subtractSecond(dateTime(dateStr1), dateTime(dateStr2));
    }

    /**
     * 时间差-单位分钟
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 分钟
     */
    public static int subtractMinute(Date date1, Date date2) {
        long cha = date1.getTime() - date2.getTime();
        return (int) cha / (1000 * 60);
    }

    /**
     * 时间差 -单位分钟
     *
     * @param dateStr1 日期字符串1
     * @param dateStr2 日期字符串2
     * @return 分钟
     * @throws ParseException
     */
    public static int subtractMinute(String dateStr1, String dateStr2) throws ParseException {
        return subtractMinute(dateTime(dateStr1), dateTime(dateStr2));
    }

    /**
     * 时间差-单位小时
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 小时
     */
    public static int subtractHour(Date date1, Date date2) {
        long cha = (date1.getTime() - date2.getTime()) / 1000;
        return Math.abs((int) cha / (60 * 60));
    }

    /**
     * 时间差-单位小时
     *
     * @param dateStr1 日期字符串1
     * @param dateStr2 日期字符串2
     * @return 小时
     * @throws ParseException
     */
    public static int subtractHour(String dateStr1, String dateStr2) throws ParseException {
        return subtractHour(dateTime(dateStr1), dateTime(dateStr2));
    }

    /**
     * 时间差-单位天
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 天
     */
    public static int subtractDay(Date date1, Date date2) {
        long cha = date1.getTime() - date2.getTime();
        return (int) cha / (1000 * 60 * 60 * 24);
    }

    /**
     * 时间差-单位天
     *
     * @param dateStr1 日期字符串1
     * @param dateStr2 日期字符串2
     * @return 天
     * @throws ParseException
     */
    public static int subtractDay(String dateStr1, String dateStr2) throws ParseException {
        return subtractDay(dateTime(dateStr1), dateTime(dateStr2));
    }

    /**
     * 时间差-单位月
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 月
     */
    public static int subtractMonth(Date date1, Date date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH);
        if (year1 == year2) {
            result = month1 - month2;
        } else {
            result = 12 * (year1 - year2) + month1 - month2;
        }
        return result;
    }

    /**
     * 时间差-单位月
     *
     * @param dateStr1 日期字符串1
     * @param dateStr2 日期字符串2
     * @return 月
     * @throws ParseException
     */
    public static int subtractMonth(String dateStr1, String dateStr2) throws ParseException {
        return subtractMonth(date(dateStr1), date(dateStr2));
    }

    /**
     * 时间差-单位年
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 年
     */
    public static int subtractYear(Date date1, Date date2) {
        int result;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        result = year1 - year2;
        return result;
    }

    /**
     * 时间差-单位年
     *
     * @param dateStr1
     * @param dateStr2
     * @return 年
     * @throws ParseException
     */
    public static int subtractYear(String dateStr1, String dateStr2) throws ParseException {
        return subtractYear(date(dateStr1), date(dateStr2));
    }

    /**
     * 获取俩个时间之前的相隔的天数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return
     * @throws ParseException
     */
    public static int subDay(Date date1, Date date2) {
        int days = 0;
        Calendar can1 = Calendar.getInstance();
        can1.setTime(date2);
        Calendar can2 = Calendar.getInstance();
        can2.setTime(date1);
        int year1 = can1.get(Calendar.YEAR);
        int year2 = can2.get(Calendar.YEAR);
        Calendar can = null;
        if (can1.before(can2)) {
            days -= can1.get(Calendar.DAY_OF_YEAR);
            days += can2.get(Calendar.DAY_OF_YEAR);
            can = can1;
        } else {
            days -= can2.get(Calendar.DAY_OF_YEAR);
            days += can1.get(Calendar.DAY_OF_YEAR);
            can = can2;
        }
        for (int i = 0; i < Math.abs(year2 - year1); i++) {
            days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
            can.add(Calendar.YEAR, 1);
        }
        return days;
    }

    /**
     * 获取俩个时间之前的相隔的天数
     *
     * @param dateStr1 日期字符串1
     * @param dateStr2 日期字符串2
     * @return
     * @throws ParseException
     */
    public static int subDay(String dateStr1, String dateStr2) throws ParseException {
        return subDay(dateTime(dateStr1), dateTime(dateStr2));
    }

    /**
     * 判断日期是否在某个时间段内
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param date 日期
     * @return
     */
    public static boolean between(Date startTime,Date endTime,Date date){
        return date.after(startTime) && date.before(endTime);
    }

    /**
     * 判断日期是否在某个时间段内
     * @param startTimeStr 开始时间字符串
     * @param endTimeStr 结束时间字符串
     * @param date 日期
     * @return
     * @throws ParseException
     */
    public static boolean between(String startTimeStr,String endTimeStr,Date date)
            throws ParseException {
        return between(dateTime(startTimeStr),dateTime(endTimeStr),date);
    }

    /**
     * 获取俩个时间的查结果用时分秒表示
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 几小时:几分钟:几秒钟
     * @Summary:此处可以讲计算结果包装成一个结构体返回便于格式化
     */
    public static String subtractTime(Date date1, Date date2) {
        String result = "";
        long sss = (date1.getTime() - date2.getTime()) / 1000;
        int hh = (int) sss / (60 * 60);
        int mm = (int) (sss - hh * 60 * 60) / (60);
        int ss = (int) (sss - hh * 60 * 60 - mm * 60);
        result = hh + ":" + mm + ":" + ss;
        return result;
    }

    /**
     * 获取俩个时间的查结果用时分秒表示
     *
     * @param dateStr1 日期字符串1
     * @param dateStr2 日期字符串2
     * @return 几小时:几分钟:几秒钟
     * @throws ParseException
     * @Summary:此处可以讲计算结果包装成一个结构体返回便于格式化
     */
    public static String subtractTime(String dateStr1, String dateStr2) throws ParseException {
        return subtractTime(dateTime(dateStr1), dateTime(dateStr2));
    }

    /**
     * 获取俩个时间的差结果用时秒表示
     *
     * @param date1 日期1
     * @param date1 日期2
     * @return 几天-几小时:几分钟:几秒钟
     * @Summary:此处可以讲计算结果包装成一个结构体返回便于格式化
     */
    public static String subtractDate(Date date1, Date date2) {
        String result = "";
        long sss = (date1.getTime() - date2.getTime()) / 1000;
        int dd = (int) sss / (60 * 60 * 24);
        int hh = (int) (sss - dd * 60 * 60 * 24) / (60 * 60);
        int mm = (int) (sss - dd * 60 * 60 * 24 - hh * 60 * 60) / (60);
        int ss = (int) (sss - dd * 60 * 60 * 24 - hh * 60 * 60 - mm * 60);
        result = dd + "-" + hh + ":" + mm + ":" + ss;
        return result;
    }

    /**
     * 获取俩个时间的差结果用时秒表示
     *
     * @param dateStr1 日期字符串1
     * @param dateStr2 日期字符串2
     * @return 几天-几小时:几分钟:几秒钟
     * @throws ParseException
     * @Summary:此处可以讲计算结果包装成一个结构体返回便于格式化
     */
    public static String subtractDate(String dateStr1, String dateStr2) throws ParseException {
        return subtractDate(dateTime(dateStr1), dateTime(dateStr2));
    }

    /**
     * 返回俩个时间在时间段(例如每天的08:00-18:00)的时长-单位秒
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param timeBurst 只就按该时间段内的08:00-18:00时长
     * @return 计算后的秒数
     * @throws ParseException
     */
    public static long subtimeBurst(Date startTime, Date endTime, String timeBurst)
            throws ParseException {
        long second = 0;
        Pattern p = Pattern.compile("^\\d{2}:\\d{2}-\\d{2}:\\d{2}");
        Matcher m = p.matcher(timeBurst);
        boolean falg = false;
        if (startTime.after(endTime)) {
            Date temp = startTime;
            startTime = endTime;
            endTime = temp;
            falg = true;
        }
        if (m.matches()) {
            String[] a = timeBurst.split("-");
            int day = subDay(startTime, endTime);
            if (day > 0) {
                long firstMintues = 0;
                long lastMintues = 0;
                long daySecond = 0;
                String strDayStart = date(startTime) + " " + a[0] + ":00";
                String strDayEnd = date(startTime) + " " + a[1] + ":00";
                Date dayStart = dateTime(strDayStart);
                Date dayEnd = dateTime(strDayEnd);
                daySecond = subtractSecond(dayStart, dayEnd);
                if ((startTime.after(dayStart) || startTime.equals(dayStart))
                        && startTime.before(dayEnd)) {
                    firstMintues = (dayEnd.getTime() - startTime.getTime()) / 1000;
                } else if (startTime.before(dayStart)) {
                    firstMintues = (dayEnd.getTime() - dayStart.getTime()) / 1000;
                }
                dayStart = dateTime(date(endTime) + " " + a[0] + ":00");
                dayEnd = dateTime(date(endTime) + " " + a[1] + ":00");
                if (endTime.after(dayStart) && (endTime.before(dayEnd) || endTime.equals(dayEnd))) {
                    lastMintues = (endTime.getTime() - dayStart.getTime()) / 1000;
                } else if (endTime.after(dayEnd)) {
                    lastMintues = (dayEnd.getTime() - dayStart.getTime()) / 1000;
                }
                //第一天的秒数 + 最好一天的秒数 + 天数*全天的秒数
                second = firstMintues + lastMintues;
                second += (day - 1) * daySecond;
            } else {
                String strDayStart = date(startTime) + " " + a[0] + ":00";
                String strDayEnd = date(startTime) + " " + a[1] + ":00";
                Date dayStart = dateTime(strDayStart);
                Date dayEnd = dateTime(strDayEnd);
                if ((startTime.after(dayStart) || startTime.equals(dayStart))
                        && startTime.before(dayEnd) && endTime.after(dayStart)
                        && (endTime.before(dayEnd) || endTime.equals(dayEnd))) {
                    second = (endTime.getTime() - startTime.getTime()) / 1000;
                } else {
                    if (startTime.before(dayStart)) {
                        if (endTime.before(dayEnd)) {
                            second = (endTime.getTime() - dayStart.getTime()) / 1000;
                        } else {
                            second = (dayEnd.getTime() - dayStart.getTime()) / 1000;
                        }
                    }
                    if (startTime.after(dayStart)) {
                        if (endTime.before(dayEnd)) {
                            second = (endTime.getTime() - startTime.getTime()) / 1000;
                        } else {
                            second = (dayEnd.getTime() - startTime.getTime()) / 1000;
                        }
                    }
                }
                if ((startTime.before(dayStart) && endTime.before(dayStart))
                        || startTime.after(dayEnd) && endTime.after(dayEnd)) {
                    second = 0;
                }
            }
        } else {
            second = (endTime.getTime() - startTime.getTime()) / 1000;
        }
        if (falg) {
            second = Long.parseLong("-" + second);
        }
        return second;
    }

    /**
     * 返回俩个时间在时间段(例如每天的08:00-18:00)的时长-单位秒
     *
     * @param startTime 开始时间字符串
     * @param endTime 结束时间字符串
     * @param timeBurst 只就按该时间段内的08:00-18:00时长
     * @return 计算后的秒数
     * @throws ParseException
     * @summary 格式错误返回0
     */
    public static long subtimeBurst(String startTime, String endTime, String timeBurst)
            throws ParseException {
        return subtimeBurst(dateTime(startTime), dateTime(endTime), timeBurst);
    }

    /**
     * 时间Date在时间段(例如每天的08:00-18:00)上增加或减去second秒
     *
     * @param date 日期
     * @param second 秒数
     * @param timeBurst
     * @return 计算后的时间
     * @throws ParseException
     * @Suumary 指定的格式错误后返回原数据
     */
    public static Date calculate(Date date, String timeBurst, int second) throws ParseException {
        Pattern p = Pattern.compile("^\\d{2}:\\d{2}-\\d{2}:\\d{2}");
        Matcher m = p.matcher(timeBurst);
        Calendar cal = Calendar.getInstance();
        if (m.matches()) {
            String[] a = timeBurst.split("-");
            Date dayStart = dateTime(date(date) + " " + a[0] + ":00");
            Date dayEnd = dateTime(date(date) + " " + a[1] + ":00");
            int DaySecond = (int) subtractSecond(dayStart, dayEnd);
            int toDaySecond = (int) subtractSecond(dayStart, dayEnd);
            if (second >= 0) {
                if ((date.after(dayStart) || date.equals(dayStart))
                        && (date.before(dayEnd) || date.equals(dayEnd))) {
                    cal.setTime(date);
                    toDaySecond = (int) subtractSecond(date, dayEnd);
                }
                if (date.before(dayStart)) {
                    cal.setTime(dayStart);
                    toDaySecond = (int) subtractSecond(dayStart, dayEnd);
                }
                if (date.after(dayEnd)) {
                    cal.setTime(DateUtils.addDays(dayStart, 1));
                    toDaySecond = 0;
                }

                if (second > toDaySecond) {
                    int day = (second - toDaySecond) / DaySecond;
                    int remainder = (second - toDaySecond) % DaySecond;
                    cal.setTime(DateUtils.addDays(dayStart, 1));
                    cal.add(Calendar.DAY_OF_YEAR, day);
                    cal.add(Calendar.SECOND, remainder);
                } else {
                    cal.add(Calendar.SECOND, second);
                }

            } else {
                if ((date.after(dayStart) || date.equals(dayStart))
                        && (date.before(dayEnd) || date.equals(dayEnd))) {
                    cal.setTime(date);
                    toDaySecond = (int) subtractSecond(date, dayStart);
                }
                if (date.before(dayStart)) {
                    cal.setTime(DateUtils.addDays(dayEnd, -1));
                    toDaySecond = 0;
                }
                if (date.after(dayEnd)) {
                    cal.setTime(dayEnd);
                    toDaySecond = (int) subtractSecond(dayStart, dayEnd);
                }
                if (Math.abs(second) > Math.abs(toDaySecond)) {
                    int day = (Math.abs(second) - Math.abs(toDaySecond)) / DaySecond;
                    int remainder = (Math.abs(second) - Math.abs(toDaySecond)) % DaySecond;
                    cal.setTime(DateUtils.addDays(dayEnd, -1));
                    cal.add(Calendar.DAY_OF_YEAR, Integer.valueOf("-" + day));
                    cal.add(Calendar.SECOND, Integer.valueOf("-" + remainder));
                } else {
                    cal.add(Calendar.SECOND, second);
                }
            }
        } else {
            cal.setTime(date);
        }
        return cal.getTime();
    }

    /**
     * 时间Date在时间段(例如每天的08:00-18:00)上增加或减去second秒
     *
     * @param date 日期字符串
     * @param second 秒数
     * @param timeBurst
     * @return 计算后的时间
     * @throws ParseException
     * @Suumary 指定的格式错误后返回原数据
     */
    public static Date calculate(String date, String timeBurst, int second) throws ParseException {
        return calculate(dateTime(date), timeBurst, second);

    }


    /**
     * 日期计算，加year年month月day天后的日期，可用负数表示减,不加减的传0
     * @param date
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date calDate(Date date,int year,int month,int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)+year);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+day);
        return calendar.getTime();
    }
}
