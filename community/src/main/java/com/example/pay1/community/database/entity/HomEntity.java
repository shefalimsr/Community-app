package com.example.pay1.community.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "homeData")
public class HomEntity {
    @PrimaryKey(autoGenerate = true)
    private int hid;

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name="title")
    private String title;

    @ColumnInfo(name = "visibility")
    private int visibility;

    @ColumnInfo(name="titleUrl")
    private String titleUrl;

    @ColumnInfo(name = "iconUrl")
    private String iconUrl;

    @ColumnInfo(name = "type")
    private int type;

    @ColumnInfo(name = "timestamp")
    private String timestamp;

    @ColumnInfo(name = "resource_representation_type")
    private int resource_representation_type;

    @ColumnInfo(name = "size")
    private int size;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getResource_representation_type() {
        return resource_representation_type;
    }

    public void setResource_representation_type(int resource_representation_type) {
        this.resource_representation_type = resource_representation_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }
}
