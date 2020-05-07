package com.biginsect.signinmanagement.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.biginsect.mvp.BaseActivity;
import com.biginsect.signinmanagement.R;
import com.biginsect.signinmanagement.app.AppData;
import com.biginsect.signinmanagement.register.RegisterActivity;
import com.biginsect.signinmanagement.student.StudentInfoPageActivity;
import com.biginsect.signinmanagement.teacher.TeacherHomePageActivity;
import com.biginsect.signinmanagement.utils.StringUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
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

    private String userIdText;
    private String password;

    private final static String EXTRA_NAME = "name";

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
        startActivity(new Intent(this, TeacherHomePageActivity.class));
        finish();
    }

    @Override
    public void studentLoginSucceed() {
        Intent intent = new Intent(this, StudentInfoPageActivity.class);
        intent.putExtra(EXTRA_NAME, AppData.INSTANCE.getCurrentStudent().getStudentName());
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailed() {
        Toasty.error(this, getString(R.string.wrong_user_name_or_password), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoUser() {
        Toasty.error(this, getString(R.string.no_that_user), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_login)
    public void onLoginClicked() {
        if (checkNameAndPassword()){
            long id = Long.parseLong(userIdText);
            if (rbTeacher.isChecked()) {
                mPresenter.teacherLogin(id, password);
            }else if (rbStudent.isChecked()){
                mPresenter.studentLogin(id, password);
            }else {
                showSelectOccupation();
            }
        }else {
            showInvalidInformation();
        }
    }

    @OnClick(R.id.btn_register)
    public void onRegisterClicked(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private boolean checkNameAndPassword() {
        boolean valid = true;
        userIdText = etUserName.getText().toString();
        password = etPassword.getText().toString();
        if (StringUtils.isBlank(userIdText)) {
            valid = false;
            layoutUserName.setError(getString(R.string.invalid_user_id));
        } else {
            layoutUserName.setErrorEnabled(false);
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
