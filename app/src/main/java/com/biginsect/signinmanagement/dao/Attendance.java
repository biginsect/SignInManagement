package com.biginsect.signinmanagement.dao;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author lipeng
 * Created at 2020/4/14 11:15
 */
@Entity
public class Attendance implements Parcelable {

    @Id(autoincrement = true)
    private Long attId;
    //请假，迟到，缺课
    private int state;
    private long studentId;
    
    protected Attendance(Parcel in) {
        attId = in.readLong();
        state = in.readInt();
        studentId = in.readLong();
    }

    @Generated(hash = 241322111)
    public Attendance(Long attId, int state, long studentId) {
        this.attId = attId;
        this.state = state;
        this.studentId = studentId;
    }

    @Generated(hash = 812698609)
    public Attendance() {
    }

    public static final Creator<Attendance> CREATOR = new Creator<Attendance>() {
        @Override
        public Attendance createFromParcel(Parcel in) {
            return new Attendance(in);
        }

        @Override
        public Attendance[] newArray(int size) {
            return new Attendance[size];
        }
    };

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(state);
        dest.writeLong(studentId);
    }

    public Long getAttId() {
        return this.attId;
    }

    public void setAttId(Long attId) {
        this.attId = attId;
    }
}
