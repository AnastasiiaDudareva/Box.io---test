package com.box.io.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.box.io.models.UserBox;

import io.reactivex.Single;

@Dao
public interface UserBoxDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(UserBox box);

    @Delete
    void delete(UserBox box);

    @Query("SELECT * FROM UserBox WHERE email IS :email LIMIT 1")
    Single<UserBox> getBox(String email);

}