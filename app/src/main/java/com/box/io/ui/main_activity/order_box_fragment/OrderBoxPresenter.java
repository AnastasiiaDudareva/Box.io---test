package com.box.io.ui.main_activity.order_box_fragment;

import com.box.io.database.AppDatabase;
import com.box.io.models.UserBox;
import com.box.io.rest.NetworkMockHelper;
import com.box.io.ui.base.BasePresenter;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderBoxPresenter extends BasePresenter<OrderBoxView> {

    private NetworkMockHelper networkMockHelper;
    private AppDatabase appDatabase;

    public OrderBoxPresenter(NetworkMockHelper networkMockHelper, AppDatabase appDatabase) {
        this.networkMockHelper = networkMockHelper;
        this.appDatabase = appDatabase;
    }

    @Override
    public void attachToView(OrderBoxView attachedView) {
        super.attachToView(attachedView);
        loadAvailBoxes();
    }

    private void loadAvailBoxes() {
        addDisposable(networkMockHelper.getAvailBoxes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(list -> view.showAvailBoxes(list)));
    }

    public void orderPressed() {
        UserBox order = new UserBox();
        order.box = view.getSelectedBox();
        order.color = view.getSelectedColor();
        order.name = view.printName();
        if (order.check()) {
                addDisposable(networkMockHelper.orderBox(order)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(userBox -> {
                            if (userBox != null)
                                saveLocal(userBox);
                        }));
        } else {
            view.onError("Some fields are not filled");
        }
    }

    private void saveLocal(UserBox created) {
        addDisposable(Completable.fromAction(() -> appDatabase.getBoxDao().insert(created))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(() -> view.onSuccess("Success")));
    }


}
