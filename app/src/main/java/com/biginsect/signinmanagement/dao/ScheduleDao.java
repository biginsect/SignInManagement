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
 * DAO for table "SCHEDULE".
*/
public class ScheduleDao extends AbstractDao<Schedule, Void> {

    public static final String TABLENAME = "SCHEDULE";

    /**
     * Properties of entity Schedule.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property CourseId = new Property(0, long.class, "courseId", false, "COURSE_ID");
        public final static Property StudentId = new Property(1, long.class, "studentId", false, "STUDENT_ID");
    }


    public ScheduleDao(DaoConfig config) {
        super(config);
    }
    
    public ScheduleDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SCHEDULE\" (" + //
                "\"COURSE_ID\" INTEGER NOT NULL ," + // 0: courseId
                "\"STUDENT_ID\" INTEGER NOT NULL );"); // 1: studentId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SCHEDULE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Schedule entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getCourseId());
        stmt.bindLong(2, entity.getStudentId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Schedule entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getCourseId());
        stmt.bindLong(2, entity.getStudentId());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Schedule readEntity(Cursor cursor, int offset) {
        Schedule entity = new Schedule( //
            cursor.getLong(offset + 0), // courseId
            cursor.getLong(offset + 1) // studentId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Schedule entity, int offset) {
        entity.setCourseId(cursor.getLong(offset + 0));
        entity.setStudentId(cursor.getLong(offset + 1));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Schedule entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Schedule entity) {
        return null;
    }

    @Override
    public boolean hasKey(Schedule entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
