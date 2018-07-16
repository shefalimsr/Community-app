package com.example.pay1.community.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.pay1.community.database.dao.feedDao;
import com.example.pay1.community.database.dao.homeDao;
import com.example.pay1.community.database.dao.updateDao;
import com.example.pay1.community.database.entity.FeedEntity;
import com.example.pay1.community.database.entity.HomEntity;
import com.example.pay1.community.database.entity.UpdateEntity;


@Database(entities = {FeedEntity.class, HomEntity.class, UpdateEntity.class}, version = 1)
public abstract class ApplicationDatabase extends RoomDatabase {

    private static final String DB_NAME = "community-app-database";

    private static ApplicationDatabase instance;

    public static ApplicationDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();

        }
        return instance;
    }

    public abstract feedDao feedDao();
    public abstract homeDao homeDao();
    public abstract updateDao updateDao();
}
