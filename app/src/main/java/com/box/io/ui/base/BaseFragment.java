package com.box.io.ui.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;


public class BaseFragment extends Fragment implements BaseView {

    public void setTitle(int resId) {
        FragmentActivity activity = getActivity();
        activity.setTitle(resId);
    }

    public void setTitle(String title) {
        FragmentActivity activity = getActivity();
        activity.setTitle(title);
    }

    public Toolbar getToolbar() {
        if (getActivity() instanceof BaseFragmentActivity
                && ((BaseFragmentActivity) getActivity()).getToolbar() != null) {
            return ((BaseFragmentActivity) getActivity()).getToolbar();
        }
        return null;
    }

    @Override
    public void onError(String errorMessage) {
        ((BaseFragmentActivity) getActivity()).onError(errorMessage);
    }

    @Override
    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow()
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void hideKeyboard(EditText edit) {
        if (getActivity() instanceof BaseFragmentActivity
                && ((BaseFragmentActivity) getActivity()).getToolbar() != null) {
            ((BaseFragmentActivity) getActivity()).hideKeyboard(edit);
        }
    }

    @Override
    public void hideKeyboard() {
        if (getActivity() instanceof BaseFragmentActivity
                && ((BaseFragmentActivity) getActivity()).getToolbar() != null) {
            ((BaseFragmentActivity) getActivity()).hideKeyboard();
        }
    }

    @Override
    public void onSuccess(String success) {
        ((BaseFragmentActivity) getActivity()).onSuccess(success);
    }

    @Nullable
    @Override
    public Context getContext() {
        return ((BaseFragmentActivity) getActivity()).getContext();
    }

}
