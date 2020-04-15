package com.biginsect.signinmanagement.dao;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author lipeng
 * Created at 2020/4/14 11:12
 */
@Entity
public class Course implements Parcelable {
    @Id
    private long courseId;
    private long studentId;
    private String courseName;

    @Generated(hash = 1119131261)
    public Course(long courseId, long studentId, String courseName) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.courseName = courseName;
    }

    @Generated(hash = 1355838961)
    public Course() {
    }

    protected Course(Parcel in) {
        courseId = in.readLong();
        studentId = in.readLong();
        courseName = in.readString();
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

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(courseId);
        dest.writeLong(studentId);
        dest.writeString(courseName);
    }
}
