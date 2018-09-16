package com.box.io.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.box.io.models.User;

import io.reactivex.Single;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User userItem);

    @Delete
    void delete(User userItem);

    @Query("SELECT * FROM User WHERE email IS :email LIMIT 1")
    Single<User> getUser(String email);

}