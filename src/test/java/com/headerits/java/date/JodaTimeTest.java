package com.headerits.java.date;

import com.headerits.util.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.Date;

/**
 * <p>Description: </p>
 * <p>Title: JodaTimeTest </p>
 * <p>Create Time: 2018/8/23 14:58 </p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class JodaTimeTest {

    @Test
    public void test() throws Exception {
        // 2018-08-23 14:58:22
        //Calendar calendar = Calendar.getInstance();
        //calendar.set(2018, 7, 23);
        //System.out.println(calendar.getTime().toLocaleString());
        DateTime dateTime = new DateTime(2018, 7, 23, 15, 14, 30);
        Date date = dateTime.toDate();
        DateTime dateTime1 = new DateTime(new Date());
        System.out.println(dateTime1.toString("yyyy-MM-dd HH:mm:ss"));
        //System.out.println(date.toLocaleString());
        //System.out.println(dateTime.plusDays(45).toString("yyyy-MM-dd HH:mm:ss"));
        //System.out.println(dateTime.plusDays(45).plusMonths(1).dayOfWeek().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void test1() throws Exception {
        LocalDateTime dateTime = new LocalDateTime();
        //LocalDateTime.Property property = dateTime.centuryOfEra();
        System.out.println(dateTime.toString(DateUtils.COMMON_DATETIME));
        System.out.println(dateTime.hourOfDay().withMaximumValue());

        //System.out.println(dateTime.toDate().toLocaleString());
        //LocalDateTime localDateTime = new LocalDateTime();

    }
}
