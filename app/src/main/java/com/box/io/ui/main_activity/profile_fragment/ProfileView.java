package com.box.io.ui.main_activity.profile_fragment;

import com.box.io.models.User;
import com.box.io.models.UserBox;
import com.box.io.ui.base.BaseView;

import java.util.Date;

public interface ProfileView extends BaseView{
    void showUser(User user);
    void showBox(UserBox box);
    void showLastUpdated(Date date);
}
