package com.example.pay1.community.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.pay1.community.database.entity.FeedEntity;

import java.util.List;

@Dao
public interface feedDao {

    @Query("SELECT * FROM feedData")
    List<FeedEntity> getAll();

    @Insert
    void insertSingle(FeedEntity feed);

    @Query("DELETE FROM feedData")
    void deleteAll();
}
