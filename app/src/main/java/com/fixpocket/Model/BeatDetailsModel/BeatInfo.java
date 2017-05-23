
package com.fixpocket.Model.BeatDetailsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeatInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("beatTitle")
    @Expose
    private String beatTitle;
    @SerializedName("shortTitle")
    @Expose
    private String shortTitle;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("deliveryTime")
    @Expose
    private Integer deliveryTime;
    @SerializedName("revisions")
    @Expose
    private Integer revisions;
    @SerializedName("totalPrice")
    @Expose
    private String totalPrice;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("categoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("cat")
    @Expose
    private String cat;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("subCategoryID")
    @Expose
    private Integer subCategoryID;
    @SerializedName("subCategory")
    @Expose
    private String subCategory;
    @SerializedName("sub")
    @Expose
    private String sub;
    @SerializedName("extra_fast_days")
    @Expose
    private Integer extraFastDays;
    @SerializedName("extra_revisions")
    @Expose
    private Integer extraRevisions;
    @SerializedName("totFeed")
    @Expose
    private Integer totFeed;
    @SerializedName("avgRating")
    @Expose
    private Double avgRating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getRevisions() {
        return revisions;
    }

    public void setRevisions(Integer revisions) {
        this.revisions = revisions;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(Integer subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Integer getExtraFastDays() {
        return extraFastDays;
    }

    public void setExtraFastDays(Integer extraFastDays) {
        this.extraFastDays = extraFastDays;
    }

    public Integer getExtraRevisions() {
        return extraRevisions;
    }

    public void setExtraRevisions(Integer extraRevisions) {
        this.extraRevisions = extraRevisions;
    }

    public Integer getTotFeed() {
        return totFeed;
    }

    public void setTotFeed(Integer totFeed) {
        this.totFeed = totFeed;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

}
