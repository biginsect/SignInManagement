package com.biginsect.signinmanagement.register;

import com.biginsect.mvp.MvpBasePresenter;
import com.biginsect.signinmanagement.app.AppApplication;
import com.biginsect.signinmanagement.dao.Student;
import com.biginsect.signinmanagement.dao.StudentDao;
import com.biginsect.signinmanagement.dao.Teacher;
import com.biginsect.signinmanagement.dao.TeacherDao;

/**
 * @author biginsect
 * @date 2020/4/20
 */
public class RegisterPresenter extends MvpBasePresenter<IRegisterContract.IView> implements IRegisterContract.IPresenter {

    @Override
    public void studentRegister(String userName, long studentId, String password) {
        Student exitStudent = AppApplication.getDaoSession().getStudentDao().
                queryBuilder().where(StudentDao.Properties.StudentId.eq(studentId)).unique();
        if(exitStudent != null && isAttached()){
            getView().idIsExist();
            return;
        }
        Student student = new Student();
        student.setStudentId(studentId);
        student.setStudentName(userName);
        student.setStudentPassword(password);
        AppApplication.getDaoSession().getStudentDao().insertOrReplace(student);
        if (isAttached()) {
            getView().registerSucceed();
        }
    }

    @Override
    public void teacherRegister(String userName, long teacherId, String password) {
        Teacher exitTeacher = AppApplication.getDaoSession().getTeacherDao()
                .queryBuilder().where(TeacherDao.Properties.TeacherId.eq(teacherId)).unique();
        if (exitTeacher != null && isAttached()){
            getView().idIsExist();
            return;
        }
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherName(userName);
        teacher.setTeacherPassword(password);
        AppApplication.getDaoSession().getTeacherDao().insertOrReplace(teacher);
        if (isAttached()) {
            getView().registerSucceed();
        }
    }
}
