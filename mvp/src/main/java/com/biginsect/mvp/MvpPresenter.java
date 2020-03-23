package com.biginsect.mvp;

import androidx.annotation.UiThread;

/**
 * @author biginsect
 * @date 2020-03-19
 */
public interface MvpPresenter<V extends MvpView> {

    @UiThread
    void attachedView(V view);

    @UiThread
    void detachView();

    @UiThread
    void destroy();
}
