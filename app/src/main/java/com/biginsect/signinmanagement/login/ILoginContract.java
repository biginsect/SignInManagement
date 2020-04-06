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

        void registerSucceed();

        void showNoUser();
    }

    interface IPresenter extends MvpPresenter<IView> {

        void teacherLogin(String userName, String password);

        void studentLogin(String userName, String password);

        void studentRegister(String userName, String password);

        void teacherRegister(String userName, String password);
    }
}
