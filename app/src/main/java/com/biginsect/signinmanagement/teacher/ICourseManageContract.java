package com.biginsect.signinmanagement.teacher;

import com.biginsect.mvp.MvpPresenter;
import com.biginsect.mvp.MvpView;
import com.biginsect.signinmanagement.dao.Course;

/**
 * @author lipeng
 * Created at 2020/4/16 11:05
 */
public interface ICourseManageContract {

    interface IView extends MvpView{

        void addSuccess(Course course);

        void onFailed(String msg);
    }

    interface IPresenter extends MvpPresenter<IView>{

        void addCourse(Course course);

        void deleteCourse(Course course);
    }
}
