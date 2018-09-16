package com.box.io.ui.login_activity;


import com.box.io.database.AppDatabase;
import com.box.io.rest.NetworkMockHelper;
import com.box.io.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginView> {

    private AppDatabase appDatabase;
    private NetworkMockHelper helper;

    LoginPresenter(AppDatabase appDatabase, NetworkMockHelper helper) {
        this.appDatabase = appDatabase;
        this.helper = helper;
    }

    void login(String email, String password) {
        if (!view.validateEmail())
            return;
        if (!view.validatePass())
            return;
        addDisposable(helper.login(email, password)
                .map(user -> appDatabase.getUserDao().insert(user))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(result -> {
                    if (result > 0) {
                        view.onLoginSuccess();
                    } else {
                        view.onError("Email or password not found");
                    }
                }, throwable -> {
                    view.onError("Email or password not found");
                    throwable.printStackTrace();
                }));
    }


}
