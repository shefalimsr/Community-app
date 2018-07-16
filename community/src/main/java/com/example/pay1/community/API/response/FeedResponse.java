package com.example.pay1.community.API.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedResponse implements Parcelable{

    @SerializedName("resource_representation_type")
    public Integer resource_representation_type;

    @SerializedName("small_icon")
    public List<SmallIcon> small_icon;

    @SerializedName("res")
    public List<Res> res = null;

    @SerializedName("visibility")
    public Integer visibility;


    @SerializedName("id")
    public Integer id;

    @SerializedName("title")
    public String title;

    @SerializedName("feed_type")
    public Integer feed_type;

    protected FeedResponse(Parcel in) {
        if (in.readByte() == 0) {
            resource_representation_type = null;
        } else {
            resource_representation_type = in.readInt();
        }
        if (in.readByte() == 0) {
            visibility = null;
        } else {
            visibility = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        if (in.readByte() == 0) {
            feed_type = null;
        } else {
            feed_type = in.readInt();
        }
    }

    public static final Creator<FeedResponse> CREATOR = new Creator<FeedResponse>() {
        @Override
        public FeedResponse createFromParcel(Parcel in) {
            return new FeedResponse(in);
        }

        @Override
        public FeedResponse[] newArray(int size) {
            return new FeedResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (resource_representation_type == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(resource_representation_type);
        }
        if (visibility == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(visibility);
        }
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(title);
        if (feed_type == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(feed_type);
        }
    }

    public class SmallIcon {

        @SerializedName("res_type")
        public Integer res_type;

        @SerializedName("res_id")
        public Integer res_id;

        @SerializedName("res_url")
        public String res_url;

        public Integer getRes_type() {
            return res_type;
        }

        public String getRes_url() {
            return res_url;
        }

        public Integer getRes_id() {
            return res_id;
        }
    }

    public class Res {

        @SerializedName("res_type")
        public Integer res_type;

        @SerializedName("res_id")
        public Integer res_id;

        @SerializedName("res_url")
        public String res_url;

        public Integer getRes_id()
        {
            return res_id;
        }

        public String getRes_url() {
            return res_url;
        }

        public Integer getRes_type() {
            return res_type;
        }
    }

}