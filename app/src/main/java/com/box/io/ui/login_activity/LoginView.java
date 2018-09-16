package com.box.io.ui.login_activity;


import com.box.io.ui.base.BaseView;

public interface LoginView extends BaseView {

    void onLoginSuccess();

    boolean validateEmail();

    boolean validatePass();
}
