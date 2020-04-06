package com.biginsect.signinmanagement.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.biginsect.signinmanagement.dao.DaoMaster;
import com.biginsect.signinmanagement.dao.DaoSession;

/**
 * @author biginsect
 * @date 2020/4/4
 */
public class AppApplication extends Application {

    private static DaoSession mDaoSession;
    private static final String DB_Name = "SignManager.db";

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_Name, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
