package com.biginsect.signinmanagement.dao;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * @author lipeng
 * Created at 2020/4/14 11:12
 */
@Entity
public class Course implements Parcelable {
    @Id
    private long courseId;
    private String courseName;
    ///当前课程是周几的（周日至下一周周六）
    @Convert(converter = DayForWeekConverter.class, columnType = Integer.class)
    private DayForWeek dayForWeek;
    private long startTime;
    private long endTime;

    protected Course(Parcel in) {
        courseId = in.readLong();
        courseName = in.readString();
        dayForWeek = in.readParcelable(DayForWeek.class.getClassLoader());
        startTime = in.readLong();
        endTime = in.readLong();
    }

    @Generated(hash = 1023665047)
    public Course(long courseId, String courseName, DayForWeek dayForWeek,
                  long startTime, long endTime) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.dayForWeek = dayForWeek;
        this.startTime = startTime;
        this.endTime = endTime;
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(courseId);
        dest.writeString(courseName);
        dest.writeParcelable(dayForWeek, flags);
        dest.writeLong(startTime);
        dest.writeLong(endTime);
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

    public DayForWeek getDayForWeek() {
        return dayForWeek;
    }

    public void setDayForWeek(DayForWeek dayForWeek) {
        this.dayForWeek = dayForWeek;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public enum DayForWeek implements Parcelable {

        SUNDAY(1), MONDAY(2), TUESDAY(3),
        WEDNESDAY(4), THURSDAY(5), FRIDAY(6), SATURDAY(7);

        final int id;

        DayForWeek(int id) {
            this.id = id;
        }


        public static final Creator<DayForWeek> CREATOR = new Creator<DayForWeek>() {
            @Override
            public DayForWeek createFromParcel(Parcel in) {
                return DayForWeek.values()[in.readInt()];
            }

            @Override
            public DayForWeek[] newArray(int size) {
                return new DayForWeek[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
        }
    }

    static class DayForWeekConverter implements PropertyConverter<DayForWeek, Integer> {

        @Override
        public DayForWeek convertToEntityProperty(Integer databaseValue) {
            if (databaseValue == null)
                return null;

            for (DayForWeek dayForWeek : DayForWeek.values()) {
                if (dayForWeek.id == databaseValue) {
                    return dayForWeek;
                }
            }

            return DayForWeek.MONDAY;
        }

        @Override
        public Integer convertToDatabaseValue(DayForWeek entityProperty) {
            return entityProperty == null ? null : entityProperty.id;
        }
    }
}
