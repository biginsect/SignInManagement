package com.biginsect.signinmanagement.register;

import com.biginsect.mvp.MvpPresenter;
import com.biginsect.mvp.MvpView;

/**
 * @author biginsect
 * @date 2020/4/20
 */
public interface IRegisterContract {

    interface IView extends MvpView {

        void showSelectOccupation();

        void showInvalidInformation();

        void registerSucceed();

        void idIsExist();
    }

    interface IPresenter extends MvpPresenter<IView> {

        void studentRegister(String userName, long studentId, String password);

        void teacherRegister(String userName, long teacherId, String password);
    }
}
