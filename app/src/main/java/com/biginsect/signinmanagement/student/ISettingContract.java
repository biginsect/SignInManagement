package com.biginsect.signinmanagement.student;

import com.biginsect.mvp.MvpPresenter;
import com.biginsect.mvp.MvpView;

/**
 * @author lipeng
 * Created at 2020/4/7 11:52
 */
public interface ISettingContract {

    interface IView extends MvpView {

        void studentNotExist();

        void updateSuccess();
    }

    interface IPresenter extends MvpPresenter<IView> {

        void updatePassword(long studentId, String newPassword);
    }
}
