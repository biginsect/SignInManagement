package com.biginsect.signinmanagement.student;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.biginsect.mvp.BaseActivity;
import com.biginsect.mvp.MvpPresenter;
import com.biginsect.signinmanagement.R;
import com.biginsect.signinmanagement.bean.StudentSelection;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author biginsect
 * @date 2020/4/4
 */
public class StudentInfoPageActivity extends BaseActivity {

    @BindView(R.id.tv_student_name)
    TextView tvStudentName;
    @BindView(R.id.rv_student_selection)
    RecyclerView rvStudentSelection;

    private final static int SIGN = 0;
    private final static int ABSENCE = 1;
    private final static int ATTENDANCE = 2;

    private StudentSelectionAdapter mAdapter;

    private List<StudentSelection> selections = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_student_home_page;
    }

    @Override
    protected MvpPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        rvStudentSelection.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new StudentSelectionAdapter();
        rvStudentSelection.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                checkAndGo(position);
            }
        });
    }

    @Override
    protected void initData() {
        selections.add(new StudentSelection(R.drawable.student_sign, getString(R.string.student_sign)));
        selections.add(new StudentSelection(R.drawable.student_absence, getString(R.string.student_absence)));
        selections.add(new StudentSelection(R.drawable.student_attendance, getString(R.string.student_attendance)));
        mAdapter.setList(selections);
    }

    private void checkAndGo(int position) {
        switch (position) {
            case SIGN:
                break;
            case ABSENCE:
                break;
            case ATTENDANCE:
                break;
        }
    }
}
