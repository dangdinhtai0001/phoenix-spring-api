/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 * @Author Đặng Đình Tài
 * @Date 6/24/21, 3:04 PM
 */

package com.phoenix.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * @param strDate : String cần convert
     * @param format  : format ngày tháng trong java
     * @return - Date: Nếu convert thành công
     * - null: Nếu có lỗi trong lúc convert
     */
    public static Date convertString2Date(String strDate, String format) {
        try {
            return new SimpleDateFormat(format).parse(strDate);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }

    public static String convertDate2String(Date date, String format) {
        if (date == null) {
            return null;
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatStringDate(String strDate, String formFormat, String desFormat) {
        return convertDate2String(convertString2Date(strDate, formFormat), desFormat);
    }

    /**
     * @param startDate_1 : Date ngày bắt đầu của khoảng 1
     * @param endDate_1   : Date ngày kết thúc của khoảng 1
     * @param startDate_2 : Date ngày bắt đầu của khoảng 2
     * @param endDate_2   : Date ngày kết thúc của khoảng 2
     * @return :
     * - true: Nếu khoảng thời gian 1 và 2 giao nhau
     * - false: Nếu ngược lại
     */
    public static boolean isIntersectTime(Date startDate_1, Date endDate_1, Date startDate_2, Date endDate_2) {
        //Declare variable
        long startTime1, endTime1, startTime2, endTime2;

        if (startDate_1 == null) {
            startTime1 = Long.MAX_VALUE;
        } else {
            startTime1 = startDate_1.getTime();
        }
        if (endDate_1 == null) {
            endTime1 = Long.MAX_VALUE;
        } else {
            endTime1 = endDate_1.getTime();
        }
        if (startDate_2 == null) {
            startTime2 = Long.MAX_VALUE;
        } else {
            startTime2 = startDate_2.getTime();
        }
        if (endDate_2 == null) {
            endTime2 = Long.MAX_VALUE;
        } else {
            endTime2 = endDate_2.getTime();
        }

//        if (startTime1 > endTime1 || endTime1 > startTime2 || startTime2 > endTime2 || startTime1 > startTime2) {
//            return true;
//        }
        if (startTime1 == startTime2) {
            return true;
        }

        if (startTime1 > startTime2 && (startTime2 > endTime2 || endTime2 > startTime1 || startTime1 > endTime1)) {
            return true;
        }

        return startTime1 < startTime2 && (startTime1 > endTime1 || endTime1 > startTime2 || startTime2 > endTime2);
    }

    /**
     * @param strStartDate_1 : ngày bắt đầu khoảng 1 dạng String
     * @param strEndDate_1   : ngày kết thúc khoảng 1 dạng String
     * @param strStartDate_2 : ngày bắt đầu khoảng 2 dạng String
     * @param strEndDate_2   : ngày kết thúc khoảng 2 dạng String
     * @param format         : date format
     * @return xem lại
     */
    public static boolean isIntersectTime(String strStartDate_1, String strEndDate_1, String strStartDate_2,
                                          String strEndDate_2, String format) {
        Date startDate_1, endDate_1, startDate_2, endDate_2;


        startDate_1 = DateUtil.convertString2Date(strStartDate_1, format);
        endDate_1 = DateUtil.convertString2Date(strEndDate_1, format);
        startDate_2 = DateUtil.convertString2Date(strStartDate_2, format);
        endDate_2 = DateUtil.convertString2Date(strEndDate_2, format);

        return isIntersectTime(startDate_1, endDate_1, startDate_2, endDate_2);
    }
}
