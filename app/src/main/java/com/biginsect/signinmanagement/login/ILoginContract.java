package com.biginsect.signinmanagement.login;

import com.biginsect.mvp.MvpPresenter;
import com.biginsect.mvp.MvpView;

/**
 * @author biginsect
 * @date 2020-03-26
 */
public interface ILoginContract {

    interface IView extends MvpView {

        void showInvalidInformation();

        void showSelectOccupation();

        void loginFailed();

        void teacherLoginSucceed();

        void studentLoginSucceed();

        void showNoUser();
    }

    interface IPresenter extends MvpPresenter<IView> {

        void teacherLogin(long id, String password);

        void studentLogin(long id, String password);
    }
}
