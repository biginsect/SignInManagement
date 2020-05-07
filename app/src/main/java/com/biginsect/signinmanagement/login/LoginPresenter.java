package com.biginsect.signinmanagement.login;

import android.util.Log;

import com.biginsect.mvp.MvpBasePresenter;
import com.biginsect.signinmanagement.app.AppApplication;
import com.biginsect.signinmanagement.app.AppData;
import com.biginsect.signinmanagement.dao.Student;
import com.biginsect.signinmanagement.dao.Teacher;
import com.biginsect.signinmanagement.utils.ListUtils;

import java.util.List;

/**
 * @author biginsect
 * @date 2020-03-26
 */
public class LoginPresenter extends MvpBasePresenter<ILoginContract.IView>
        implements ILoginContract.IPresenter {


    @Override
    public void teacherLogin(long id, String password) {
        boolean find = false;
        List<Teacher> teacherList = AppApplication.getDaoSession().getTeacherDao().queryBuilder().list();
        if (!isAttached()) {
            return;
        }
        if (ListUtils.isEmpty(teacherList)) {
            getView().showNoUser();
        } else {
            for (Teacher teacher : teacherList) {
                if (teacher.getTeacherId().equals(id) && password.equals(teacher.getTeacherPassword())) {
                    AppData.INSTANCE.setCurrentTeacher(teacher);
                    getView().teacherLoginSucceed();
                    find = true;
                }
            }
        }
        if (!find) {
            getView().loginFailed();
        }
    }

    @Override
    public void studentLogin(long id, String password) {
        boolean find = false;
        List<Student> studentList = AppApplication.getDaoSession().getStudentDao().queryBuilder().list();
        if (!isAttached()) {
            return;
        }
        if (ListUtils.isEmpty(studentList)) {
            getView().showNoUser();
        } else {
            for (Student student : studentList) {
                if (id == student.getStudentId() && password.equals(student.getStudentPassword())) {
                    AppData.INSTANCE.setCurrentStudent(student);
                    getView().studentLoginSucceed();
                    find = true;
                }
            }
            if (!find) {
                getView().loginFailed();
            }
        }
    }
}
