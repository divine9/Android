
package com.fixpocket.Model.CollectionsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("shortTitle")
    @Expose
    private String shortTitle;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("img1")
    @Expose
    private String img1;
    @SerializedName("collectionID")
    @Expose
    private String collectionID;
    @SerializedName("beatID")
    @Expose
    private String beatID;
    @SerializedName("count")
    @Expose
    private Integer count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(String collectionID) {
        this.collectionID = collectionID;
    }

    public String getBeatID() {
        return beatID;
    }

    public void setBeatID(String beatID) {
        this.beatID = beatID;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
