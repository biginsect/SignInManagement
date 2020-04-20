package com.biginsect.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author biginsect
 * @date 2020/4/20
 */
public abstract class BaseFragment<P extends MvpPresenter> extends Fragment implements MvpView {

    protected BaseActivity mActivity;
    protected P mPresenter;
    private Unbinder unbinder;

    public BaseFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null){
            mPresenter.attachedView(this);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mActivity = (BaseActivity) getActivity();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mPresenter != null){
            mPresenter.detachView();
            mPresenter.destroy();
        }
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract P createPresenter();
}
