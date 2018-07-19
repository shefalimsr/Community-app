package com.example.pay1.community.HOME;

public class Home {

    public int id;
    public String title;
    public String titleUrl;
    public String iconUrl;
    public int type;
    public String timestamp;
    public int visibility;
    public int resource_representation_type;
    public int sizeRes;


    public Home(int i,String tit, String titUrl, String icUrl, int typ, String tmstmp,int v,int r)
    {
        visibility=v;
        id=i;
        title=tit;
        titleUrl=titUrl;
        iconUrl=icUrl;
        type=typ;
        timestamp=tmstmp;
        resource_representation_type=r;
    }

    public String getTitle() {
        return title;
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

    public void setTitle(String title) {
        this.title = title;
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

    public int getSizeRes()
    {
        return sizeRes;
    }

    public void setSizeRes(int sizeRes) {
        this.sizeRes = sizeRes;
    }
}
