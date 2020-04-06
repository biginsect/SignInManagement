package com.biginsect.signinmanagement.login;

import com.biginsect.mvp.MvpBasePresenter;
import com.biginsect.signinmanagement.app.AppApplication;
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
    public void teacherLogin(String userName, String password) {
        List<Teacher> teacherList = AppApplication.getDaoSession().getTeacherDao().queryBuilder().list();
        if (!isAttached()) {
            return;
        }
        if (ListUtils.isEmpty(teacherList)) {
            getView().showNoUser();
        } else {
            for (Teacher teacher : teacherList) {
                if (userName.equals(teacher.getTeacherName()) && password.equals(teacher.getTeacherPassword())) {
                    getView().teacherLoginSucceed();
                } else {
                    getView().loginFailed();
                }
            }
        }
    }

    @Override
    public void studentLogin(String userName, String password) {
        List<Student> studentList = AppApplication.getDaoSession().getStudentDao().queryBuilder().list();
        if (!isAttached()) {
            return;
        }
        if (ListUtils.isEmpty(studentList)) {
            getView().showNoUser();
        } else {
            for (Student student : studentList) {
                if (userName.equals(student.getStudentName()) && password.equals(student.getStudentPassword())) {
                    getView().studentLoginSucceed();
                } else {
                    getView().loginFailed();
                }
            }
        }
    }

    @Override
    public void studentRegister(String userName, String password) {
        Student student = new Student();
        student.setStudentId(null);
        student.setStudentName(userName);
        student.setStudentPassword(password);
        AppApplication.getDaoSession().getStudentDao().insertOrReplace(student);
        if (isAttached()) {
            getView().registerSucceed();
        }
    }

    @Override
    public void teacherRegister(String userName, String password) {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(null);
        teacher.setTeacherName(userName);
        teacher.setTeacherPassword(password);
        AppApplication.getDaoSession().getTeacherDao().insertOrReplace(teacher);
        if (isAttached()) {
            getView().registerSucceed();
        }
    }
}
