package com.example.pay1.community.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "updateData")
public class UpdateEntity {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name="title")
    private String title;

    @ColumnInfo(name="titleUrl")
    private String titleUrl;

    @ColumnInfo(name = "iconUrl")
    private String iconUrl;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "timestamp")
    private String timestamp;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
