package com.example.pay1.community.update;

public class Update {

    private String title;
    private String titleUrl;
    private String iconUrl;
    private String type;
    private String timestamp;

    public Update(String tit, String titUrl, String icUrl, String typ, String tmstmp)
    {
        title=tit;
        titleUrl=titUrl;
        iconUrl=icUrl;
        type=typ;
        timestamp=tmstmp;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
}
