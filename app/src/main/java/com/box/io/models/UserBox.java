package com.box.io.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class UserBox {
    @NonNull
    @PrimaryKey
    public String email;
    public Box box;
    public Integer color;
    public boolean name;

    public boolean check() {
        return box != null && color != null;
    }

    @Override
    public String toString() {
        return "UserBoxDao{" +
                "box=" + box +
                ", color=" + color +
                ", name='" + name + '\'' +
                '}';
    }
}
