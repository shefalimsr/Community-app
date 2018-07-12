package com.example.pay1.community.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.pay1.community.database.entity.UpdateEntity;

import java.util.List;

@Dao
public interface updateDao {

    @Query("SELECT * FROM updateData")
    List<UpdateEntity> getAll();

    @Insert
    void insertSingle(UpdateEntity update);

    @Query("DELETE FROM updateData")
    void deleteAll();
}