package com.headerits.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


/**
 * 时间控制
 *
 * @author xiongmin
 */
public class DateUtils {

    /**
     * 格式：年－月－日 小时:分钟:秒:毫秒
     */
    public static final String COMMON_DATETIME_SSS = "yyyy-MM-dd HH:mm:ss:SSS";

    /**
     * 格式：年－月－日 小时:分钟:秒
     */
    public static final String COMMON_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式：年－月－日 小时:分钟
     */
    public static final String COMMON_DATETIME_MINUTE = "yyyy-MM-dd HH:mm";

    /**
     * 格式：年－月－日
     */
    public static final String LONG_DATE = "yyyy-MM-dd";

    /**
     * 格式：年-月
     */
    public static final String YEAR_MONTH = "yyyy-MM";

    /**
     * 格式：月－日
     */
    public static final String SHORT_DATE = "MM-dd";

    /**
     * 格式：小时:分钟:秒
     */
    public static final String LONG_TIME = "HH:mm:ss";

    /**
     * 格式：yyyy年MM月dd日HH时mm分ss秒
     */
    public static final String COMMON_DATETIME_YMDHMS = "yyyy年MM月dd日HH时mm分ss秒";

    /**
     * 格式：yyyyMMddHHmmssSSS
     */
    public static final String COMMON_DATETIME_SSS_XML = "yyyyMMddHHmmssSSS";

    /**
     * 格式：yyyyMMddHHmmss
     */
    public static final String COMMON_DATETIME_XML = "yyyyMMddHHmmss";

    public static final String[] WEEK_NAMES = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public static final int DEVICE_MISSING_TIME = 30;

    /**
     * 把符合日期格式的字符串转换为日期类型
     *
     * @param dateStr string时间
     * @param format  格式
     * @return
     */
    public static Date string2Date(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            // 严格解析
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            d = null;
        } finally {
            formater = null;
        }
        return d;
    }

    /**
     * 把符合日期格式的字符串转换为日期类型
     */
    public static Date string2Date(String dateStr, String format, ParsePosition pos) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr, pos);
        } catch (Exception e) {
            d = null;
        } finally {
            formater = null;
        }
        return d;
    }

    /**
     * 把日期转换为字符串
     *
     * @param date
     * @return
     */
    public static String date2String(Date date, String format) {
        if (date == null) {
            return "";
        }
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            result = "";
        } finally {
            formater = null;
        }
        return result;
    }

    /**
     * 将失联时间转换为秒、分、时、天单位返回
     *
     * @param lastTime 失联时间单位毫秒
     * @return
     */
    public static String convertLastTimeUnit(long lastTime) {
        String result;
        if (lastTime < TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES)) {
            //1分钟内单位秒
            result = TimeUnit.SECONDS.convert(lastTime, TimeUnit.MILLISECONDS) + "秒";
        } else if (lastTime < TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)) {
            //1小时内单位分
            result = TimeUnit.MINUTES.convert(lastTime, TimeUnit.MILLISECONDS) + "分";
        } else if (lastTime < TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)) {
            //48小时内单位小时
            result = TimeUnit.HOURS.convert(lastTime, TimeUnit.MILLISECONDS) + "小时";
        } else {
            //显示超过两天
            result = ">2天";
        }
        return result;
    }

    /**
     * 判断是不是当天
     *
     * @param thisLoginTime
     * @return
     */
    public static boolean isToday(Date thisLoginTime) {
        String currDateStr = date2String(new Date(), "yyyyMMdd");
        String paramDateStr = date2String(thisLoginTime, "yyyyMMdd");
        return currDateStr.equals(paramDateStr);
    }

    /**
     * 获取当前时间的指定格式
     *
     * @param format
     * @return
     */
    public static String getCurrDate(String format) {
        return date2String(new Date(), format);
    }

    /**
     * 获取当前的日期(yyyy-MM-dd)
     *
     * @return
     */
    public static String getCurrDate() {
        return DateUtils.date2String(new Date(), DateUtils.LONG_DATE);
    }

    /**
     * 获取当前的日期(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String getCurrDateTime() {
        return DateUtils.date2String(new Date(), DateUtils.COMMON_DATETIME);
    }

    /**
     * 获得当前星期
     *
     * @return
     */
    public static String getCurrWeek() {
        int i = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        return WEEK_NAMES[i - 1];
    }

    /**
     * 获得当前日期,如7月13号就返回13
     *
     * @return
     */
    public static int getCurrDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获得当前月份
     *
     * @return
     */
    public static int getCurrMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得当前年份
     *
     * @return
     */
    public static int getCurrYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的天，即yyyy-MM-dd中的dd
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的月份，1-12,即yyyy-MM-dd中的MM
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日期的年,即yyyy-MM-dd中的yyyy
     *
     * @param date Date
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 为日期增减时间,如: DateUtil.dateSub(Calendar.HOUR,"1999-9-9 15:16:36",3)
     *
     * @param dateStr
     * @param amount
     * @return
     */
    public static String dateChange(int dateKind, String dateStr, int amount) {
        Date date = string2Date(dateStr, COMMON_DATETIME);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateKind, amount);
        return date2String(calendar.getTime(), COMMON_DATETIME);
    }

    /**
     * 为日期增减时间,如: DateUtil.dateSub(Calendar.HOUR,"1999-9-9 15:16:36",3)
     *
     * @param dateStr
     * @param amount
     * @return 日期格式数据
     */
    public static Date dateChangeBackDate(int dateKind, String dateStr, int amount) {
        Date date = string2Date(dateStr, COMMON_DATETIME);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateKind, amount);
        return calendar.getTime();
    }

    /**
     * 为日期增减时间,如: DateUtil.dateSub(Calendar.HOUR,"1999-9-9 15:16:36",3)
     *
     * @param dateStr
     * @param amount
     * @return
     */
    public static Date dateChangeBackDate(int dateKind, Date dateStr, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateStr);
        calendar.add(dateKind, amount);
        return calendar.getTime();
    }

    /**
     * 为日期增减时间,如: DateUtil.dateSub(Calendar.HOUR,"1999-9-9 15:16:36",3)
     *
     * @param dateStr
     * @param amount
     * @return 字符串格式日期数据
     */
    public static String dateChangeBackStr(int dateKind, Date dateStr, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateStr);
        calendar.add(dateKind, amount);
        return date2String(calendar.getTime(), COMMON_DATETIME);
    }

    /**
     * 为日期增减时间,如: DateUtil.dateSub(Calendar.HOUR,"1999-9-9 15:16:36",3)
     *
     * @param dateStr
     * @param amount
     * @return 字符串格式日期数据
     */
    public static String dateChangeBackStr(int dateKind, String dateStr, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(string2Date(dateStr, COMMON_DATETIME));
        calendar.add(dateKind, amount);
        return date2String(calendar.getTime(), COMMON_DATETIME);
    }

    /**
     * 两个日期相减得到秒数
     *
     * @param smallTime
     * @param bigTime
     * @return 相减得到的秒数
     */
    public static long timeSub(String smallTime, String bigTime) {
        long first = string2Date(smallTime, COMMON_DATETIME).getTime();
        long second = string2Date(bigTime, COMMON_DATETIME).getTime();
        return (second - first) / 1000;
    }

    /**
     * 两个日期相减得到分钟数
     *
     * @param smallTime
     * @param bigTime
     * @return 相减得到分钟数
     */
    public static Long timeSubMin(String smallTime, String bigTime) {
        long first = string2Date(smallTime, COMMON_DATETIME).getTime();
        long second = string2Date(bigTime, COMMON_DATETIME).getTime();
        return (second - first) / 60000;
    }

    /**
     * 两个日期相减得到分钟数
     *
     * @param smallTime
     * @param bigTime
     * @return 相减得到分钟数
     */
    public static Long timeSubMin(Date smallTime, Date bigTime) {
        long first = smallTime.getTime();
        long second = bigTime.getTime();
        return (second - first) / 60000;
    }

    /**
     * 两个日期相减得到秒数
     *
     * @param smallTime
     * @param bigTime
     * @return 相减得到的秒数
     */
    public static long timeSub(Date smallTime, Date bigTime) {
        long first = smallTime.getTime();
        long second = bigTime.getTime();
        return (second - first) / 1000;
    }

    /**
     * 两个日期相减得到秒数
     *
     * @param smallTime
     * @param bigTime
     * @return 相减得到的秒数【有小数输出】
     */
    public static double timeSubDecimal(Date smallTime, Date bigTime) {
        long first = smallTime.getTime();
        long second = bigTime.getTime();
        return ((1.0 * (second - first)) / 1000);
    }


    /**
     * 获取某年某月的天数
     *
     * @param year  年份
     * @param month 月份[1-12]
     * @return 天数
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
     *
     * @param date1 Date
     * @param date2 Date
     * @return long
     */
    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }

    /**
     * 比较两个日期的年差
     *
     * @param before 格式：yyyy-MM-dd
     * @param after  格式：yyyy-MM-dd
     * @return
     */
    public static int yearDiff(String before, String after) {
        Date beforeDay = string2Date(before, LONG_DATE);
        Date afterDay = string2Date(after, LONG_DATE);
        return getYear(afterDay) - getYear(beforeDay);
    }

    /**
     * 当前日期减去after所指定的日期得到的年份
     *
     * @param after 格式：yyyy-MM-dd
     * @return
     */
    public static int yearDiffCurr(String after) {
        Date beforeDay = new Date();
        Date afterDay = string2Date(after, LONG_DATE);
        return getYear(beforeDay) - getYear(afterDay);
    }

    /**
     * 比较当前日期与指定日期的差
     *
     * @param before 格式：yyyy-MM-dd
     * @return
     */
    public static long dayDiffCurr(String before) {
        Date currDate = DateUtils.string2Date(getCurrDate(), LONG_DATE);
        Date beforeDate = string2Date(before, LONG_DATE);
        return (currDate.getTime() - beforeDate.getTime()) / 86400000;
    }

    /**
     * 比较当前时间与指定时间的差
     * 如果当前时间小于或者等于指定时间返回true,否则返回false
     *
     * @param currentTime 当前时间(格式 HH:mm:ss)
     * @param specifyTime 指定时间(格式 HH:mm:ss)
     * @return
     */
    public static boolean timeDiffCurr(String currentTime, String specifyTime) {
        long timeDiff = string2Date(currentTime, LONG_TIME).getTime() - string2Date(specifyTime, LONG_TIME).getTime();
        return timeDiff <= 0 ? true : false;
    }

    /**
     * 获得当前日期字符串，格式"yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static String getNow() {
        Calendar today = Calendar.getInstance();
        return date2String(today.getTime(), COMMON_DATETIME);
    }

    /**
     * 根据生日获取星座
     *
     * @param birth 格式：YYYY-mm-dd
     * @return
     */
    public static String getConstellation(String birth) {
        if (!isDate(birth)) {
            birth = "2000" + birth;
        }
        if (!isDate(birth)) {
            return "";
        }
        int month = Integer.parseInt(birth.substring(birth.indexOf("-") + 1, birth.lastIndexOf("-")));
        int day = Integer.parseInt(birth.substring(birth.lastIndexOf("-") + 1));
        String s = "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
        int[] arr = {20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};
        int start = month * 2 - (day < arr[month - 1] ? 2 : 0);
        return s.substring(start, start + 2) + "座";
    }

    /**
     * 判断日期是否有效,包括闰年的情况
     *
     * @param date 格式：YYYY-mm-dd
     * @return
     */
    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer("^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }

    /**
     * 取得指定日期过 months 月后的日期 (当 months 为负数表示指定月之前);
     *
     * @param date   日期 为null时表示当天
     * @param months 相加(相减)的月数
     */
    public static Date nextMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 取得指定日期过 day 天后的日期 (当 day 为负数表示指日期之前);
     *
     * @param date 日期 为null时表示当天
     * @param days 相加(相减)的天数
     */
    public static Date nextDay(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    /**
     * 取得距离今天 day 日的日期
     *
     * @param day
     * @param format
     * @return
     */
    public static String nextDay(int day, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, day);
        return date2String(cal.getTime(), format);
    }

    /**
     * 取得指定日期过 week 周后的日期 (当 week 为负数表示指定月之前)
     *
     * @param date 日期 为null时表示当天
     */
    public static Date nextWeek(Date date, int week) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.WEEK_OF_MONTH, week);
        return cal.getTime();
    }

    /**
     * 获取昨天的日期,返回的日期格式：yyyy-MM-dd
     *
     * @return
     */
    public static String befoDay() {
        return befoDay(DateUtils.LONG_DATE);
    }

    /**
     * 根据时间类型获取昨天的日期
     *
     * @param format
     * @return
     */
    public static String befoDay(String format) {
        return DateUtils.date2String(DateUtils.nextDay(new Date(), -1), format);
    }

    /**
     * 获取明天的日期
     *
     * @return
     */
    public static String afterDay() {
        return DateUtils.date2String(DateUtils.nextDay(new Date(), 1), DateUtils.LONG_DATE);
    }

    /**
     * 取得当前时间距离1900/1/1的天数
     *
     * @return
     */
    public static int getDayNum() {
        int daynum = 0;
        GregorianCalendar gd = new GregorianCalendar();
        Date dt = gd.getTime();
        GregorianCalendar gd1 = new GregorianCalendar(1900, 1, 1);
        Date dt1 = gd1.getTime();
        daynum = (int) ((dt.getTime() - dt1.getTime()) / (24 * 60 * 60 * 1000));
        return daynum;
    }

    /**
     * getDayNum的逆方法(用于处理Excel取出的日期格式数据等)
     *
     * @param day
     * @return
     */
    public static Date getDateByNum(int day) {
        GregorianCalendar gd = new GregorianCalendar(1900, 1, 1);
        Date date = gd.getTime();
        date = nextDay(date, day);
        return date;
    }

    /**
     * 获取本月第一天
     *
     * @param format
     * @return
     */
    public static String getFirstDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        return date2String(cal.getTime(), format);
    }

    /**
     * 获取本月最后一天
     *
     * @param format
     * @return
     */
    public static String getLastDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return date2String(cal.getTime(), format);
    }

    /**
     * 得到某日期的起始TimeStamp，比如：2011-08-17 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getBeginTimeOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        Calendar ret = Calendar.getInstance();
        cal.setTime(date);
        ret.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        return ret.getTime();
    }

    /**
     * 得到某日期的最后一刻TimeStamp，比如：2011-08-17 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getEndTimeOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        Calendar ret = Calendar.getInstance();
        cal.setTime(date);
        ret.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        return ret.getTime();
    }

    /**
     * 得到该日期所在周的周一的凌晨
     *
     * @param date
     * @return
     */
    public static Date getBeginTimeOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 得到该日期所在周的周末的最后一刻
     *
     * @param date
     * @return
     */
    public static Date getEndTimeOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_MONTH, 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 得到该日期所在月的第一天的凌晨
     *
     * @param date
     * @return
     */
    public static Date getBeginTimeOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 得到该日期所在月的最后一天的最后一刻
     *
     * @param date
     * @return
     */
    public static Date getEndTimeOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.SECOND, -1);
        return cal.getTime();
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static String getDateString() {
        SimpleDateFormat df = new SimpleDateFormat(COMMON_DATETIME_XML);
        return df.format(new Date());
    }

    public static String getDateTimestampString() {
        SimpleDateFormat df = new SimpleDateFormat(COMMON_DATETIME_SSS_XML);
        return df.format(new Date());
    }

    /**
     * 秒转12时2分2秒
     *
     * @param time
     * @return
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int i = 60;
        int j = 99;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) {
            return "00:00";
        } else {
            minute = time / 60;
            if (minute < i) {
                second = time % 60;
                timeStr = unitFormat(minute) + "分" + unitFormat(second) + "秒";
            } else {
                hour = minute / 60;
                if (hour > j) {
                    return "99:59:59";
                }
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + "时" + unitFormat(minute) + "分" + unitFormat(second) + "秒";
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + Integer.toString(i);
        } else {
            retStr = "" + i;
        }
        return retStr;
    }

    /**
     * 将两个时间拼装成03月27日 12:10-14:10这种格式数据
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static String handlerTime(String startTime, String endTime) {
        startTime = StringUtils.replaceEach(StringUtils.substring(startTime, 5, 16), new String[]{"-", " "}, new String[]{"月", "日 "});
        endTime = StringUtils.substring(endTime, 11, 16);
        return String.format("%s-%s", startTime, endTime);
    }

    /**
     * 设备失联判断
     *
     * @param lastTime 最后通讯时间
     * @return 设备失联返回 true 否则返回 fasle
     */
    public static boolean deviceMissing(Date lastTime) {
        if (dateChangeBackDate(Calendar.MINUTE, new Date(), -DEVICE_MISSING_TIME).getTime() < lastTime.getTime()) {
            return false;
        }
        return true;
    }

    /**
     * 设备失联判断
     *
     * @param lastTime 最后通讯时间
     * @return 设备失联返回 true 否则返回 fasle
     */
    public static boolean deviceMissing(String lastTime) {
        if (dateChangeBackDate(Calendar.MINUTE, new Date(), -DEVICE_MISSING_TIME).getTime() < string2Date(lastTime, COMMON_DATETIME).getTime()) {
            return false;
        }
        return true;
    }

    /**
     * 验证当前时间是否开放时间
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 是开放时间返回 1 否则返回 2
     */
    public static int validationOperStatus(String startTime, String endTime) {
        if(StringUtils.isBlank(startTime)){
            startTime = "00:00:00";
        }
        if(StringUtils.isBlank(endTime)){
            endTime = "23:59:59";
        }
        Date startDate = string2Date(startTime, LONG_TIME);
        Date endDate = string2Date(endTime, LONG_TIME);
        Date currentDate = string2Date(date2String(new Date(), DateUtils.LONG_TIME), DateUtils.LONG_TIME);
        if (currentDate.getTime() >= startDate.getTime() && currentDate.getTime() <= endDate.getTime()) {
            // 是共享时间
            return 1;
        }
        return 2;
    }

    public static void main(String[] args) {
        String startTime = "";
        String endTime = "";
        System.out.println(validationOperStatus(startTime, endTime));
    }
}