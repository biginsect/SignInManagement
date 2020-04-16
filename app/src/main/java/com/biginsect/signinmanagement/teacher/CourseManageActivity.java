package com.biginsect.signinmanagement.teacher;

import com.biginsect.mvp.BaseActivity;
import com.biginsect.signinmanagement.R;

/**
 * @author biginsect
 * @date 2020/4/16
 */
public class CourseManageActivity extends BaseActivity<CourseManagePresenter> implements ICourseManageContract.IView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_management;
    }

    @Override
    protected CourseManagePresenter createPresenter() {
        return new CourseManagePresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
