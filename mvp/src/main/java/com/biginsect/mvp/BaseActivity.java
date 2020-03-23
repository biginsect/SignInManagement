package com.biginsect.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author biginsect
 * @date 2020-03-19
 */
public abstract class BaseActivity<P extends MvpPresenter>
        extends AppCompatActivity implements MvpView {

    protected P mPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            unbinder = ButterKnife.bind(getActivity());
        }
        if (null == mPresenter) {
            mPresenter = createPresenter();
            mPresenter.attachedView(this);
        }
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter.detachView();
        }
        if (null != unbinder) {
            unbinder.unbind();
        }
    }

    protected BaseActivity getActivity() {
        return this;
    }

    protected abstract int getLayoutId();

    protected abstract P createPresenter();

    protected abstract void initView();

    protected abstract void initData();
}
