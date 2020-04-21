package com.biginsect.signinmanagement.dao;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 授课 上课
 * @author biginsect
 * @date 2020/4/21
 */
@Entity
public class Lesson implements Parcelable {

    private long studentId;
    private long teacherId;

    protected Lesson(Parcel in) {
        studentId = in.readLong();
        teacherId = in.readLong();
    }

    @Generated(hash = 334299191)
    public Lesson(long studentId, long teacherId) {
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    @Generated(hash = 1669664117)
    public Lesson() {
    }

    public static final Creator<Lesson> CREATOR = new Creator<Lesson>() {
        @Override
        public Lesson createFromParcel(Parcel in) {
            return new Lesson(in);
        }

        @Override
        public Lesson[] newArray(int size) {
            return new Lesson[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(studentId);
        dest.writeLong(teacherId);
    }

    public long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }
}
