package com.easy.common.util.lang;

import org.dromara.hutool.core.date.DateTime;
import org.dromara.hutool.core.date.DateUtil;
import org.dromara.hutool.core.date.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.dromara.hutool.core.date.DateFormatPool.*;


/**
 * 时间工具
 * </p>
 *
 * @author Matt
 */
public class DateUtils extends DateUtil {

    public static final String DATE_PATH = "yyyy/MM/dd";

    public static final String[] WEEK = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};

    private DateUtils() {
        throw new IllegalAccessError("DateUtils.class");
    }

    public static String nowDateTime() {
        return now(NORM_DATETIME_PATTERN);
    }

    public static String now(String pattern) {
        return LocalDateTime.now(ZoneId.of("UTC+8")).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime nowLocalDateTime() {
        return LocalDateTime.now(ZoneId.of("UTC+8"));
    }

    public static Date nowDate() {
        ZonedDateTime zdt = LocalDateTime.now().atZone(ZoneId.of("UTC+8"));
        return Date.from(zdt.toInstant());
    }

    public static String get(String datePattern) {
        return format(LocalDateTime.now(ZoneId.of("UTC+8")), datePattern);
    }

    public static String getDate() {
        return format(LocalDateTime.now(ZoneId.of("UTC+8")), NORM_DATE_PATTERN);
    }

    public static Date toDate(String date) {
        LocalDateTime parse = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ZonedDateTime zdt = parse.atZone(ZoneId.of("UTC+8"));
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取当前年月日
     *
     * @return 日期路径 即年/月/日 如2018/08/08
     */
    public static String datePath() {
        return format(new Date(), DATE_PATH);
    }

    /**
     * 获取当前 时 分 秒 毫秒
     *
     * @return 日期数字  如154320846
     */
    public static String timeNum() {
        return format(new Date(), PURE_TIME_FORMATTER);
    }

    /**
     * 将一种日期时间格式转化为另外一种格式
     *
     * @param date         String 日期时间
     * @param sourceFormat 原来格式
     * @param format       转化格式
     * @return String日期
     */
    public static String formatStrToStr(String date, String sourceFormat, String format) {
        DateTime parse = DateUtils.parse(date, sourceFormat);
        return format(parse, format);
    }

    /**
     * 传入两个时间范围，返回这两个时间范围内的所有日期，并保存在一个集合中
     */
    public static List<String> findEveryDay(String beginTime, String endTime, String formatter) {
        // 1.创建一个放所有日期的集合
        List<String> dates = new ArrayList<>();
        // 3.将传入的时间解析成Date类型,相当于格式化
        Date dBegin = parse(beginTime, formatter);
        Date dEnd = parse(endTime, formatter);
        // 4.将格式化后的第一天添加进集合
        dates.add(format(dBegin, formatter));
        // 5.使用本地的时区和区域获取日历
        Calendar calBegin = Calendar.getInstance();
        // 6.传入起始时间将此日历设置为起始日历
        calBegin.setTime(dBegin);
        // 8.判断结束日期是否在起始日历的日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 9.根据日历的规则:月份中的每一天，为起始日历加一天
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            // 10.得到的每一天就添加进集合
            dates.add(format(calBegin.getTime(), formatter));
            // 11.如果当前的起始日历超过结束日期后,就结束循环
        }
        return dates;
    }

    /**
     * 日期转星期
     */
    public static String dateToWeek(String date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        // 获得一个日历
        Calendar cal = Calendar.getInstance();
        Date datet;
        try {
            datet = f.parse(date);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 根据星期获取下标 inex
     * 星期一为 0  以此类推
     */
    public static int getWeekIndex(String week) {
        if (StringUtils.isBlank(week)) {
            throw new RuntimeException("时间不能为空");
        }
        for (int i = 0; i < WEEK.length; i++) {
            if (WEEK[i].equals(week)) {
                return i;
            }
        }
        throw new RuntimeException("数据错误");
    }

    /**
     * 计算开始和借结束时间差值
     *
     * @param st  开始时间
     * @param end 结束时间
     * @return 秒数 long
     * @throws ParseException
     */
    public static long getTimeDifference(String st, String end) throws ParseException {
        if (StringUtils.isBlank(st) || StringUtils.isBlank(end)) {
            return 0;
        }
        if (st.equals(end)) {
            return 0;
        }
        SimpleDateFormat format = new SimpleDateFormat(NORM_DATETIME_PATTERN);
        Date stDate = format.parse(st);
        Date endDate = format.parse(end);
        return getTimeDifference(stDate, endDate);
    }

    /**
     * 计算开始和借结束时间差值
     *
     * @param stDate  开始时间
     * @param endDate 结束时间
     * @return 秒数 long
     * @throws ParseException
     */
    public static long getTimeDifference(Date stDate, Date endDate) throws ParseException {
        if (stDate.getTime() > endDate.getTime()) {
            return 0;
        }
        long between = endDate.getTime() - stDate.getTime();
        return (between / 1000);
    }

    /**
     * 格式化成日期
     *
     * @param dateStr 标准日期格式 FastDateFormat：yyyy-MM-dd
     * @return LocalDate
     */
    public static LocalDate parseLocalDate(CharSequence dateStr) {
        return TimeUtil.parseDate(dateStr, NORM_DATE_FORMATTER);
    }

    /**
     * 根据特定格式格式化日期
     *
     * @param localDate 被格式化的日期
     * @return 标准日期格式 FastDateFormat：yyyy-MM-dd
     */
    public static String formatDate(LocalDate localDate, String formatter) {
        return TimeUtil.format(localDate, formatter);
    }


    /**
     * 根据特定格式格式化日期
     */
    public static String formatTo(String date, String originalFormatter, String targetFormatter) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(originalFormatter);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new SimpleDateFormat(targetFormatter).format(parse);
    }

    /**
     * 根据给定的日期、类型和天数，计算新的日期。
     *
     * @param date 输入日期，格式为 "yyyy-MM-dd"
     * @param type 类型：0表示减日期，1表示加日期
     * @param days 加减的天数
     * @return 计算后的日期（LocalDate对象）
     * @throws DateTimeParseException 如果输入日期格式不正确
     */
    public static LocalDate addOrSubtractDays(String date, int type, int days) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(date, formatter);
        return switch (type) {
            case 0 ->
                // 向前计算（减少天数）
                    startDate.minusDays(days);
            case 1 ->
                // 向后计算（增加天数）
                    startDate.plusDays(days);
            default ->
                    throw new IllegalArgumentException("Invalid type specified. Expected 0 (for adding days) or 1 (for subtracting days).");
        };
    }

    /**
     * 根据给定的日期、类型和天数，计算新的日期。
     *
     * @param date 输入日期，格式为 "yyyy-MM-dd"
     * @param type 类型：0表示向后计算，1表示向前计算
     * @param days 加减的天数
     * @return 计算后的日期（String对象）
     * @throws DateTimeParseException 如果输入日期格式不正确
     */
    public static String addOrSubtractDaysStr(String date, int type, int days, String formatter) throws DateTimeParseException {
        LocalDate startDate = parseLocalDate(date);
        return switch (type) {
            case 0 ->
                // 向前计算（减少天数）
                    formatDate(startDate.minusDays(days), formatter);
            case 1 ->
                // 向后计算（增加天数）
                    formatDate(startDate.plusDays(days), formatter);
            default ->
                    throw new IllegalArgumentException("Invalid type specified. Expected 0 (for adding days) or 1 (for subtracting days).");
        };
    }

    /**
     * 根据给定的日期、类型和天数，计算新的日期。
     *
     * @param date   输入日期，格式为 "yyyy-MM-dd"
     * @param type   类型：0表示向后计算，1表示向前计算
     * @param months 加减的月数
     * @return 计算后的日期（String对象）
     * @throws DateTimeParseException 如果输入日期格式不正确
     */
    public static String addOrSubtractMonthsStr(String date, int type, int months, String formatter) throws DateTimeParseException {

        LocalDateTime startDate = TimeUtil.parse(date, formatter);
        return switch (type) {
            // 向前计算（减少天数）
            case 0 -> TimeUtil.format(startDate.minusMonths(months), formatter);
            // 向后计算（增加天数）
            case 1 -> TimeUtil.format(startDate.plusMonths(months), formatter);
            default ->
                    throw new IllegalArgumentException("Invalid type specified. Expected 0 (for adding days) or 1 (for subtracting days).");
        };
    }

    /**
     * 根据给定的日期时间字符串和小时数，计算并返回过去或未来指定小时数后的日期时间字符串。
     *
     * @param dateTimeStr 原始日期时间字符串，格式为 "yyyy-MM-dd HH:mm:ss"
     * @param hoursOffset 小时偏移量，正数表示未来，负数表示过去
     * @return 计算后的新日期时间字符串，格式仍为 "yyyy-MM-dd HH:mm:ss"
     */
    public static String calculateByHours(String dateTimeStr, int hoursOffset) {
        LocalDateTime dateTime = parse(dateTimeStr).toLocalDateTime();
        LocalDateTime newDateTime = dateTime.plusHours(hoursOffset);
        return formatLocalDateTime(newDateTime);
    }

    /**
     * 根据给定的日期时间字符串和天数，计算并返回过去或未来指定天数后的日期时间字符串。
     *
     * @param dateTimeStr 原始日期时间字符串，格式为 "yyyy-MM-dd HH:mm:ss"
     * @param daysOffset  天数偏移量，正数表示未来，负数表示过去
     * @return 计算后的新日期时间字符串，格式仍为 "yyyy-MM-dd HH:mm:ss"
     */
    public static String calculateByDays(String dateTimeStr, int daysOffset) {
        LocalDateTime dateTime = parse(dateTimeStr).toLocalDateTime();
        LocalDateTime newDateTime = dateTime.plusDays(daysOffset);
        return formatLocalDateTime(newDateTime);
    }

    /**
     * 根据给定的日期时间字符串和月数，计算并返回过去或未来指定月数后的日期时间字符串。
     *
     * @param dateTimeStr  原始日期时间字符串，格式为 "yyyy-MM-dd HH:mm:ss"
     * @param monthsOffset 月数偏移量，正数表示未来，负数表示过去
     * @return 计算后的新日期时间字符串，格式仍为 "yyyy-MM-dd HH:mm:ss"
     */
    public static String calculateByMonths(String dateTimeStr, int monthsOffset) {
        LocalDateTime dateTime = parse(dateTimeStr).toLocalDateTime();
        LocalDateTime newDateTime = dateTime.plusMonths(monthsOffset);
        return formatLocalDateTime(newDateTime);
    }

    /**
     * 将 "yyyy-MM-dd HH:mm:ss" 格式的字符串时间转换为指定时区的时间戳（毫秒）
     *
     * @param timeStr 输入的日期时间字符串，格式为 "yyyy-MM-dd HH:mm:ss"
     * @return 给定时间在指定时区下的时间戳（毫秒）
     * @throws IllegalArgumentException 如果输入字符串无法解析或时区 ID 无效
     */
    public static long convertStringToTimestamp(String timeStr) throws IllegalArgumentException {
        // 校验输入参数
        if (timeStr == null) {
            throw new IllegalArgumentException("Both timeStr must not be null.");
        }
        // 定义日期时间格式器，与输入字符串的格式匹配
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            // 解析字符串为 LocalDateTime 对象
            LocalDateTime dateTime = LocalDateTime.parse(timeStr, formatter);

            // 将 LocalDateTime 转换为 ZonedDateTime，指定时区
            ZoneId zoneId = ZoneId.of("UTC+8");
            ZonedDateTime zonedDateTime = dateTime.atZone(zoneId);

            // 从 ZonedDateTime 获取对应的 Instant
            Instant instant = zonedDateTime.toInstant();

            // 从 Instant 中获取时间戳（毫秒）
            return instant.getEpochSecond();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid timeStr or timeZoneId.", e);
        }
    }

    public static String rfcTime() {

        return rfcTime(LocalDateTime.now());
    }

    public static String rfcTime(LocalDateTime localDateTime) {

        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ISO8601_WITH_XXX_OFFSET_PATTERN);
        return zonedDateTime.format(formatter);
    }
}