package com.biginsect.mvp;

import androidx.annotation.UiThread;

import java.lang.ref.WeakReference;

/**
 * @author biginsect
 * @date 2020-03-19
 */
public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> viewRef;

    @Override
    public void attachedView(V view) {
        viewRef = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        viewRef.clear();
        viewRef = null;
    }

    @Override
    public void destroy() {

    }

    @UiThread
    protected V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    protected boolean isAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    protected void ifViewAttached(ViewAction<V> action) {
        V view = viewRef == null ? null : viewRef.get();
        if (null != view) {
            action.run(view);
        }
    }

    public interface ViewAction<V> {
        void run(V v);
    }
}
