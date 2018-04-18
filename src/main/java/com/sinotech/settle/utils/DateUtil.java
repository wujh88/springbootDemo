package com.sinotech.settle.utils;

import com.sinotech.settle.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间日期工具类
 * Haakon 2017-4-14
 * 
 */
public class DateUtil {

    // 默认日期格式
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";

    // 默认日期时间格式
    public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //毫秒级别的日期时间格式
    public static final String DATETIME_MIL_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    //默认时间格式
    public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";

    // 日期格式化
    private static DateFormat dateFormat = null;

    // 时间格式化
    private static DateFormat dateTimeFormat = null;
    
    private static DateFormat dateTimeMilFormat = null;

    private static DateFormat timeFormat = null;

    private static Calendar gregorianCalendar = null;

    static {
        dateFormat = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
        dateTimeFormat = new SimpleDateFormat(DATETIME_DEFAULT_FORMAT);
        dateTimeMilFormat = new SimpleDateFormat(DATETIME_MIL_FORMAT);
        timeFormat = new SimpleDateFormat(TIME_DEFAULT_FORMAT);
        gregorianCalendar = new GregorianCalendar();
    }
    
    /**
     * 日期串进行格式转换，yyyyMMddHHmmss转为yyyy-MM-dd HH:mm:ss
     * @param formatDate
     * @return
     */
    public static String format2other(String formatDate) {
//    	String formatDate = "20170809161756";
		String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
		return formatDate.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
	}

    /**
     * 根据格式化字符串，将日期格式化
     * 
     * @param date
     * @param format
     * @return
     */
    public static Date formatDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 日期格式化yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static String getDateFormat(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 日期时间格式化yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String getDateTimeFormat(Date date) {
        return dateTimeFormat.format(date);
    }

    /**
     * 日期时间格式化yyyy-MM-dd HH:mm:ss.SSS
     * 
     * @param date
     * @return
     */
    public static String getDateTimeMilFormat(Date date) {
    	return dateTimeMilFormat.format(date);
    }
    
    /**
     * 时间格式化
     * 
     * @param date
     * @return HH:mm:ss
     */
    public static String getTimeFormat(Date date) {
        return timeFormat.format(date);
    }

    /**
     * 日期格式化
     * 
     * @param date
     * @param formatStr 格式化类型
     * @return
     */
    public static String getDateFormat(Date date, String formatStr) {
        if (StringUtils.isNotBlank(formatStr)) {
            return new SimpleDateFormat(formatStr).format(date);
        }
        return null;
    }

    /**
     * 日期格式化
     * 
     * @param date
     * @return
     */
    public static Date getDateFormat(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间格式化
     * 
     * @param date
     * @return
     */
    public static Date getDateTimeFormat(String date) {
        try {
            return dateTimeFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @return
     */
    public static Date getNowDate() {
        return DateUtil.getDateFormat(dateFormat.format(new Date()));
    }

    /**
     * 获取当前日期星期一日期
     * 
     * @return date
     */
    public static Date getFirstDayOfWeek() {
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek()); // Monday
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前日期星期日日期
     * 
     * @return date
     */
    public static Date getLastDayOfWeek() {
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek() + 6); // Monday
        return gregorianCalendar.getTime();
    }

    /**
     * 获取指定日期星期一日期
     * 
     * @param
     * @return date
     */
    public static Date getFirstDayOfWeek(Date date) {
        if (date == null) {
            return null;
        }
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek()); // Monday
        return gregorianCalendar.getTime();
    }

    /**
     * 获取指定日期星期一日期
     * 
     * @param date 指定日期
     * @return date
     */
    public static Date getLastDayOfWeek(Date date) {
        if (date == null) {
            return null;
        }
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek() + 6); // Monday
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前月的第一天
     * 
     * @return date
     */
    public static Date getFirstDayOfMonth() {
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前月的最后一天
     * 
     * @return
     */
    public static Date getLastDayOfMonth() {
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.add(Calendar.MONTH, 1);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取指定月的第一天
     * 
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取指定月的最后一天
     * 
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.add(Calendar.MONTH, 1);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期前一天
     * 
     * @param date
     * @return
     */
    public static Date getDayBefore(Date date) {
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day - 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期后一天
     * 
     * @param date
     * @return
     */
    public static Date getDayAfter(Date date) {
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day + 1);
        return gregorianCalendar.getTime();
    }
    
    /**
     * 获取当天后几天的日期
     * 例如，今天是2017-4-14，2天后即为2017-4-16
     * @param count 天数
     * @return
     */
    public static Date getDayAfter(int count) {
    	gregorianCalendar.setTime(new Date());
    	int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day + count);
    	return gregorianCalendar.getTime();
	}

    /**
     * 获取当前年
     * 
     * @return
     */
    public static int getNowYear() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     * 
     * @return
     */
    public static int getNowMonth() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当月天数
     * 
     * @return
     */
    public static int getNowMonthDay() {
        Calendar d = Calendar.getInstance();
        return d.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取时间段的每一天
     * 
     * @param startDate 开始日期
     * @param endDate 结算日期
     * @return 日期列表
     */
    public static List<Date> getEveryDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        // 格式化日期(yy-MM-dd)
        startDate = DateUtil.getDateFormat(DateUtil.getDateFormat(startDate));
        endDate = DateUtil.getDateFormat(DateUtil.getDateFormat(endDate));
        List<Date> dates = new ArrayList<Date>();
        gregorianCalendar.setTime(startDate);
        dates.add(gregorianCalendar.getTime());
        while (gregorianCalendar.getTime().compareTo(endDate) < 0) {
            // 加1天
            gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
            dates.add(gregorianCalendar.getTime());
        }
        return dates;
    }

    /**
     * 获取提前多少个月后的时间
     * 
     * @param monty 月数
     * @return
     */
    public static Date getFirstMonth(int monty) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -monty);
        return c.getTime();
    }
    
    /**
     * 将时间戳转化为时间串
     * long --> "yyyy-MM-dd HH:mm:ss.SSS"
     * @param timestamp
     * @return
     */
    public static String getDateStr(Long timestamp) {
    	return new SimpleDateFormat(DATETIME_MIL_FORMAT).format(timestamp);
	}

    /**
     * 将时间戳转化为时间串
     * long --> "yyyy-MM-dd HH:mm:ss"
     * @param timestamp
     * @return
     */
    public static String getDateTimeStr(Long timestamp) {
        return new SimpleDateFormat(DATETIME_DEFAULT_FORMAT).format(timestamp);
    }
    
    /**
     * 比较两个日期的大小
     * @param time
     * @param time1
     * @return   
     * @author ZTF
     * @throws ServiceException 
     * @date 2017年7月20日 上午10:20:43
     */
    public static boolean compareDate(String time,Date time1) throws ServiceException {
    	
    	 DateFormat df = new SimpleDateFormat("yyyyMMdd");
        
             Date dt1;
             Date dt2;
			try {
				dt1 = df.parse(time);
				dt2 = df.parse(df.format(time1));
			    if (dt1.getTime() >= dt2.getTime()) {
	                return false;
	             }
			} catch (ParseException e) {
				e.printStackTrace();
				throw new ServiceException("时间格式不正确");
			}
         
        
		return true;
    }

    /**
     * 判断字符是否是年份
     * @param date
     * @return
     */
    public static boolean isDateYear(String date){
        DateFormat df = new SimpleDateFormat("yyyy");
        if(date.length()!=4){
            return false;
        }
        try{
            df.parse(date);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 判断字符是否是月份
     * @param date
     * @return
     */
    public static boolean isDateMonth(String date){
        DateFormat df = new SimpleDateFormat("MM");
        if(date.length()>2){
            return false;
        }
        try{
            df.parse(date);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static void main(String[] args) {
		System.out.println(DateUtil.getDateFormat(new Date(),"yyMM:HHmm").codePointCount(0, 4));
        System.out.println(getDateTimeStr(0L));
	}
}
