package com.box.io.ui.main_activity;

import com.box.io.models.User;
import com.box.io.ui.base.BaseView;

public interface MainView extends BaseView {
    void showUserData(User user);
}
