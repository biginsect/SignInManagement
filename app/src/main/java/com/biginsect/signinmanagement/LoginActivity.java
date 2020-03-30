package com.biginsect.signinmanagement;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.biginsect.mvp.BaseActivity;

/**
 * @author biginsect
 * @date 2020-03-25
 */
public class LoginActivity<P extends ILoginContract.IPresenter> extends BaseActivity<P>
        implements ILoginContract.IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected P createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
