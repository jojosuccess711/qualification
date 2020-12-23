package com.bdjbd.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
  * @className DateUtil
  * @description 日期时间工具类
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public final class DateUtil {

    /**
     * 中国式星期
     */
    public enum ChineseWeek {
        星期一,
        星期二,
        星期三,
        星期四,
        星期五,
        星期六,
        星期日;
    }

    /**
     * 字符串 (" ")
     */
    public static final String STR_SPACE = " ";

    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    public static final String DATE_FORMAT_YYYY_MM_DD_ = "yyyy-MM-dd ";

    public static final String DATE_FORMAT_HH_MM_SS = "HH:mm:ss";

    public static final String DATE_FORMAT_HH_MM = "HH:mm";

    public static final String DATE_FORMAT_MM_DD_HH_MM = "MM-dd HH:mm";

    /**
     * 日期格式转化
     * @param sourcePattern
     * @param targetPattern
     * @param date
     * @return
     */
    public static String dateFormat(String sourcePattern, String targetPattern, String date){
        try{
            Date sourceDate = dateParse(sourcePattern, date);
            return dateFormat(targetPattern, sourceDate);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 时间格式转化为字符串
     */
    public static String dateFormat(String pattern, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date == null ? getWhenDate() : date);
    }

    /**
     * 字符串转化为时间
     */
    public static Date dateParse(String pattern, String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 字符串转化为时间
     * yyyy-MM-dd
     */
    public static Date dateParseYMD(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return getWhenDate();
        }
    }

    /**
     * 字符串转化为时间
     * HH:mm
     */
    public static Date dateParseHM(String date, Long addMinutes) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
        try {
            return sdf.parse(dateFormat(DATE_FORMAT_YYYY_MM_DD, getDateAddMinute(null, addMinutes)) + " " + date + ":00");
        } catch (ParseException e) {
            return getWhenDate();
        }
    }

    /**
     * 字符串转化为时间
     * HH:mm:ss
     */
    public static Date dateParseHMS(String date, Long addMinutes) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
        try {
            return sdf.parse(dateFormat(DATE_FORMAT_YYYY_MM_DD, getDateAddMinute(null, addMinutes)) + " " + date);
        } catch (ParseException e) {
            return getWhenDate();
        }
    }

    /**
     * 判断 指定日期 是否 是当天
     *
     * @param date
     * @return 如果是当天 则返回 true 否则放回 false
     */
    public static Boolean isToday(Date date) {
        String dateString = dateFormat(DATE_FORMAT_YYYY_MM_DD, date);
        String todayString = dateFormat(DATE_FORMAT_YYYY_MM_DD, new Date());
        if (todayString.equals(dateString)) {
            return true;
        }
        return false;
    }

    /**
     * 获取今日日期  yyyy-MM-dd (忽略时间)
     *
     * @return Date
     */
    public static Date getTodayFirst() {
        String dateString = dateFormat(DATE_FORMAT_YYYY_MM_DD, new Date());
        return dateParse(DATE_FORMAT_YYYY_MM_DD, dateString);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getWhenDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 判断 指定日期是否在某个固定时间区间段内
     *
     * @param indexDate       指定日期
     * @param beginDate       开始时间
     * @param endDate         结束时间
     * @param isContainsBegin 是否包含开始时间
     * @param isContainsEnd   是否包含结束时间
     * @return 在指定时间段内返回 true, 否则返回 false
     */
    public static Boolean isInDateInterval(Date indexDate, Date beginDate, Date endDate, Boolean isContainsBegin, Boolean isContainsEnd) {
        indexDate = indexDate == null ? getWhenDate() : indexDate;
        if (indexDate.after(beginDate) && indexDate.before(endDate)) {
            return true;
        }
        if (isContainsBegin && indexDate.equals(beginDate)) {
            return true;
        }
        if (isContainsEnd && indexDate.equals(endDate)) {
            return true;
        }
        return false;
    }

    /**
     * 判断指定时间 是否在某个固定时间段内 (忽略日期)【HH:mm:ss】<br/>
     * 指定时间为空 默认为当前时间
     *
     * @param startTime 开始时间【09:30:00】
     * @param endTime   结束时间【19:30:00】
     * @return 在指定时间段内 返回 ture ,否则返回 false
     */
    public static Boolean isForATime(String startTime, String endTime, Date date) {
        date = (date == null) ? getWhenDate() : date;

        if (startTime == null && endTime == null) {
            return false;
        }
        String currYMD = DateUtil.dateFormat(DATE_FORMAT_YYYY_MM_DD_, date);
        Date startDate = DateUtil.dateParse(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, currYMD + startTime);
        Date endDate = DateUtil.dateParse(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, currYMD + endTime);

        if (startTime == null || endTime == null) {
            if (startTime == null) {
                if (date.before(endDate) || date.equals(endDate)) {
                    return true;
                }
            } else {
                if (date.after(startDate) || date.equals(startDate)) {
                    return true;
                }
            }
        } else {
            if ((date.after(startDate) && date.before(endDate))
                    || date.equals(endDate) || date.equals(startDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前时间 是否在指定时间段内 (忽略日期)【HH:mm:ss】
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @param indexDate 指定日期
     * @return 在指定时间段内 返回 ture ,否则返回 false (忽略日期)
     */
    public static Boolean isForATime(Date beginDate, Date endDate, Date indexDate) {
        return isForATime(formatHMS(beginDate), formatHMS(endDate), indexDate);
    }

    /**
     * 是否在指定时间段内
     *
     * @param indexDate     指定时间
     * @param beginDate     开始时间
     * @param endDate       结束时间
     * @param isContainsYTD 是否包含日期(false 表示忽略日期部分)
     * @return 在指定时间段内返回 true, 否则返回 false
     */
    public static Boolean isInTimeInterval(Date indexDate, Date beginDate, Date endDate, Boolean isContainsYTD) {
        Boolean isInDateInterval = isInDateInterval(indexDate, beginDate, endDate, true, true);
        if (isContainsYTD) {
            return isInDateInterval;
        } else {
            return isInDateInterval && isForATime(beginDate, endDate, indexDate);
        }
    }

    /**
     * 时间格式转化为字符串
     * <p>
     * yyyy-MM-dd
     */
    public static String formatYMD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        return sdf.format(date == null ? getWhenDate() : date);
    }

    /**
     * 时间格式转化为字符串
     * <p>
     * yyyy-MM-dd
     */
    public static String formatYMDNoUnderline(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
        return sdf.format(date == null ? getWhenDate() : date);
    }

    /**
     * 时间格式转化为字符串
     * <p>
     * yyyyMMddHHmmss
     */
    public static String formatY4M2D2H2M2S2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSS);
        return sdf.format(date == null ? getWhenDate() : date);
    }

    /**
     * 时间格式转化为字符串
     * <p>
     * yyyy-MM-dd HH:mm:ss
     */
    public static String formatYMDHMS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
        return sdf.format(date == null ? getWhenDate() : date);
    }

    /**
     * 时间格式转化为字符串
     * <p>
     * yyyy-MM-dd HH:mm
     */
    public static String formatYMDHM(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM);
        return sdf.format(date == null ? getWhenDate() : date);
    }

    /**
     * 获取 日期 时间
     *
     * @param date 日期
     * @return HH:mm:ss
     */
    public static String formatHMS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_HH_MM_SS);
        return sdf.format(date == null ? getWhenDate() : date);
    }

    /**
     * 获取 日期 时间
     *
     * @param date 日期
     * @return
     */
    public static String formatHM(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_HH_MM);
        return sdf.format(date == null ? getWhenDate() : date);
    }

    /**
     * 获取 日期 时间
     *
     * @param date 日期
     * @return
     */
    public static String formatMDHM(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_MM_DD_HH_MM);
        return sdf.format(date == null ? getWhenDate() : date);
    }

    /**
     * 获取指定日期 最小时间 00:00:00
     *
     * @param date 指定日期
     * @return 指定日期 最小时间 00:00:00
     */
    public static Date getDateMin(Date date) {
        Calendar calendar = org.apache.commons.lang3.time.DateUtils.toCalendar(date == null ? getWhenDate() : date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        return calendar.getTime();
    }

    /**
     * 获取指定日期 最大时间 23:59:59
     *
     * @param date 指定日期
     * @return 指定日期 最大时间 23:59:59
     */
    public static Date getDateMax(Date date) {
        Calendar calendar = org.apache.commons.lang3.time.DateUtils.toCalendar(date == null ? getWhenDate() : date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        return calendar.getTime();
    }

    /**
     * 获取当前日期 + 指定时分秒
     *
     * @param formatHHmmss 时分秒
     * @return 获取当前日期 + 指定时分秒
     */
    public static Date getDate(String formatHHmmss) {
        String dateFormat = formatYMD(getWhenDate()) + STR_SPACE + formatHHmmss;
        return dateParse(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, dateFormat);
    }

    /**
     * 获取指定日期 + 指定时分秒
     *
     * @param date         指定日期 (为空取当前日期)
     * @param formatHHmmss 时分秒
     * @return 获取当前日期 + 指定时分秒
     */
    public static Date getDate(Date date, String formatHHmmss) {
        String dateFormat = formatYMD(date == null ? getWhenDate() : date) + STR_SPACE + formatHHmmss;
        return dateParse(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, dateFormat);
    }

    /**
     * 获取 日期属性 小时
     *
     * @param date 指定日期
     * @return
     */
    public static Integer getDateHour(Date date) {
        Calendar calendar = org.apache.commons.lang3.time.DateUtils.toCalendar(date == null ? getWhenDate() : date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取 日期属性 分钟
     *
     * @param date 指定日期
     * @return
     */
    public static Integer getDateMinute(Date date) {
        Calendar calendar = org.apache.commons.lang3.time.DateUtils.toCalendar(date == null ? getWhenDate() : date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取 日期属性 秒
     *
     * @param date 指定日期
     * @return
     */
    public static Integer getDateSecond(Date date) {
        Calendar calendar = org.apache.commons.lang3.time.DateUtils.toCalendar(date == null ? getWhenDate() : date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 给指定日期 设置 指定日期的时分秒
     *
     * @param indexDate  指定日期
     * @param dateHHMMSS 指定日期时分秒
     * @return 增加指定时分秒后的指定日期
     */
    public static Date setHHMMSS(Date indexDate, Date dateHHMMSS) {
        Calendar indexCalendar = org.apache.commons.lang3.time.DateUtils.toCalendar(indexDate == null ? getWhenDate() : indexDate);
        Calendar dateCalendar = org.apache.commons.lang3.time.DateUtils.toCalendar(dateHHMMSS);
        indexCalendar.set(Calendar.HOUR_OF_DAY, dateCalendar.get(Calendar.HOUR_OF_DAY));
        indexCalendar.set(Calendar.MINUTE, dateCalendar.get(Calendar.MINUTE));
        indexCalendar.set(Calendar.SECOND, dateCalendar.get(Calendar.SECOND));
        return indexCalendar.getTime();
    }

    /**
     * 获取当前月第一天 零点零分零秒
     *
     * @return 当前月第一天 零点零分零秒
     */
    public static Date getDateTheFirstOfTheMonth() {
        Calendar calendar = org.apache.commons.lang3.time.DateUtils.toCalendar(getWhenDate());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        return calendar.getTime();
    }

    /**
     * 获取当前月当前日
     *
     * @param date    指定日期
     * @param addDays
     * @return 当前日
     */
    public static Integer getCurrDayOfMonth(Date date, Integer addDays) {
        date = date == null ? (addDays == null ? getWhenDate() : org.apache.commons.lang3.time.DateUtils.addDays(getWhenDate(), addDays)) : (addDays == null ? date : org.apache.commons.lang3.time.DateUtils.addDays(date, addDays));
        Calendar calendar = org.apache.commons.lang3.time.DateUtils.toCalendar(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取月最大日
     *
     * @param date    指定日期
     * @param addDays
     * @return 月最大日
     */
    public static Integer getMaxDayOfMonth(Date date, Integer addDays) {
        date = date == null ? (addDays == null ? getWhenDate() : org.apache.commons.lang3.time.DateUtils.addDays(getWhenDate(), addDays)) : (addDays == null ? date : org.apache.commons.lang3.time.DateUtils.addDays(date, addDays));
        Calendar calendar = org.apache.commons.lang3.time.DateUtils.toCalendar(date);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取月剩余天数
     *
     * @param date    指定日期
     * @param addDays
     * @return 月剩余天数
     */
    public static Integer getRemainingDaysOfMonth(Date date, Integer addDays) {
        date = date == null ? (addDays == null ? getWhenDate() : org.apache.commons.lang3.time.DateUtils.addDays(getWhenDate(), addDays)) : (addDays == null ? date : org.apache.commons.lang3.time.DateUtils.addDays(date, addDays));
        Calendar calendar = org.apache.commons.lang3.time.DateUtils.toCalendar(date);
        Integer todayDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        Integer maxDayOfMonth = calendar.getActualMaximum(Calendar.DATE);
        return maxDayOfMonth - todayDayOfMonth + 1;
    }

    /**
     * 是否月初第一天
     *
     * @param date 日期
     * @return 月初第一天返回 true, 否则返回false
     */
    public static Boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = org.apache.commons.lang3.time.DateUtils.toCalendar(date == null ? getWhenDate() : date);
        if (calendar.get(Calendar.DAY_OF_MONTH) == calendar.getActualMinimum(Calendar.DATE)) {
            return true;
        }
        return false;
    }

    /**
     * 是否月底最后一天
     *
     * @param date 日期
     * @return 月底最后一天返回 true, 否则返回false
     */
    public static Boolean isLastDayOfMonth(Date date) {
        Calendar calendar = org.apache.commons.lang3.time.DateUtils.toCalendar(date == null ? getWhenDate() : date);
        if (calendar.get(Calendar.DAY_OF_MONTH) == calendar.getActualMaximum(Calendar.DATE)) {
            return true;
        }
        return false;
    }

    /**
     * 给指定时间增加 指定 分钟
     *
     * @param date      日期
     * @param addMinute 指定分钟
     * @return 增加指定时间后的日期
     */
    public static Date getDateAddMinute(Date date, Long addMinute) {
        date = date == null ? getWhenDate() : date;
        long current = date.getTime();
        long ln = addMinute * 60 * 1000;
        long ot = current + ln;
        return new Date(ot);
    }

    /**
     * 获取中国式 星期
     *
     * @param date 日期
     * @return 中国式星期
     */
    public static ChineseWeek getChineseWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(getDateAddMinute(date == null ? getWhenDate() : date, 0L));
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        if (w < 0) {
            w = 6;
        }
        return ChineseWeek.values()[w];
    }

    /**
     * 获取时间差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 时间差 endDate - startDate
     */
    public static Long getDifftimeMinute(Date startDate, Date endDate) {
        startDate = startDate == null ? getWhenDate() : startDate;
        endDate = endDate == null ? getWhenDate() : endDate;
        Long differentDate = Math.abs(endDate.getTime() - startDate.getTime());
        return differentDate / 1000 / 60;
    }

    /**
     * 获取时间差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 时间差 endDate - startDate
     */
    public static Long getDifftimeSecond(Date startDate, Date endDate) {
        startDate = startDate == null ? getWhenDate() : startDate;
        endDate = endDate == null ? getWhenDate() : endDate;
        Long differentDate = endDate.getTime() - startDate.getTime();
        return differentDate / 1000;
    }

    /**
     * 获取时间差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 时间差 endDate - startDate
     */
    public static Long getDifftimeDay(Date startDate, Date endDate) {
        startDate = getDateMin(startDate);
        endDate = getDateMin(endDate);
        Long differentDate = Math.abs(endDate.getTime() - startDate.getTime());
        return differentDate / 1000 / 60 / 60 / 24;
    }

    /**
     * 获取两个日志之间的所有日志
     *
     * @param start
     * @param end
     * @return 返回日期列表
     */
    public static List<Date> getDates(Date start, Date end) {
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(start);
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(end);

        List<Date> result = new ArrayList<Date>();

        while (cStart.before(cEnd)) {
            result.add(cStart.getTime());
            cStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        result.add(cStart.getTime());
        return result;
    }
}
