package com.box.io;

import android.app.Application;

import com.box.io.di.AppComponent;
import com.box.io.di.ApplicationModule;
import com.box.io.di.DaggerAppComponent;
import com.box.io.utils.UserPreferences;

public class BoxIo extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        UserPreferences.initData(this);
    }

    public static AppComponent getComponent() {
        return component;
    }
}
