package com.example.registerationapp;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EntryDao {
    @Insert
    void insertaEntry(Entry entry);

    @Delete
    void deleteaEntry(Entry entry);

    @Query("SELECT * FROM people ORDER BY id DESC")
    List<Entry> getAllEntry();

    @Query("SELECT id FROM people")
    List<Integer> getAllId();

    @Query("UPDATE people SET name=:name,gender=:gender,email=:email,phonenumber=:contact WHERE id=:id")
    void updateEntry(int id,String name,String gender,String email,String contact);

    @Query("SELECT * FROM people WHERE id=:id")
    Entry getEntryById(int id);
}
