package com.biginsect.signinmanagement.teacher;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.biginsect.mvp.BaseActivity;
import com.biginsect.signinmanagement.R;
import com.biginsect.signinmanagement.dao.Course;
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

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_teacher_add_course)
    ImageView mIvAddCourse;
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
    protected void initView() {
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

        mCourseTable.curWeek(1).
                callback(new ISchedule.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, List<Schedule> scheduleList) {
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

    @OnClick({R.id.iv_teacher_add_course, R.id.iv_course_manage_back})
    public void onViewClicked(View view) {
        final int viewId = view.getId();
        if (viewId == R.id.iv_course_manage_back) {
            finish();
        } else if (viewId == R.id.iv_teacher_add_course) {//添加课程
            if (mWeekView.isShowing()) hideWeekView();
            else showWeekView();
        }
    }

    /**
     * 周次选择布局的左侧被点击时回调<br/>
     * 对话框修改当前周次
     */
    protected void onWeekLeftLayoutClicked() {
        final String items[] = new String[20];
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
                });
        builder.setPositiveButton("设置为当前周", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (target != -1) {
                    mWeekView.curWeek(target + 1).updateView();
                    mCourseTable.changeWeekForce(target + 1);
                }
            }
        });
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }

    /**
     * 隐藏周次选择，此时需要将课表的日期恢复到本周并将课表切换到当前周
     */
    public void hideWeekView() {
        mWeekView.isShow(false);
        int cur = mCourseTable.curWeek();
        mCourseTable.onDateBuildListener()
                .onUpdateDate(cur, cur);
        mCourseTable.changeWeekOnly(cur);
    }

    public void showWeekView() {
        mWeekView.isShow(true);
    }
}
