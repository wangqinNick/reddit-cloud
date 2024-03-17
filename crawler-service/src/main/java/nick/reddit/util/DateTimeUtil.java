package nick.reddit.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;


public class DateTimeUtil {
    /**
     * 日期转String
     *
     * @param dateTime 日期
     * @param pattern  日期正则格式
     * @return
     */
    public static String parseToString(DateTime dateTime, String pattern) {
        return dateTime.toString(DateTimeFormat.forPattern(pattern));
    }

    /**
     * 将秒时归零
     *
     * @param dateTime 指定日期
     * @return
     */
    public static DateTime getDateTimeWithoutSecond(DateTime dateTime) {
        DateTime newDate = dateTime.withSecondOfMinute(0).withMillisOfSecond(0);
        return newDate;
    }

    /**
     * 将秒时归零
     *
     * @param dateTime 指定日期字符串，格式必须是：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static DateTime getDateTimeWithoutSecond(String dateTime) {
        DateTime parse = DateTime.parse(dateTime, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        return getDateTimeWithoutSecond(parse);
    }

    public static Date getCurDate() {
        DateTime now = DateTime.now(DateTimeZone.getDefault());
        return now.toDate();
    }
}