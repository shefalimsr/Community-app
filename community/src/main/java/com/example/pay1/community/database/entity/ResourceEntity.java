package com.example.pay1.community.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by shefali on 18/07/18.
 */
@Entity(tableName = "resourceData")
public class ResourceEntity {

    @PrimaryKey(autoGenerate = true)
    private int rid;

    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name = "resource_representation_type")
    private int resource_representation_type;

    @ColumnInfo(name = "res_id")
    private int res_id;

    @ColumnInfo(name = "res_type")
    private int res_type;

    @ColumnInfo(name = "res_url")
    private String res_url;


    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResource_representation_type() {
        return resource_representation_type;
    }

    public void setResource_representation_type(int resource_representation_type) {
        this.resource_representation_type = resource_representation_type;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    public int getRes_type() {
        return res_type;
    }

    public void setRes_type(int res_type) {
        this.res_type = res_type;
    }

    public String getRes_url() {
        return res_url;
    }

    public void setRes_url(String res_url) {
        this.res_url = res_url;
    }
}
