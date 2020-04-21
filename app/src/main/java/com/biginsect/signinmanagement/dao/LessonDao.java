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
 * DAO for table "LESSON".
*/
public class LessonDao extends AbstractDao<Lesson, Void> {

    public static final String TABLENAME = "LESSON";

    /**
     * Properties of entity Lesson.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property StudentId = new Property(0, long.class, "studentId", false, "STUDENT_ID");
        public final static Property TeacherId = new Property(1, long.class, "teacherId", false, "TEACHER_ID");
    }


    public LessonDao(DaoConfig config) {
        super(config);
    }
    
    public LessonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LESSON\" (" + //
                "\"STUDENT_ID\" INTEGER NOT NULL ," + // 0: studentId
                "\"TEACHER_ID\" INTEGER NOT NULL );"); // 1: teacherId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LESSON\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Lesson entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getStudentId());
        stmt.bindLong(2, entity.getTeacherId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Lesson entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getStudentId());
        stmt.bindLong(2, entity.getTeacherId());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Lesson readEntity(Cursor cursor, int offset) {
        Lesson entity = new Lesson( //
            cursor.getLong(offset + 0), // studentId
            cursor.getLong(offset + 1) // teacherId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Lesson entity, int offset) {
        entity.setStudentId(cursor.getLong(offset + 0));
        entity.setTeacherId(cursor.getLong(offset + 1));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Lesson entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Lesson entity) {
        return null;
    }

    @Override
    public boolean hasKey(Lesson entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}