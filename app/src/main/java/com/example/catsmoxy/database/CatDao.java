package com.example.catsmoxy.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CatDao {

    @Query("SELECT * FROM catentity")
    List<CatEntity> getAll();

    @Insert
    void insert(CatEntity catEntity);
}
