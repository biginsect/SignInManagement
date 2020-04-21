package com.biginsect.signinmanagement.app;

import com.biginsect.signinmanagement.dao.Student;
import com.biginsect.signinmanagement.dao.Teacher;

/**
 * 全局保存当前登录用户信息
 *
 * @author biginsect
 * @date 2020/4/21
 */
public enum AppData {

    INSTANCE;

    private Student currentStudent;
    private Teacher currentTeacher;

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    public Teacher getCurrentTeacher() {
        return currentTeacher;
    }

    public void setCurrentTeacher(Teacher currentTeacher) {
        this.currentTeacher = currentTeacher;
    }
}
