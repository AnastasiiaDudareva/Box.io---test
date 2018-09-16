package com.box.io.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.box.io.Keys;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class User {

    @NonNull
    @PrimaryKey
    public String email;
    public String name;
    public String userInfo;

    @Override
    public String toString() {
        return "User{" +
                " email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", userInfo='" + userInfo + '\'' +
                '}';
    }

    public String fillTestGenerateJson() {
        JSONObject object = new JSONObject();
        try {
            object.put(Keys.NAME, "New User");
            object.put(Keys.EMAIL, email);
            object.put(Keys.USER_INFO, userInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public int hasInfo() {
        return TextUtils.isEmpty(userInfo)? View.GONE:View.VISIBLE;
    }

}
