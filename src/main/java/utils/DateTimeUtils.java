package utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期时间工具类
 * TODO javadoc for org.eking.framework.utils.DateTimeUtils
 *
 * @author: xie-qing
 * @since: 2012-5-11
 */
public class DateTimeUtils {

    public final static String sdfyyyy = "yyyy";

    public final static String sdfyyyyMM = "yyyy-MM";

    public final static String sdfyyyyMMdd = "yyyy-MM-dd";

    public final static String sdfyyyyMMddHH = "yyyy-MM-dd HH";

    public final static String sdfyyyyMMddHHmm = "yyyy-MM-dd HH:mm";

    public final static String sdfyyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

    public final static String sdfMM = "MM";

    public final static String sdfdd = "dd";

    public final static String sdfHH = "HH";

    public final static String sdfmm = "mm";

    public final static String sdfss = "ss";

    public final static String sdfHHmm = "HH:mm";

    public final static String sdfHHmmss = "HH:mm:ss";

    /**
     * 是否是同一天
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static boolean isSameDate(Date date1, Date date2) throws ParseException {
        return toDayStart(date1).getTime() == toDayStart(date2).getTime();
    }

    /**
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     * @description: 比较date1是否在date2之前
     * @creator: xie-qing
     * @createDate: 2012-8-20
     * @modifier:
     * @modifiedDate:
     */
    public static boolean isBefore(Date date1, Date date2) throws ParseException {
        return toDayStart(date1).getTime() <= toDayStart(date2).getTime();
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(LocalDateTime date, String pattern) {
        if (null != date)
            return DateTimeFormatter.ofPattern(pattern).format(date);
        return "";
    }


    /**
     * 字符串转换成日期
     *
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String date, String pattern) throws ParseException {
        if (null != date && null != pattern) {
            return DateUtils.parseDate(date, new String[]{
                    pattern
            });
        } else {
            return null;
        }
    }

    /***
     * 获取当前时间为中心向前某个月的起始时间
     * @return
     * @throws ParseException
     */
    public static Date getBeginSomeTime(int a) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -a);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return toDayStart(calendar.getTime());
    }

    /***
     * 获取当前时间为中心向前某个月的结束时间
     * @return
     * @throws ParseException
     */
    public static Date getEndSomeTime(int a) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return toDayEnd(calendar.getTime());
    }


    /***
     * 获取本月的开始时间
     * @return
     * @throws ParseException
     */
    public static Date getBeginDayOfMonth() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getYear(), getMonth() - 1, 1);
        return toDayStart(calendar.getTime());
    }

    /***
     * 获取本月的结束时间
     * @return
     * @throws ParseException
     */
    public static Date getEndDayOfMonth() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getYear(), getMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getYear(), getMonth() - 1, day);
        return toDayEnd(calendar.getTime());
    }


    /**
     * 获取本年的开始时间
     *
     * @return
     * @throws ParseException
     */
    public static Date getBeginDayOfYear() throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getYear());
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return toDayStart(cal.getTime());
    }

    /**
     * 获取本年的结束时间
     *
     * @return
     * @throws ParseException
     */
    public static Date getEndDayOfYear() throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return toDayEnd(cal.getTime());
    }

    /**
     * 指定日期的开始时间: 2010-10-10 00:00:00
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date toDayStart(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(sdfyyyyMMdd);
        if (null != date) {
            return sdf.parse(sdf.format(date));
        } else {
            return null;
        }
    }

    /**
     * 指定日期的最后时间: 2010-10-10 23:59:59
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date toDayEnd(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(sdfyyyyMMdd);
        Date tmp = null;
        if (null != date) {
            tmp = sdf.parse(sdf.format(date));
        } else {
            return null;
        }
        return new Date(tmp.getTime() + 24 * 60 * 60 * 1000 - 1);
    }

    /**
     * 获取当前日期的年份
     *
     * @return
     */
    public static int getYear() {
        return getYear(new Date());
    }

    /**
     * 获取指定日期的年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        String year = DateFormatUtils.format(date, sdfyyyy);
        return Integer.parseInt(year);

    }

    /**
     * 获取当前日期的月份
     *
     * @return
     */
    public static int getMonth() {
        return getMonth(new Date());
    }

    /**
     * 获取指定日期的月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        String month = DateFormatUtils.format(date, sdfMM);
        return Integer.parseInt(month);
    }

    /**
     * 获取当前日期几号
     *
     * @return
     */
    public static int getDay() {
        return getDay(new Date());
    }

    /**
     * 获取指定日期几号
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        String day = DateFormatUtils.format(date, sdfdd);
        return Integer.parseInt(day);
    }

    /**
     * 获取当前日期几点
     *
     * @return
     */
    public static int getHour() {
        return getHour(new Date());
    }

    /**
     * 获取指定日期几点
     *
     * @return
     */
    public static int getHour(Date date) {
        String hours = DateFormatUtils.format(date, sdfHH);
        return Integer.parseInt(hours);
    }

    /**
     * 获取当前日期几分钟
     *
     * @return
     */
    public static int getMinute() {
        return getMinute(new Date());
    }

    /**
     * 获取指定日期几分钟
     *
     * @return
     */
    public static int getMinute(Date date) {
        String minutes = DateFormatUtils.format(date, sdfmm);
        return Integer.parseInt(minutes);
    }

    /**
     * 获取当前日期几秒钟
     *
     * @return
     */
    public static int getSecond() {
        return getSecond(new Date());
    }

    /**
     * 获取指定日期几秒钟
     *
     * @return
     */
    public static int getSecond(Date date) {
        String seconds = DateFormatUtils.format(date, sdfss);
        return Integer.parseInt(seconds);
    }

    /**
     * 获取当前日期是星期几
     *
     * @return
     */
    public static int getWeek() {
        return getWeek(new Date());
    }

    /**
     * 获取指定日期是星期几
     *
     * @return
     */
    public static int getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return week < 0 ? 0 : week;
    }

    /**
     * 获取当前月有多少天
     *
     * @param date
     * @return
     */
    public static int getMonthDays(Date date) {
        if (date == null) {
            throw new RuntimeException("date is null");
        }
        int year = getYear(date);
        int month = getMonth(date);
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 2) {
            if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                return 29;
            } else
                return 28;
        } else {
            return 30;
        }
    }

    /**
     * 获取两个日期之间相差几年(2011-10-10至2013-10-09 算1年, 2011-10-10至2013-10-10 算2年)
     *
     * @param date1 开始日期
     * @param date2 结束日期
     * @return
     */
    public static int getDiffYears(Date date1, Date date2) {
        //return Math.abs(getYear(date2) - getYear(date1));
        int y = getYear(date2), m = getMonth(date2), d = getDay(date2), yFrom = getYear(date1), mFrom = getMonth(date1), dFrom = getDay(date1);
        // 1. 当前的月份大于出生月份
        if (m > mFrom) {
            return y - yFrom;
        }
        // 2. 当前的月份等于出生月份
        if (m == mFrom) {
            if (d >= dFrom) {
                return y - yFrom;
            } else {
                return y - yFrom - 1;
            }
        }
        // 3. 当前的月份小于出生月份
        if (m < mFrom) {
            return y - yFrom - 1;
        }
        return 0;
    }

    /**
     * 获取两个日期之间相差几月
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDiffMonths(Date date1, Date date2) {
        return Math.abs(getYear(date2) * 12 + getMonth(date2) - getYear(date1) * 12 - getMonth(date1));
    }

    /**
     * 获取两个日期之间相差几天
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int getDiffDays(Date date1, Date date2) throws ParseException {
        long diffTimes = Math.abs(toDayStart(date2).getTime() - toDayStart(date1).getTime());
        return (int) (diffTimes / (1000 * 60 * 60 * 24));
    }

    /**
     * 获取当前月份的第一天
     *
     * @return
     * @throws ParseException
     */
    public static Date getFirstDayOfCurrentMonth() throws ParseException {
        return getFirstDayOfCurrentMonth(new Date());
    }

    /**
     * 获取指定月份的第一天
     *
     * @return
     * @throws ParseException
     */
    public static Date getFirstDayOfCurrentMonth(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(sdfyyyyMM);
        if (null != date) {
            return sdf.parse(sdf.format(date));
        } else {
            return null;
        }
    }

    /**
     * 获取当前月份的最后一天
     *
     * @return
     * @throws ParseException
     */
    public static Date getLastDayOfCurrentMonth() throws ParseException {
        return getLastDayOfCurrentMonth(new Date());
    }

    /**
     * 获取指定月份的最后一天
     *
     * @return
     * @throws ParseException
     */
    public static Date getLastDayOfCurrentMonth(Date date) throws ParseException {
        Date temp = DateUtils.addMonths(date, 1);
        SimpleDateFormat sdf = new SimpleDateFormat(sdfyyyyMM);
        if (null != date) {
            Long time = sdf.parse(sdf.format(temp)).getTime();
            return new Date(time - 1);
        } else {
            return null;
        }
    }

    /**
     * 获取当前星期的第一天(周一)
     *
     * @return
     */
    public static Date getFirstDayOfCurrentWeek() {
        return getFirstDayOfCurrentWeek(new Date());
    }

    /**
     * 获取指定星期的第一天(周一)
     *
     * @return
     */
    public static Date getFirstDayOfCurrentWeek(Date date) {
        int week = getWeek(date);
        //星期天
        if (0 == week) {
            return DateUtils.addDays(date, -6);
        } else {
            return DateUtils.addDays(date, 1 - week);
        }
    }

    /**
     * 获取当前星期的最后一天(周日)
     *
     * @return
     */
    public static Date getLastDayOfCurrentWeek() {
        return getLastDayOfCurrentWeek(new Date());
    }

    /**
     * 获取指定星期的最后一天(周日)
     *
     * @return
     */
    public static Date getLastDayOfCurrentWeek(Date date) {
        int week = getWeek(date);
        //星期天
        if (0 == week) {
            return date;
        } else {
            return DateUtils.addDays(date, 7 - week);
        }
    }

    /**
     * 根据出生日期获取年龄
     *
     * @param birthday
     * @return
     */
    public static int getAge(Date birthday) {
        int y = getYear(), m = getMonth(), d = getDay(), yFrom = getYear(birthday), mFrom = getMonth(birthday), dFrom = getDay(birthday);
        // 1. 当前的月份大于出生月份
        if (m > mFrom) {
            return y - yFrom;
        }
        // 2. 当前的月份等于出生月份
        if (m == mFrom) {
            if (d >= dFrom) {
                return y - yFrom;
            } else {
                return y - yFrom - 1;
            }
        }
        // 3. 当前的月份小于出生月份
        if (m < mFrom) {
            return y - yFrom - 1;
        }
        return 0;
    }

    /**
     * @param i 负数表示向前算多少天，正数表示往后多少天
     * @return
     * @description: 获得向前或者向后算的日期
     * @creator: 符德维
     * @createDate: 2014年11月25日
     * @modifier:
     * @modifiedDate:
     */
    public static Date getDateBySpecify(int i) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, i);
        return now.getTime();
    }

    /**
     * @param i 负数表示向前算多少天，正数表示往后多少天
     * @return
     * @description: 获得向前或者向后算的日期
     * @creator: 符德维
     * @createDate: 2014年11月25日
     * @modifier:
     * @modifiedDate:
     */
    public static String getDateBySpecify(int i, String pattern) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, i);
        return dateToString(now.getTime(), pattern);
    }

    /**
     * @param date 指定的时间
     * @param i    负数表示向前算多少天，正数表示往后多少天
     * @return
     * @description: 返回指定的时间向前或者向后几天的日期
     * @creator: 符德维
     * @createDate: 2014年11月26日
     * @modifier:
     * @modifiedDate:
     */
    public static Date getDateBySpecify(Date date, int i) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE, i);
        return rightNow.getTime();
    }

    /**
     * @param date    指定的时间
     * @param i       负数表示向前算多少天，正数表示往后多少天
     * @param pattern 时间格式
     * @return
     * @description: 返回指定的时间向前或者向后几天的日期
     * @creator: 符德维
     * @createDate: 2014年11月26日
     * @modifier:
     * @modifiedDate:
     */
    public static String getDateBySpecify(Date date, int i, String pattern) {
        return dateToString(getDateBySpecify(date, i), pattern);
    }

    /**
     * @param date
     * @return
     * @description: 返回YYYY-MM-DD格式的字符串
     * @creator: 符德维
     * @createDate: 2014年12月2日
     * @modifier:
     * @modifiedDate:
     */
    public static String dateToYYYYMMDD(Date date) {
        return dateToString(date, sdfyyyyMMdd);
    }

    /**
     * @param pattern
     * @return
     * @description: 获取当前的日期
     * @creator: hsh.chen
     * @createDate: 2016年10月11日
     * @modifier:
     * @modifiedDate:
     */
    public static String currentDate(String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String returnTime = null;
        Date currentTime = new Date();
        returnTime = formatter.format(currentTime);
        return returnTime;
    }

    /**
     * @param strDate 传入的格式是 yyyy-MM-dd HH:mm:ss
     * @return
     * @description: 获取指定日期与当前时间差   如果是同一天 返回时差   如果不同天  则返回日期
     * @creator: hsh.chen
     * @createDate: 2016年10月11日
     * @modifier:
     * @modifiedDate:
     */
    public static String currentTimeDiff(String strDate) {
        String returnStr = null;
        String currentDate = currentDate(sdfyyyyMMddHHmmss);//当前日期
        if (!strDate.isEmpty()) {
            if (strDate.substring(0, 11).equals(currentDate.substring(0, 11))) {//比较时间
                int currentHour = Integer.parseInt(currentDate.substring(11, 13));
                int strDateHour = Integer.parseInt(strDate.substring(11, 13));
                if (currentHour == strDateHour) {//小时相等 返回分钟
                    int currentSec = Integer.parseInt(currentDate.substring(14, 16));
                    int strDateSec = Integer.parseInt(strDate.substring(14, 16));

                    if (currentSec == strDateSec) {//分钟相等 返回秒
                        int currentMin = Integer.parseInt(currentDate.substring(17, 19));
                        int strDateMin = Integer.parseInt(strDate.substring(17, 19));
                        return (currentMin - strDateMin) + "秒前";
                    } else {
                        return (currentSec - strDateSec) + "分钟前";
                    }

                } else {
                    return (currentHour - strDateHour) + " 小时前";
                }

            } else {
                returnStr = strDate.substring(0, 11);
            }
        }
        return returnStr;

    }

    /**
     * @param date
     * @return
     * @description: 获取指定日期与当前时间差   如果是同一天 返回时差   如果不同天  则返回日期
     * @creator: hsh.chen
     * @createDate: 2016年10月11日
     * @modifier:
     * @modifiedDate:
     */
    public static String currentTimeDiff(Date date) {
        String returnStr = null;
        String currentDate = currentDate(sdfyyyyMMddHHmmss);//当前日期
        String strDate = dateToString(date, sdfyyyyMMddHHmmss);
        try {
            if (strDate.substring(0, 11).equals(currentDate.substring(0, 11))) {//比较时间
                int currentHour = Integer.parseInt(currentDate.substring(11, 13));
                int strDateHour = Integer.parseInt(strDate.substring(11, 13));
                if (currentHour == strDateHour) {//小时相等 返回分钟
                    int currentSec = Integer.parseInt(currentDate.substring(14, 16));
                    int strDateSec = Integer.parseInt(strDate.substring(14, 16));

                    if (currentSec == strDateSec) {//分钟相等 返回秒
                        int currentMin = Integer.parseInt(currentDate.substring(17, 19));
                        int strDateMin = Integer.parseInt(strDate.substring(17, 19));
                        return (currentMin - strDateMin) + "秒前";
                    } else {
                        return (currentSec - strDateSec) + "分钟前";
                    }

                } else {
                    return (currentHour - strDateHour) + " 小时前";
                }

            } else {
                returnStr = strDate.substring(0, 11);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStr;
    }

    /**
     * @param date 指定的时间
     * @param i    负数表示向前算多少天，正数表示往后多少天
     * @return
     * @description: 返回指定年的时间向前或者向后几天的日期
     * @creator: 符德维
     * @createDate: 2014年11月26日
     * @modifier:
     * @modifiedDate:
     */
    public static Date getYearBySpecify(Date date, int i) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.YEAR, i);
        return rightNow.getTime();
    }
}
