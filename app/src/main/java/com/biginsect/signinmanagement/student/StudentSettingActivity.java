package com.biginsect.signinmanagement.student;

import com.biginsect.mvp.BaseActivity;

/**
 * @author lipeng
 * Created at 2020/4/7 11:38
 */
public class StudentSettingActivity extends BaseActivity<SettingPresenter> implements ISettingContract.IView {

    @Override
    protected int getLayoutId() {
        return 0;
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
}
