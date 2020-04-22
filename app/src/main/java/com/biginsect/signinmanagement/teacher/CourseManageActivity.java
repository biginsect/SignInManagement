package com.biginsect.signinmanagement.teacher;

import android.view.View;
import android.widget.ImageView;

import com.biginsect.mvp.BaseActivity;
import com.biginsect.signinmanagement.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author biginsect
 * @date 2020/4/16
 */
public class CourseManageActivity extends BaseActivity<CourseManagePresenter> implements ICourseManageContract.IView {

    @BindView(R.id.iv_teacher_add_course)
    ImageView mIvAddCourse;
    @BindView(R.id.iv_course_manage_back)
    ImageView mIvBack;

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

    @OnClick({R.id.iv_teacher_add_course, R.id.iv_course_manage_back})
    public void onViewClicked(View view){
        final int viewId = view.getId();
        if (viewId == R.id.iv_course_manage_back){
            finish();
        }else if (viewId == R.id.iv_teacher_add_course){//添加课程

        }
    }
}
