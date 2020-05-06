package com.biginsect.signinmanagement.teacher;

import com.biginsect.mvp.MvpBasePresenter;
import com.biginsect.signinmanagement.app.AppApplication;
import com.biginsect.signinmanagement.dao.Course;
import com.biginsect.signinmanagement.dao.CourseDao;

/**
 * @author lipeng
 * Created at 2020/4/16 11:06
 */
public class CourseManagePresenter extends MvpBasePresenter<ICourseManageContract.IView>
        implements ICourseManageContract.IPresenter {

    @Override
    public void addCourse(Course course) {
        if (!isAttached()){
            return;
        }
        long id = course.getCourseId();
        Course tmp = AppApplication.getDaoSession().getCourseDao().queryBuilder().where(CourseDao.Properties.CourseId.eq(id)).unique();
        if (tmp != null){
            getView().onFailed("该Id对应的课程已存在!");
        }else {
            AppApplication.getDaoSession().getCourseDao().insertOrReplace(course);
            getView().addSuccess();
        }
    }

    @Override
    public void deleteCourse(Course course) {

    }
}
