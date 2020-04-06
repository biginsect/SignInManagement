package com.biginsect.signinmanagement.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.biginsect.mvp.BaseActivity;
import com.biginsect.signinmanagement.MainActivity;
import com.biginsect.signinmanagement.R;
import com.biginsect.signinmanagement.student.StudentInfoPageActivity;
import com.biginsect.signinmanagement.utils.StringUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * @author biginsect
 * @date 2020-03-25
 */
public class LoginActivity extends BaseActivity<LoginPresenter>
        implements ILoginContract.IView {

    @BindView(R.id.et_user_name)
    TextInputEditText etUserName;
    @BindView(R.id.layout_user_name)
    TextInputLayout layoutUserName;
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
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private String userName;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showInvalidInformation() {
        Toasty.error(this, getText(R.string.invalid_information), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSelectOccupation() {
        Toasty.info(this, getString(R.string.select_occupation), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void teacherLoginSucceed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void studentLoginSucceed() {
        startActivity(new Intent(this, StudentInfoPageActivity.class));
        finish();
    }

    @Override
    public void loginFailed() {
        Toasty.error(this, getString(R.string.wrong_user_name_or_password), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSucceed() {
        Toasty.success(this, getString(R.string.register_succeed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoUser() {
        Toasty.error(this, getString(R.string.no_that_user), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_login)
    public void onLoginClicked() {
        if (checkNameAndPassword()){
            if (rbTeacher.isChecked()) {
                mPresenter.teacherLogin(userName, password);
            }else if (rbStudent.isChecked()){
                mPresenter.studentLogin(userName, password);
            }else {
                showSelectOccupation();
            }
        }else {
            showInvalidInformation();
        }
    }

    @OnClick(R.id.btn_register)
    public void onRegisterClicked(){
        if (checkNameAndPassword()){
            if (rbTeacher.isChecked()) {
                mPresenter.teacherRegister(userName, password);
            }else if (rbStudent.isChecked()){
                mPresenter.studentRegister(userName, password);
            }else {
                showSelectOccupation();
            }
        }else {
            showInvalidInformation();
        }
    }

    private boolean checkNameAndPassword() {
        boolean valid = true;
        userName = etUserName.getText().toString();
        password = etPassword.getText().toString();
        if (StringUtils.isBlank(userName)) {
            valid = false;
            layoutUserName.setError(getString(R.string.invalid_user_name));
        } else {
            layoutUserName.setErrorEnabled(false);
        }
        if (StringUtils.isBlank(password)) {
            valid = false;
            layoutPassword.setError(getString(R.string.invalid_user_name));
        } else {
            layoutPassword.setErrorEnabled(false);
        }

        return valid;
    }
}
