package com.box.io.ui.base;

import android.content.Context;
import android.view.View;
import android.widget.EditText;


public interface BaseView {

    void onError(String errorMessage);

    void requestFocus(View view);

    void hideKeyboard(EditText edit);

    void hideKeyboard();

    void onSuccess(String success);

    Context getContext();
}
