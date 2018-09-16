package com.box.io.di;

import android.content.Context;

import com.box.io.ui.SplashActivity;
import com.box.io.ui.login_activity.LoginActivity;
import com.box.io.ui.main_activity.MainActivity;
import com.box.io.ui.main_activity.order_box_fragment.OrderBoxFragment;
import com.box.io.ui.main_activity.profile_fragment.ProfileFragment;

import dagger.Component;

@ApplicationScope
@Component(modules = {StorageModule.class, ApplicationModule.class, NetworkModule.class})
public interface AppComponent {

    void injectLoginActivity(LoginActivity loginActivity);

    void injectSplashActivity(SplashActivity splashActivity);

    void injectMainActivity(MainActivity mainActivity);

    void injectOrderBoxFragment(OrderBoxFragment orderBoxFragment);

    void injectProfileFragment(ProfileFragment profileFragment);

    Context getContext();
}
