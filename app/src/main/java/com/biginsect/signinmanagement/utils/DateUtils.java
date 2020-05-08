package com.biginsect.signinmanagement.utils;


import java.util.Calendar;

/**
 * @author biginsect
 * @date 2020/5/9
 */
public final class DateUtils {

    private DateUtils(){

    }

    /**
     * 获取当前是周几,1-周日，7-周六
     * @return
     */
    public static int otainCurrentDay(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
