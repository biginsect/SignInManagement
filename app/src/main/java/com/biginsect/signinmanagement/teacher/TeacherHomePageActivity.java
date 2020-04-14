package com.biginsect.signinmanagement.teacher;

import android.widget.LinearLayout;

import com.biginsect.mvp.BaseActivity;
import com.biginsect.mvp.MvpPresenter;
import com.biginsect.signinmanagement.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author biginsect
 * @date 2020/4/6
 */
public class TeacherHomePageActivity extends BaseActivity {

    @BindView(R.id.ll_teacher_add_student)
    LinearLayout llTeacherAddStudent;
    @BindView(R.id.ll_teacher_attendance_check)
    LinearLayout llTeacherAttendanceCheck;
    @BindView(R.id.ll_teacher_course_management)
    LinearLayout llTeacherCourseManagement;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_home_page;
    }

    @Override
    protected MvpPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.ll_teacher_add_student)
    public void addStudentClicked() {

    }

    @OnClick(R.id.ll_teacher_attendance_check)
    public void checkAttendanceClicked() {

    }

   @OnClick(R.id.ll_teacher_course_management)
    public void courseManagementClicked(){

   }
}
