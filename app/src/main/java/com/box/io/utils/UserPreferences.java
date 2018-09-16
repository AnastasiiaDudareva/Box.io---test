package com.box.io.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Hashtable;

/**
 * Created by dudareva on 03.01.18.
 */

public class UserPreferences {
    private static final String SETTINGS = "user_settings";
    public static final String EXPIRES_IN = "expires_in";
    public static final String LAST_UPDATED = "last_updated";
    public static final String MENU_SELECTED = "menu_selected";
    public static final String EMAIL = "email";

    private static SharedPreferences sharedPrefs;
    private static Hashtable<String, Long> dataParams = new Hashtable<>();
    private static Hashtable<String, String> stringParams = new Hashtable<>();

    private static final String[] DATA_PARAMS = new String[]{
            EXPIRES_IN, MENU_SELECTED,
    };

    private static final String[] STRING_PARAMS = new String[]{
            EMAIL
    };

    public static void initData(Context context) {
        dataParams.clear();
        dataParams = new Hashtable<>(DATA_PARAMS.length);
        stringParams.clear();
        stringParams = new Hashtable<>(STRING_PARAMS.length);
        sharedPrefs = context.getSharedPreferences(
                SETTINGS,
                Application.MODE_PRIVATE);
        for (int i = 0; i < DATA_PARAMS.length; i++) {
            dataParams.put(DATA_PARAMS[i],
                    sharedPrefs.getLong(DATA_PARAMS[i], -1));
        }
        for (int i = 0; i < STRING_PARAMS.length; i++) {
            stringParams.put(STRING_PARAMS[i],
                    sharedPrefs.getString(STRING_PARAMS[i], ""));
        }
    }

    public static void saveData(String settingItemName, long settingItemValue) {
        sharedPrefs.edit().putLong(settingItemName, settingItemValue)
                .commit();
        dataParams.put(settingItemName, settingItemValue);
    }

    public static long getData(String settingItemName) {
        if (dataParams != null && dataParams.get(settingItemName) != null) {
            return dataParams.get(settingItemName);
        }
        return sharedPrefs.getLong(settingItemName, -1);
    }

    public static void saveString(String settingItemName, String settingItemValue) {
        sharedPrefs.edit().putString(settingItemName, settingItemValue)
                .commit();
        stringParams.put(settingItemName, settingItemValue);
    }

    public static String getString(String settingItemName) {
        if (stringParams != null && stringParams.get(settingItemName) != null) {
            return stringParams.get(settingItemName);
        }
        return sharedPrefs.getString(settingItemName, "");
    }

    public static boolean checkExpiresIn() {
        long expireIn = getData(EXPIRES_IN);
        return expireIn > System.currentTimeMillis();
    }

}
