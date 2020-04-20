package com.biginsect.signinmanagement.register;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.biginsect.mvp.BaseActivity;
import com.biginsect.signinmanagement.R;
import com.biginsect.signinmanagement.utils.StringUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * @author biginsect
 * @date 2020/4/20
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements IRegisterContract.IView {

    @BindView(R.id.et_user_name)
    TextInputEditText etUserName;
    @BindView(R.id.layout_user_name)
    TextInputLayout layoutUserName;
    @BindView(R.id.et_user_id)
    TextInputEditText etUserId;
    @BindView(R.id.layout_user_id)
    TextInputLayout layoutUserId;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.layout_password)
    TextInputLayout layoutPassword;
    @BindView(R.id.rb_student)
    RadioButton rbStudent;
    @BindView(R.id.rb_teacher)
    RadioButton rbTeacher;
    @BindView(R.id.group_user)
    RadioGroup groupUser;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private String userName;
    private String userIdText;
    private String password;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public void showSelectOccupation() {
        Toasty.info(this, getString(R.string.select_occupation), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInvalidInformation() {
        Toasty.error(this, getText(R.string.please_check_info), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSucceed() {
        Toasty.success(this, getString(R.string.register_succeed), Toast.LENGTH_SHORT).show();
        postDelayFinish(1000);
    }

    @Override
    public void idIsExist() {
        Toasty.error(this, getString(R.string.id_exist), Toasty.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_register)
    public void onRegisterClicked(){
        if (checkInfo()){
            long id = Long.parseLong(userIdText);
            if (rbTeacher.isChecked()) {
                mPresenter.teacherRegister(userName, id, password);
            }else if (rbStudent.isChecked()){
                mPresenter.studentRegister(userName, id, password);
            }else {
                showSelectOccupation();
            }
        }else {
            showInvalidInformation();
        }
    }

    private boolean checkInfo() {
        boolean valid = true;
        userName = etUserName.getText().toString();
        userIdText = etUserId.getText().toString();
        password = etPassword.getText().toString();
        if (StringUtils.isBlank(userName)){
            valid = false;
            layoutUserName.setError(getString(R.string.invalid_user_name));
        }else {
            layoutUserName.setErrorEnabled(false);
        }
        if (StringUtils.isBlank(userIdText)) {
            valid = false;
            layoutUserId.setError(getString(R.string.invalid_user_id));
        } else {
            layoutUserId.setErrorEnabled(false);
        }
        if (StringUtils.isBlank(password)) {
            valid = false;
            layoutPassword.setError(getString(R.string.invalid_password));
        } else {
            layoutPassword.setErrorEnabled(false);
        }

        return valid;
    }
}
