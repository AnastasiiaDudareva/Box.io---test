package com.box.io.utils;

import android.content.Context;
import android.content.Intent;

import com.box.io.ui.login_activity.LoginActivity;
import com.box.io.ui.main_activity.MainActivity;

public class ActionsController {

    public static void openMain(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public static void openAuthorization(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
