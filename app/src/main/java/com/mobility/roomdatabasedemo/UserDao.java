package com.mobility.roomdatabasedemo;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("Select * from User")
    List<User> getAllUser();

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Delete
    void deleteAll(User... user);

}
