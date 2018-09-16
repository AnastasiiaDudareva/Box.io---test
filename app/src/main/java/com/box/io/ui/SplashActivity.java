package com.box.io.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.box.io.BoxIo;
import com.box.io.rest.NetworkMockHelper;
import com.box.io.utils.ActionsController;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    @Inject
    public NetworkMockHelper helper;
    private Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BoxIo.getComponent().injectSplashActivity(this);
        disposable = helper.checkToken().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(valid -> {
                            if (valid) {
                                ActionsController.openMain(this);
                            } else {
                                ActionsController.openAuthorization(this);
                            }
                            finish();
                        },
                        throwable -> {
                            ActionsController.openAuthorization(this);
                            finish();
                        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
