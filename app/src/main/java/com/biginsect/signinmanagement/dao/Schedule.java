package com.biginsect.signinmanagement.dao;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 选课表
 * @author biginsect
 * @date 2020/4/15
 */
@Entity
public class Schedule implements Parcelable {

    private long courseId;
    private long studentId;

    protected Schedule(Parcel in) {
        courseId = in.readLong();
        studentId = in.readLong();
    }

    @Generated(hash = 2113063531)
    public Schedule(long courseId, long studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    @Generated(hash = 729319394)
    public Schedule() {
    }

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel in) {
            return new Schedule(in);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(courseId);
        dest.writeLong(studentId);
    }

    public long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
