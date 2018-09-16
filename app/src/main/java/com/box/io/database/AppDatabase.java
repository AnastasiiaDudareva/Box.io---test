package com.box.io.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.box.io.database.converters.BoxConverter;
import com.box.io.database.dao.UserBoxDao;
import com.box.io.database.dao.UserDao;
import com.box.io.models.User;
import com.box.io.models.UserBox;


@Database(entities = {User.class, UserBox.class},
        version = 1,
        exportSchema = false)
@TypeConverters({BoxConverter.class})

public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();

    public abstract UserBoxDao getBoxDao();

}
