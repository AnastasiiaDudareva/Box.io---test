package com.box.io.ui.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.box.io.R;
import com.box.io.databinding.ActivityBaseBinding;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragmentActivity extends AppCompatActivity implements BaseView {
    private ActivityBaseBinding binding;
    private Toolbar toolBar;
    protected CompositeDisposable destroyDisposable = new CompositeDisposable();

    public Toolbar getToolbar() {
        return toolBar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_base, null, false);
        setContentView(binding.getRoot());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyDisposable.dispose();
    }

    protected <T extends ViewDataBinding> T putContentView(@LayoutRes int resId) {
        return putContentView(resId, true);
    }

    protected <T extends ViewDataBinding> T putContentView(@LayoutRes int resId, boolean addToolbar) {
        T contentBinding = DataBindingUtil.inflate(getLayoutInflater(), resId, binding.container, true);
        if (addToolbar) {
            binding.container.addView(getLayoutInflater().inflate(R.layout.tool_bar, null, true), 0);
            toolBar = binding.container.findViewById(R.id.tool_bar);
        } else {
            toolBar = binding.container.findViewById(R.id.tool_bar);
        }
        onPrepareToolBar(toolBar);
        return contentBinding;
    }

    protected void onPrepareToolBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void setTitle(final CharSequence titleText) {
        if (toolBar != null) {
            toolBar.setTitle(titleText);
        }
    }

    @Override
    public void setTitle(final int titleId) {
        if (toolBar != null) {
            toolBar.setTitle(titleId);
        }
    }


    @Override
    public void onError(String errorMessage) {
        runOnUiThread(() -> Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show());
    }

    @Override
    public void onSuccess(String success) {
        runOnUiThread(() -> Toast.makeText(this, success, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void hideKeyboard(EditText edit) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
    }

    @Override
    public void hideKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
