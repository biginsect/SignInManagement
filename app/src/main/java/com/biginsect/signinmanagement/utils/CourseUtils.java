package com.biginsect.signinmanagement.utils;

/**
 * @author biginsect
 * @date 2020/5/8
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
    private static final long interval = 10 * 60L;

    /**
     * 1--8:00
     * 2--8:50
     * 3--9:40
     * 4--10:30
     * 5--11:20---->12:10
     *
     * 6--14:30
     * 7--15:20
     * 8--16:10
     * 9--17:00----->17:50
     *
     * 10--18:40
     * 11--19:30
     * 12--20:20---->21:10
     */
}
