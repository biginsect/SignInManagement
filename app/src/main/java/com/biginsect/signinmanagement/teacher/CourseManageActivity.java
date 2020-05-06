package com.biginsect.signinmanagement.teacher;

import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.biginsect.mvp.BaseActivity;
import com.biginsect.signinmanagement.R;
import com.biginsect.signinmanagement.app.AppData;
import com.biginsect.signinmanagement.dao.Course;
import com.biginsect.signinmanagement.utils.ListUtils;
import com.biginsect.signinmanagement.utils.StringUtils;
import com.kongzue.dialog.v3.CustomDialog;
import com.zhuangfei.timetable.TimetableView;
import com.zhuangfei.timetable.listener.ISchedule;
import com.zhuangfei.timetable.listener.IWeekView;
import com.zhuangfei.timetable.model.Schedule;
import com.zhuangfei.timetable.view.WeekView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * @author biginsect
 * @date 2020/4/16
 */
public class CourseManageActivity extends BaseActivity<CourseManagePresenter> implements ICourseManageContract.IView {

    @BindView(R.id.tb_course_management_title)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_course_manage_back)
    ImageView mIvBack;
    @BindView(R.id.id_weekview)
    WeekView mWeekView;
    @BindView(R.id.table_course_manage)
    TimetableView mCourseTable;

    //记录切换的周次，不一定是当前周
    int target = -1;
    List<Course> courses = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_management;
    }

    @Override
    protected CourseManagePresenter createPresenter() {
        return new CourseManagePresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCourseTable.onDateBuildListener()
                .onHighLight();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_manage_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int menuId = item.getItemId();
        switch (menuId) {
            case R.id.menu_add_course:
                addCourse();
                break;
            case R.id.menu_select_week:
                checkAndShowWeekView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkAndShowWeekView(){
        if (mWeekView.isShowing()) hideWeekView();
        else showWeekView();
    }

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mWeekView.curWeek(1)
                .callback(new IWeekView.OnWeekItemClickedListener() {
                    @Override
                    public void onWeekClicked(int week) {
                        int cur = mCourseTable.curWeek();
                        //更新切换后的日期，从当前周cur->切换的周week
                        mCourseTable.onDateBuildListener()
                                .onUpdateDate(cur, week);
                        mCourseTable.changeWeekOnly(week);
                    }
                })
                .callback(new IWeekView.OnWeekLeftClickedListener() {
                    @Override
                    public void onWeekLeftClicked() {
                        onWeekLeftLayoutClicked();
                    }
                })
                .isShow(false)//设置隐藏，默认显示
                .showView();

        mCourseTable.curWeek(1)
                .callback(new ISchedule.OnWeekChangedListener() {
                    @Override
                    public void onWeekChanged(int curWeek) {
                        String title = "第" + curWeek + "周";
                        mTvTitle.setText(title);
                    }
                })
                .callback(new ISchedule.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, List<Schedule> scheduleList) {//点击某个课
                        Toasty.info(CourseManageActivity.this, "" + scheduleList.size(), Toasty.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void initData() {
        List<Integer> weeks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            weeks.add(i);
        }
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCourseId(i);
            course.setCourseName("组织");
            course.setDay(2);
            course.setStart(1);
            course.setStep(2);
            courses.add(course);
            course.setWeekList(weeks);
        }
        mWeekView.source(courses).showView();
        mCourseTable.source(courses).showView();
    }

    @OnClick({R.id.iv_course_manage_back})
    public void onViewClicked(View view) {
        final int viewId = view.getId();
        if (viewId == R.id.iv_course_manage_back) {
            finish();
        }
    }

    /**
     * 周次选择布局的左侧被点击时回调<br/>
     * 对话框修改当前周次
     */
    protected void onWeekLeftLayoutClicked() {
        final String[] items = new String[20];
        int itemCount = mWeekView.itemCount();
        for (int i = 0; i < itemCount; i++) {
            items[i] = "第" + (i + 1) + "周";
        }
        target = -1;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置当前周");
        builder.setSingleChoiceItems(items, mCourseTable.curWeek() - 1,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        target = i;
                    }
                })
                .setPositiveButton("设置为当前周", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (target != -1) {
                            mWeekView.curWeek(target + 1).updateView();
                            mCourseTable.changeWeekForce(target + 1);
                        }
                        hideWeekView();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    /**
     * 隐藏周次选择，此时需要将课表的日期恢复到本周并将课表切换到当前周
     */
    private void hideWeekView() {
        mWeekView.isShow(false);
        int cur = mCourseTable.curWeek();
        mCourseTable.onDateBuildListener().onUpdateDate(cur, cur);
        mCourseTable.changeWeekOnly(cur);
    }

    private void showWeekView() {
        mWeekView.isShow(true);
    }

    private void addCourse() {
        CustomDialog.show(this, R.layout.layout_add_course, new CustomDialog.OnBindView() {
            @Override
            public void onBind(CustomDialog dialog, View v) {
                Button cancel = v.findViewById(R.id.btn_add_course_cancel);
                cancel.setOnClickListener(v1 -> dialog.doDismiss());
                EditText etId = v.findViewById(R.id.et_add_course_id);
                EditText etName = v.findViewById(R.id.et_add_course_name);
                EditText etDay = v.findViewById(R.id.et_add_course_day);
                EditText etStart = v.findViewById(R.id.et_add_course_start);
                EditText etStep = v.findViewById(R.id.et_add_course_step);


                Button ok = v.findViewById(R.id.btn_add_course_ok);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idStr = etId.getText().toString();
                        String nameStr = etName.getText().toString();
                        String dayStr = etDay.getText().toString();
                        String startStr = etStart.getText().toString();
                        String stepStr = etStep.getText().toString();

                        if (StringUtils.isBlank(idStr) || StringUtils.isBlank(nameStr) || StringUtils.isBlank(dayStr)
                                || StringUtils.isBlank(startStr) || StringUtils.isBlank(stepStr)) {
                            Toasty.warning(CourseManageActivity.this, "信息不能为空!", Toasty.LENGTH_SHORT).show();
                        } else {
                            long id = Long.parseLong(idStr);
                            int day = Integer.parseInt(dayStr);
                            int start = Integer.parseInt(startStr);
                            int step = Integer.parseInt(stepStr);
                            if (day < 1 || day > 7) {
                                Toasty.error(CourseManageActivity.this, "日期选择是1到7!", Toasty.LENGTH_SHORT).show();
                            } else if (start < 1 || start > 12) {
                                Toasty.error(CourseManageActivity.this, "课的开始节数是1到12", Toasty.LENGTH_SHORT).show();
                            } else if (step < 1 || step > 12) {
                                Toasty.error(CourseManageActivity.this, "课的总节数是1到12", Toasty.LENGTH_SHORT).show();
                            } else if (start + step > 12) {
                                Toasty.error(CourseManageActivity.this, "课的时间超出范围12!", Toasty.LENGTH_SHORT).show();
                            } else {
                                Course course = new Course();
                                course.setCourseId(id);
                                course.setCourseName(nameStr);
                                course.setWeekList(ListUtils.defaultWeeks());
                                course.setStart(start);
                                course.setStep(step);
                                course.setTeacherId(AppData.INSTANCE.getCurrentTeacher().getTeacherId());
                                course.setTeacherName(AppData.INSTANCE.getCurrentTeacher().getTeacherName());
                                mPresenter.addCourse(course);
                            }
                        }
                    }
                });
            }
        });
    }
}
