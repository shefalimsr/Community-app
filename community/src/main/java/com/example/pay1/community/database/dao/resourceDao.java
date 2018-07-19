package com.example.pay1.community.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.pay1.community.database.entity.ResourceEntity;

import java.util.List;

/**
 * Created by shefali on 18/07/18.
 */

@Dao
public interface resourceDao {
    @Query("SELECT * FROM resourceData")
    List<ResourceEntity> getAll();

    @Insert
    void insertSingle(ResourceEntity feed);


    @Query("DELETE FROM resourceData")
    void deleteAll();
}
