package com.biginsect.signinmanagement.student;

import com.biginsect.mvp.MvpBasePresenter;
import com.biginsect.signinmanagement.app.AppApplication;
import com.biginsect.signinmanagement.dao.Student;
import com.biginsect.signinmanagement.dao.StudentDao;

/**
 * @author lipeng
 * Created at 2020/4/7 11:54
 */
public class SettingPresenter extends MvpBasePresenter<ISettingContract.IView>
        implements ISettingContract.IPresenter {

    @Override
    public void updatePassword(long studentId, String newPassword) {
        if (!isAttached()){
            return;
        }
        Student student = AppApplication.getDaoSession().getStudentDao().queryBuilder().where(StudentDao.Properties.StudentId.eq(studentId)).unique();
        if (student == null){
            getView().studentNotExist();
        }else {
            student.setStudentPassword(newPassword);
            AppApplication.getDaoSession().getStudentDao().update(student);
            getView().updateSuccess();
        }
    }
}
