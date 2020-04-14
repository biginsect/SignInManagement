package com.biginsect.signinmanagement.dao;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author biginsect
 * @date 2020/4/4
 */
@Entity
public class Teacher implements Parcelable {

    @Id
    private Long teacherId;
    private String teacherName;
    private String teacherPassword;
    

    protected Teacher(Parcel in) {
        if (in.readByte() == 0) {
            teacherId = null;
        } else {
            teacherId = in.readLong();
        }
        teacherName = in.readString();
        teacherPassword = in.readString();
    }

    @Generated(hash = 1916755203)
    public Teacher(Long teacherId, String teacherName, String teacherPassword) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherPassword = teacherPassword;
    }

    @Generated(hash = 1630413260)
    public Teacher() {
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (teacherId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(teacherId);
        }
        dest.writeString(teacherName);
        dest.writeString(teacherPassword);
    }
}
