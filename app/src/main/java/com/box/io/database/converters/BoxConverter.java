package com.box.io.database.converters;

import android.arch.persistence.room.TypeConverter;

import com.box.io.models.Box;
import com.google.gson.Gson;

public class BoxConverter {
    @TypeConverter
    public static Box fromString(String value) {
        return new Gson().fromJson(value, Box.class);
    }

    @TypeConverter
    public static String fromItem(Box box) {
        Gson gson = new Gson();
        String json = gson.toJson(box);
        return json;
    }
}