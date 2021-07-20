/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/24/21, 3:21 PM
 */

package com.phoenix.time;

import com.phoenix.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

public class TestDateUtil {
    @Test
    public void testIsIntersectTime() throws ParseException {
        Date startDate_1 = DateUtil.convertString2Date("10/06/2021", "dd/MM/yyyy");
        Date endDate_1 = DateUtil.convertString2Date("27/06/2021", "dd/MM/yyyy");
        Date startDate_2 = DateUtil.convertString2Date("25/06/2021", "dd/MM/yyyy");
        Date endDate_2 = DateUtil.convertString2Date("30/06/2021", "dd/MM/yyyy");

        System.out.println(DateUtil.isIntersectTime(startDate_1, endDate_1, startDate_2, endDate_2));
    }

    @Test
    public void testIsIntersectTime2() throws ParseException {
        Assert.assertTrue(DateUtil.isIntersectTime("01/01/2021", "",
                "02/01/2021", null, "dd/MM/yyyy"));

        Assert.assertTrue(DateUtil.isIntersectTime("01/01/2021", "15/06/2021",
                "10/01/2021", "", "dd/MM/yyyy"));

        Assert.assertTrue(DateUtil.isIntersectTime("01/01/2021", "15/06/2021",
                "10/01/2021", "04/04/2021", "dd/MM/yyyy"));

        Assert.assertTrue(DateUtil.isIntersectTime("01/01/2021", "15/06/2021",
                "01/01/2021", "", "dd/MM/yyyy"));

        Assert.assertTrue(DateUtil.isIntersectTime("01/01/2021", "15/06/2021",
                "09/12/2021", "04/04/2021", "dd/MM/yyyy"));

        Assert.assertFalse(DateUtil.isIntersectTime("01/01/2021", "15/06/2021",
                "09/12/2021", "", "dd/MM/yyyy"));

        Assert.assertFalse(DateUtil.isIntersectTime("02/06/2021", "",
                "01/01/2000", "01/06/2021", "dd/MM/yyyy"));

        Assert.assertTrue(DateUtil.isIntersectTime("", "",
                "", "", "dd/MM/yyyy"));
    }

    @Test
    public void testConvertString2Date() {
        String raw = "30/02";
        Date date = DateUtil.convertString2Date(raw, "dd/MM");
        String strDate = DateUtil.convertDate2String(date, "dd/MM");
        System.out.println(date);
        System.out.println(strDate);
        Assert.assertNotNull(DateUtil.convertString2Date("30/02", "dd/MM"));
    }
}
