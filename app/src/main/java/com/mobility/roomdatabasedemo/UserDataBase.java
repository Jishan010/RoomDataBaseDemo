package com.mobility.roomdatabasedemo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {User.class}, version = 1)
public abstract class UserDataBase extends RoomDatabase {


    private static volatile  UserDataBase userDataBase;

    public abstract UserDao getUserDao();

    static UserDataBase getInstance(Context context) {
        if (userDataBase == null) {
            userDataBase = Room.databaseBuilder(context, UserDataBase.class, "UserDataBase").build();
        }
        return userDataBase;
    }

    public void cleanUp() {
        userDataBase = null;
    }
}
