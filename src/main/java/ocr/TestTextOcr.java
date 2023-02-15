package ocr;

import net.sourceforge.tess4j.TesseractException;
import utils.DateTimeUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.11.07
 */
public class TestTextOcr {


    public static void main(String[] args) throws IOException, TesseractException, ParseException {
//
//        final Date beginSomeTime = DateTimeUtils.getBeginSomeTime(1);
//        final String s1 = DateTimeUtils.dateToString(beginSomeTime, DateTimeUtils.sdfyyyyMMddHHmmss);
//        System.out.println(s1);

        String a = "d89cb7b223e74aa0.a8050422.715ed297.pdf";
        final String[] split = a.split(".");
        final String substring = a.substring(a.lastIndexOf("."));
        System.out.println(split);
    }

    /*private static Date getStringInt(String str) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取上个月的第一天
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        final String timeStrStartLast = formatTemp.format(calendar.getTime());
        // 获取上个月的最后一天
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        timeStrEndLast = formatTemp.format(calendar.getTime());
        final String substring = timeStrStartLast.substring(0, 4);
        final String substring1 = timeStrStartLast.substring(4, 6);
        //上上个月的第一天
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        timeStrStartBefore=formatTemp.format(calendar.getTime());
        //上上个月的最后一天
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        timeStrEndBefore=formatTemp.format(calendar.getTime());
        yearBefor=timeStrStartBefore.substring(0,4);
        monthBefore=timeStrStartBefore.substring(4,6);
    }*/



}
