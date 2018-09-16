package com.box.io.ui.main_activity;

import com.box.io.Keys;
import com.box.io.database.AppDatabase;
import com.box.io.ui.base.BasePresenter;
import com.box.io.utils.UserPreferences;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainView> {

    public AppDatabase appDatabase;

    public MainPresenter(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public void attachToView(MainView attachedView) {
        super.attachToView(attachedView);
        loadUserData();
    }

    private void loadUserData() {
        addDisposable(appDatabase.getUserDao()
                .getUser(UserPreferences.getString(Keys.EMAIL))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(user -> view.showUserData(user)));
    }
}
