
package com.fixpocket.Model.BeatListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Beat {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("levelID")
    @Expose
    private String levelID;
    @SerializedName("doc")
    @Expose
    private String doc;
    @SerializedName("beatTitle")
    @Expose
    private String beatTitle;
    @SerializedName("shortTitle")
    @Expose
    private String shortTitle;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("is_featured")
    @Expose
    private String isFeatured;
    @SerializedName("CategoryID")
    @Expose
    private String categoryID;
    @SerializedName("subCategory")
    @Expose
    private String subCategory;
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("userAccountID")
    @Expose
    private String userAccountID;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("totFeed")
    @Expose
    private String totFeed;
    @SerializedName("avgRating")
    @Expose
    private Integer avgRating;
    @SerializedName("is_fav")
    @Expose
    private Integer isFav;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevelID() {
        return levelID;
    }

    public void setLevelID(String levelID) {
        this.levelID = levelID;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getBeatTitle() {
        return beatTitle;
    }

    public void setBeatTitle(String beatTitle) {
        this.beatTitle = beatTitle;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(String isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(String userAccountID) {
        this.userAccountID = userAccountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTotFeed() {
        return totFeed;
    }

    public void setTotFeed(String totFeed) {
        this.totFeed = totFeed;
    }

    public Integer getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Integer avgRating) {
        this.avgRating = avgRating;
    }

    public Integer getIsFav() {
        return isFav;
    }

    public void setIsFav(Integer isFav) {
        this.isFav = isFav;
    }

}
