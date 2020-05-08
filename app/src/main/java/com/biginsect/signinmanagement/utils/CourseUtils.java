package com.biginsect.signinmanagement.utils;


import android.text.format.Time;

import androidx.annotation.IntDef;
import androidx.annotation.IntRange;

/**
 * 1--8:00
 * 2--8:50
 * 3--9:40
 * 4--10:30
 * 5--11:20---->12:10
 * <p>
 * 6--14:30
 * 7--15:20
 * 8--16:10
 * 9--17:00----->17:50
 * <p>
 * 10--18:40
 * 11--19:30
 * 12--20:20---->21:10
 */

public final class CourseUtils {
    //正常签到
    public static final int ATTENDANCE = 1;
    //迟到
    public static final int LATE = 2;
    //缺课
    public static final int ABSENCE = 3;
    //请假
    public static final int LEAVE = 4;

    //一节课的时间
    private static final long DURATION = 40 * 60L;
    //两节课之间的休息时间
    private static final long INTERVAL = 10 * 60L;

    private CourseUtils() {

    }

    /**
     * 前十分钟和上课后十分钟
     *
     * @param start 课的开始节数
     * @return 是否是正常签到
     */
    public static boolean isAttendance(int start) {//1 6 10
        int beginHour = 0;
        int beginMinute = 0;
        int endHour = 0;
        int endMinute = 0;
        if (start == 1) {
            beginHour = 7;
            beginMinute = 50;
            endHour = 8;
            endMinute = 10;
        } else if (start == 6) {
            beginHour = endHour = 14;
            beginMinute = 20;
            endMinute = 40;
        } else if (start == 10) {
            beginHour = endHour = 18;
            beginMinute = 30;
            endMinute = 50;
        }

        final long currentTimeMillis = System.currentTimeMillis();// 当前时间

        Time now = new Time();
        now.set(currentTimeMillis);

        Time startTime = new Time();
        startTime.set(currentTimeMillis);
        startTime.hour = beginHour;
        startTime.minute = beginMinute;

        Time endTime = new Time();
        endTime.set(currentTimeMillis);
        endTime.hour = endHour;
        endTime.minute = endMinute;

        return !now.before(startTime) && !now.after(endTime);
    }

    public static boolean isLate(int start, @IntRange(from = 2, to = 4) int step) {
        int beginHour = 0;
        int beginMinute = 0;
        int endHour = 0;
        int endMinute = 0;
        int duration = step == 2 ? 10 : (step == 3 ? 20 : 30);
        if (start == 1) {
            beginHour = 8;
            beginMinute = 10;
        } else if (start == 6) {
            beginHour = 14;
            beginMinute = 40;
        } else if (start == 10) {
            beginHour = 18;
            beginMinute = 50;
        }

        endHour = beginHour + (step * 40 + duration + beginMinute) / 60;
        endMinute = (step * 40 + duration + beginMinute) % 60;

        final long currentTimeMillis = System.currentTimeMillis();// 当前时间

        Time now = new Time();
        now.set(currentTimeMillis);

        Time startTime = new Time();
        startTime.set(currentTimeMillis);
        startTime.hour = beginHour;
        startTime.minute = beginMinute;

        Time endTime = new Time();
        endTime.set(currentTimeMillis);
        endTime.hour = endHour;
        endTime.minute = endMinute;

        return !now.before(startTime) && !now.after(endTime);
    }
}
