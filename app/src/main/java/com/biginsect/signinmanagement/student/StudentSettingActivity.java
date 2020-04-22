package com.biginsect.signinmanagement.student;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.biginsect.mvp.BaseActivity;
import com.biginsect.signinmanagement.R;
import com.biginsect.signinmanagement.app.AppData;
import com.biginsect.signinmanagement.utils.StringUtils;
import com.kongzue.dialog.v3.CustomDialog;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * @author lipeng
 * Created at 2020/4/7 11:38
 */
public class StudentSettingActivity extends BaseActivity<SettingPresenter> implements ISettingContract.IView {

    @BindView(R.id.cl_student_personal_info)
    ConstraintLayout clStudentUpdateTeacher;
    @BindView(R.id.cl_student_update_password)
    ConstraintLayout clStudentUpdatePassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_student_setting;
    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter();
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void studentNotExist() {
        Toasty.error(this, getString(R.string.student_not_exist), Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void updateSuccess() {
        Toasty.success(this, getString(R.string.update_success), Toasty.LENGTH_SHORT).show();
    }

    @OnClick({R.id.cl_student_personal_info, R.id.cl_student_update_password})
    public void onViewClicked(View view) {
        final int viewId = view.getId();
        if (viewId == R.id.cl_student_personal_info) {

        } else if (viewId == R.id.cl_student_update_password) {
            updatePassword();
        }
    }

    private void updatePassword() {
        CustomDialog.show(this, R.layout.layout_student_update_password, new CustomDialog.OnBindView() {
            @Override
            public void onBind(CustomDialog dialog, View v) {
                Button cancel = v.findViewById(R.id.btn_update_password_cancel);
                cancel.setOnClickListener(v1 -> dialog.doDismiss());

                EditText etOld = v.findViewById(R.id.et_old_student_password);
                EditText etNew = v.findViewById(R.id.et_new_student_password);
                Button ok = v.findViewById(R.id.btn_update_password_ok);

                ok.setOnClickListener(v1 -> {
                    String lastPassword = etOld.getText().toString();
                    if (StringUtils.isBlank(lastPassword) || !lastPassword.equals(AppData.INSTANCE.getCurrentStudent().getStudentPassword())) {
                        Toasty.info(StudentSettingActivity.this, getString(R.string.wrong_password), Toasty.LENGTH_SHORT).show();
                    } else {
                        String newPassword = etNew.getText().toString();
                        if (StringUtils.isBlank(newPassword)) {
                            Toasty.info(StudentSettingActivity.this, getString(R.string.password_cannot_be_empty), Toasty.LENGTH_SHORT).show();
                        } else {
                            mPresenter.updatePassword(AppData.INSTANCE.getCurrentStudent().getStudentId(), newPassword);
                            dialog.doDismiss();
                        }
                    }
                });
            }
        });
    }
}
