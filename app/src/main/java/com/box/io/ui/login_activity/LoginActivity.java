package com.box.io.ui.login_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.box.io.BoxIo;
import com.box.io.R;
import com.box.io.database.AppDatabase;
import com.box.io.databinding.ActivityAuthBinding;
import com.box.io.rest.NetworkMockHelper;
import com.box.io.ui.base.BaseFragmentActivity;
import com.box.io.utils.ActionsController;
import com.box.io.utils.Validator;

import javax.inject.Inject;

public class LoginActivity extends BaseFragmentActivity implements LoginView {

    private ActivityAuthBinding binding;
    private LoginPresenter presenter;
    @Inject
    public AppDatabase database;
    @Inject
    public NetworkMockHelper helper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BoxIo.getComponent().injectLoginActivity(this);
        binding = putContentView(R.layout.activity_auth, false);
        presenter = new LoginPresenter(database, helper);
        presenter.attachToView(this);
        binding.btLogin.setOnClickListener(view ->
                presenter.login(binding.etEmail.getEditableText().toString(),
                        binding.etPass.getEditableText().toString()));
    }

    @Override
    public void onLoginSuccess() {
        ActionsController.openMain(this);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachFromView();
    }

    @Override
    public boolean validateEmail() {
        String email = binding.etEmail.getText().toString().trim();
        if (email.isEmpty() || !Validator.isValidEmail(email)) {
            binding.inputEmail.setError(getString(R.string.err_msg_email));
            binding.etEmail.setError("");
            requestFocus(binding.etEmail);
            return false;
        } else {
            binding.inputEmail.setErrorEnabled(false);
        }
        return true;
    }

    @Override
    public boolean validatePass() {
        String pass = binding.etPass.getText().toString().trim();
        if (pass.isEmpty() || !Validator.isValidPassword(pass)) {
            binding.inputPass.setError(getString(R.string.err_msg_pass));
            binding.etPass.setError("");
            requestFocus(binding.etPass);
            return false;
        } else {
            binding.inputPass.setErrorEnabled(false);
        }
        return true;
    }

    @Override
    protected void onPrepareToolBar(Toolbar toolbar) {
        super.onPrepareToolBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }
}
