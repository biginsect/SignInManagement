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
public class Student implements Parcelable {
    @Id
    private Long studentId;
    private String studentName;
    private String studentPassword;


    protected Student(Parcel in) {
        studentId = in.readLong();
        studentName = in.readString();
        studentPassword = in.readString();
    }

    @Generated(hash = 1168609020)
    public Student(Long studentId, String studentName, String studentPassword) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }


    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(studentId);
        dest.writeString(studentName);
        dest.writeString(studentPassword);
    }
}
