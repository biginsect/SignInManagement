package com.biginsect.signinmanagement.dao;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * @author lipeng
 * Created at 2020/4/14 11:15
 */
@Entity
public class Attendance implements Parcelable {

    @Id(autoincrement = true)
    private Long attId;
    //请假，迟到，缺课
    @Convert(converter = StateConverter.class, columnType = Integer.class)
    private State state;
    private long studentId;

    @Generated(hash = 1048853817)
    public Attendance(Long attId, State state, long studentId) {
        this.attId = attId;
        this.state = state;
        this.studentId = studentId;
    }

    @Generated(hash = 812698609)
    public Attendance() {
    }

    protected Attendance(Parcel in) {
        if (in.readByte() == 0) {
            attId = null;
        } else {
            attId = in.readLong();
        }
        state = in.readParcelable(State.class.getClassLoader());
        studentId = in.readLong();
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public Long getAttId() {
        return this.attId;
    }

    public void setAttId(Long attId) {
        this.attId = attId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (attId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(attId);
        }
        dest.writeParcelable(state, flags);
        dest.writeLong(studentId);
    }

    public enum State implements Parcelable{
        /**
         * 正常签到
         */
        ATTENDANCE(1),

        /**
         * 缺课
         */
        ABSENCE(2),

        /**
         * 请假
         */
        LEAVE(3);

        final int id;

        State(int id) {
            this.id = id;
        }

        public static final Creator<State> CREATOR = new Creator<State>() {
            @Override
            public State createFromParcel(Parcel in) {
                return State.values()[in.readInt()];
            }

            @Override
            public State[] newArray(int size) {
                return new State[size];
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

    static class StateConverter implements PropertyConverter<State, Integer>{

        @Override
        public State convertToEntityProperty(Integer databaseValue) {
            if (databaseValue == null)
                return null;

            for (State state : State.values()) {
                if (state.id == databaseValue) {
                    return state;
                }
            }

            return State.ATTENDANCE;
        }

        @Override
        public Integer convertToDatabaseValue(State entityProperty) {
            return entityProperty == null ? null : entityProperty.id;
        }
    }
}
