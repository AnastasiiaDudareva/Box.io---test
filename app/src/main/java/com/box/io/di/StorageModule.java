package com.box.io.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.box.io.database.AppDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {
    private static final String DB_NAME = "box-io-database";

    @Provides
    @ApplicationScope
    AppDatabase provideDataBase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

}