package com.example.pay1.community.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.pay1.community.database.entity.HomEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface homeDao {

    @Query("SELECT * FROM homeData")
    List<HomEntity> getAll();

    @Insert
    void insertSingle(HomEntity home);

    @Query("DELETE FROM homeData")
    void deleteAll();
}