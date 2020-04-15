package com.biginsect.signinmanagement.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ATTENDANCE".
*/
public class AttendanceDao extends AbstractDao<Attendance, Long> {

    public static final String TABLENAME = "ATTENDANCE";

    /**
     * Properties of entity Attendance.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property AttId = new Property(0, Long.class, "attId", true, "_id");
        public final static Property State = new Property(1, int.class, "state", false, "STATE");
        public final static Property StudentId = new Property(2, long.class, "studentId", false, "STUDENT_ID");
    }


    public AttendanceDao(DaoConfig config) {
        super(config);
    }
    
    public AttendanceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ATTENDANCE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: attId
                "\"STATE\" INTEGER NOT NULL ," + // 1: state
                "\"STUDENT_ID\" INTEGER NOT NULL );"); // 2: studentId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ATTENDANCE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Attendance entity) {
        stmt.clearBindings();
 
        Long attId = entity.getAttId();
        if (attId != null) {
            stmt.bindLong(1, attId);
        }
        stmt.bindLong(2, entity.getState());
        stmt.bindLong(3, entity.getStudentId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Attendance entity) {
        stmt.clearBindings();
 
        Long attId = entity.getAttId();
        if (attId != null) {
            stmt.bindLong(1, attId);
        }
        stmt.bindLong(2, entity.getState());
        stmt.bindLong(3, entity.getStudentId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Attendance readEntity(Cursor cursor, int offset) {
        Attendance entity = new Attendance( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // attId
            cursor.getInt(offset + 1), // state
            cursor.getLong(offset + 2) // studentId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Attendance entity, int offset) {
        entity.setAttId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setState(cursor.getInt(offset + 1));
        entity.setStudentId(cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Attendance entity, long rowId) {
        entity.setAttId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Attendance entity) {
        if(entity != null) {
            return entity.getAttId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Attendance entity) {
        return entity.getAttId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}