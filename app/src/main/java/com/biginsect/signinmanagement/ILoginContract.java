package com.biginsect.signinmanagement;

import com.biginsect.mvp.MvpPresenter;
import com.biginsect.mvp.MvpView;

/**
 * @author biginsect
 * @date 2020-03-26
 */
public class ILoginContract {

    interface IView extends MvpView {

    }

    interface IPresenter extends MvpPresenter<IView> {

    }
}
