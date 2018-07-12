package com.example.pay1.community.api.response;



import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedResource {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("small_icon")
    @Expose
    private List<SmallIcon> smallIcon = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("feed_type")
    @Expose
    private Integer feedType;
    @SerializedName("resource_representation_type")
    @Expose
    private Integer resourceRepresentationType;
    @SerializedName("res")
    @Expose
    private List<Resource> res = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<SmallIcon> getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(List<SmallIcon> smallIcon) {
        this.smallIcon = smallIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Integer getFeedType() {
        return feedType;
    }

    public void setFeedType(Integer feedType) {
        this.feedType = feedType;
    }

    public Integer getResourceRepresentationType() {
        return resourceRepresentationType;
    }

    public void setResourceRepresentationType(Integer resourceRepresentationType) {
        this.resourceRepresentationType = resourceRepresentationType;
    }

    public List<Resource> getRes() {
        return res;
    }

    public void setRes(List<Resource> res) {
        this.res = res;
    }

}
