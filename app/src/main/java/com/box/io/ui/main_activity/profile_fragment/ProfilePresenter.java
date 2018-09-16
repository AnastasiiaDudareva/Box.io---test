package com.box.io.ui.main_activity.profile_fragment;

import com.box.io.bus.BusProvider;
import com.box.io.bus.LogoutEvent;
import com.box.io.database.AppDatabase;
import com.box.io.rest.NetworkMockHelper;
import com.box.io.ui.base.BasePresenter;
import com.box.io.utils.UserPreferences;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter extends BasePresenter<ProfileView> {

    private AppDatabase appDatabase;
    private NetworkMockHelper networkMockHelper;

    public ProfilePresenter(AppDatabase appDatabase, NetworkMockHelper networkMockHelper) {
        this.appDatabase = appDatabase;
        this.networkMockHelper = networkMockHelper;
    }

    @Override
    public void attachToView(ProfileView attachedView) {
        super.attachToView(attachedView);
        loadData();
    }

    private void loadData() {
        addDisposable(appDatabase.getUserDao()
                .getUser(UserPreferences.getString(UserPreferences.EMAIL))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(user -> view.showUser(user)));
        addDisposable(appDatabase.getBoxDao()
                .getBox(UserPreferences.getString(UserPreferences.EMAIL))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(box -> view.showBox(box),
                        Throwable::printStackTrace));

        view.showLastUpdated(getLastUpdated());
    }

    private Date getLastUpdated() {
        return new Date(UserPreferences.getData(UserPreferences.LAST_UPDATED));
    }

    public void logout() {
        networkMockHelper.logout();
        BusProvider.post(new LogoutEvent());
    }

    public void updateInfo(String userInfo) {
        addDisposable(networkMockHelper.updateInfo(userInfo)
                .map(user -> {
                    appDatabase.getUserDao().insert(user);
                    return user;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(user -> {
                            view.showLastUpdated(getLastUpdated());
                            view.showUser(user);
                        }, Throwable::printStackTrace));
    }
}
