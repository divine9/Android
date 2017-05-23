
package com.fixpocket.Model.BeatDetailsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("levelID")
    @Expose
    private Integer levelID;
    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("userAccountID")
    @Expose
    private Integer userAccountID;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("UserAvgRating")
    @Expose
    private String userAvgRating;
    @SerializedName("UserTotalReview")
    @Expose
    private Integer userTotalReview;
    @SerializedName("responseTime")
    @Expose
    private String responseTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevelID() {
        return levelID;
    }

    public void setLevelID(Integer levelID) {
        this.levelID = levelID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(Integer userAccountID) {
        this.userAccountID = userAccountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserAvgRating() {
        return userAvgRating;
    }

    public void setUserAvgRating(String userAvgRating) {
        this.userAvgRating = userAvgRating;
    }

    public Integer getUserTotalReview() {
        return userTotalReview;
    }

    public void setUserTotalReview(Integer userTotalReview) {
        this.userTotalReview = userTotalReview;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

}
