package com.biginsect.signinmanagement.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuangfei.timetable.model.Schedule;
import com.zhuangfei.timetable.model.ScheduleEnable;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;

/**
 * @author lipeng
 * Created at 2020/4/14 11:12
 */
@Entity
public class Course implements Parcelable, ScheduleEnable {
    public static final String EXTRAS_ID = "extras_id";

    @Id
    private long courseId;
    private String courseName;
    //当前教师id
    private long teacherId;
    private String teacherName;
    /**
     * 开始上课的节次
     */
    private int start;

    /**
     * 上课节数
     */
    private int step;
    /**
     * 周几上
     */
    private int day;
    /**
     * 一个随机数，用于对应课程的颜色
     */
    private int colorRandom = 0;
    private long startTime;
    private long endTime;
    /**
     * 第几周至第几周上
     */
    @Convert(columnType = String.class, converter = IntegerConverter.class)
    private List<Integer> weekList;

    protected Course(Parcel in) {
        courseId = in.readLong();
        courseName = in.readString();
        teacherId = in.readLong();
        teacherName = in.readString();
        start = in.readInt();
        step = in.readInt();
        day = in.readInt();
        colorRandom = in.readInt();
        startTime = in.readLong();
        endTime = in.readLong();
    }

    @Generated(hash = 449820642)
    public Course(long courseId, String courseName, long teacherId, String teacherName,
                  int start, int step, int day, int colorRandom, long startTime, long endTime,
                  List<Integer> weekList) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.start = start;
        this.step = step;
        this.day = day;
        this.colorRandom = colorRandom;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekList = weekList;
    }

    @Generated(hash = 1355838961)
    public Course() {
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    @Override
    public Schedule getSchedule() {
        Schedule schedule = new Schedule();
        schedule.setDay(getDay());
        schedule.setName(getCourseName());
        schedule.setStart(getStart());
        schedule.setStep(getStep());
        schedule.setTeacher(getTeacherName());
        schedule.setWeekList(getWeekList());
        schedule.setColorRandom(2);
        schedule.putExtras(EXTRAS_ID, getCourseId());
        return schedule;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getColorRandom() {
        return colorRandom;
    }

    public void setColorRandom(int colorRandom) {
        this.colorRandom = colorRandom;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public List<Integer> getWeekList() {
        return weekList;
    }

    public void setWeekList(List<Integer> weekList) {
        this.weekList = weekList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(courseId);
        dest.writeString(courseName);
        dest.writeLong(teacherId);
        dest.writeString(teacherName);
        dest.writeInt(start);
        dest.writeInt(step);
        dest.writeInt(day);
        dest.writeInt(colorRandom);
        dest.writeLong(startTime);
        dest.writeLong(endTime);
    }

    static class IntegerConverter implements PropertyConverter<List<Integer>, String> {
        private final Gson mGson;

        public IntegerConverter() {
            mGson = new Gson();
        }

        @Override
        public List<Integer> convertToEntityProperty(String databaseValue) {
            Type type = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            return mGson.<ArrayList<Integer>>fromJson(databaseValue, type);
        }

        @Override
        public String convertToDatabaseValue(List<Integer> entityProperty) {
            return mGson.toJson(entityProperty);
        }
    }
}
