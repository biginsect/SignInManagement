package com.biginsect.mvp;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.LayoutRes;
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

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            unbinder = ButterKnife.bind(getActivity());
        }
        mPresenter = createPresenter();
        if (mPresenter != null) {
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

    protected void postDelayFinish(int time){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, time);
    }

    protected BaseActivity getActivity() {
        return this;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract P createPresenter();

    protected abstract void initView();

    protected abstract void initData();
}
