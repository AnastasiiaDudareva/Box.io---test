package com.box.io.rest;

import android.content.Context;
import android.text.format.DateUtils;

import com.box.io.Keys;
import com.box.io.models.AvailBoxWrapper;
import com.box.io.models.User;
import com.box.io.models.UserBox;
import com.box.io.rest.parser.ArrayBoxParser;
import com.box.io.rest.parser.UserBoxParser;
import com.box.io.rest.parser.UserParser;
import com.box.io.utils.UserPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.reactivex.Single;

public class NetworkMockHelper {

    private static final long EXPIRES_RANGE = DateUtils.MINUTE_IN_MILLIS * 30;

    private Context context;

    public NetworkMockHelper(Context context) {
        this.context = context;
    }

    public Single<User> login(String email, String password) {
        UserPreferences.saveData(UserPreferences.EXPIRES_IN, getExpiresIn());
        UserPreferences.saveData(UserPreferences.LAST_UPDATED, System.currentTimeMillis());
        UserPreferences.saveString(UserPreferences.EMAIL, email);
        return loadUserData();
    }

    public Single<Boolean> checkToken() {
        return Single.create(emitter -> emitter.onSuccess(UserPreferences.checkExpiresIn()));
    }

    private long getExpiresIn() {
        return System.currentTimeMillis() + EXPIRES_RANGE;
    }

    public Single<User> loadUserData() {
        return Single.create(emitter -> {
            User user = new User();
            user.email = UserPreferences.getString(Keys.EMAIL);
            emitter.onSuccess(new UserParser().parse(user.fillTestGenerateJson()));
        });
    }

    public Single<List<AvailBoxWrapper>> getAvailBoxes() {
        return Single.create(emitter -> emitter.onSuccess(new ArrayBoxParser()
                .parse(parseFileToString("avail_box.json"))));
    }

    public String parseFileToString(String filename) {
        try {
            InputStream stream = context.getAssets().open(filename);
            int size = stream.available();

            byte[] bytes = new byte[size];
            stream.read(bytes);
            stream.close();
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Single<UserBox> orderBox(UserBox box) {
        return Single.create(emitter -> {
            //send data to server
            //...

            //result
            box.email = UserPreferences.getString(UserPreferences.EMAIL);
            JSONObject boxJson = new JSONObject();
            try {
                boxJson.put(Keys.HEIGHT, box.box.height);
                boxJson.put(Keys.WIDTH, box.box.width);
                boxJson.put(Keys.DEPTH, box.box.depth);
                boxJson.put(Keys.COLOR, box.color);
                boxJson.put(Keys.NAME, box.name);
                boxJson.put(Keys.EMAIL, box.email);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            UserPreferences.saveData(UserPreferences.LAST_UPDATED, System.currentTimeMillis());
            emitter.onSuccess(new UserBoxParser().parse(boxJson.toString()));
        });
    }

    public void logout() {
        UserPreferences.saveString(UserPreferences.EMAIL, "");
        UserPreferences.saveData(UserPreferences.LAST_UPDATED, -1);
        UserPreferences.saveData(UserPreferences.EXPIRES_IN, -1);
        UserPreferences.saveData(UserPreferences.MENU_SELECTED, -1);
    }

    public Single<User> updateInfo(String userInfo) {
        return Single.create(emitter -> {
            UserPreferences.saveData(UserPreferences.LAST_UPDATED, System.currentTimeMillis());
            User user = new User();
            user.userInfo = userInfo;
            user.email = UserPreferences.getString(Keys.EMAIL);
            emitter.onSuccess(new UserParser().parse(user.fillTestGenerateJson()));
        });
    }
}