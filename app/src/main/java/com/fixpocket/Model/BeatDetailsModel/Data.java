
package com.fixpocket.Model.BeatDetailsModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("BeatInfo")
    @Expose
    private BeatInfo beatInfo;
    @SerializedName("BeatDocs")
    @Expose
    private List<BeatDoc> beatDocs = null;
    @SerializedName("Reviews")
    @Expose
    private List<Review> reviews = null;
    @SerializedName("SellerInfo")
    @Expose
    private SellerInfo sellerInfo;
    @SerializedName("SellerLang")
    @Expose
    private List<SellerLang> sellerLang = null;

    public BeatInfo getBeatInfo() {
        return beatInfo;
    }

    public void setBeatInfo(BeatInfo beatInfo) {
        this.beatInfo = beatInfo;
    }

    public List<BeatDoc> getBeatDocs() {
        return beatDocs;
    }

    public void setBeatDocs(List<BeatDoc> beatDocs) {
        this.beatDocs = beatDocs;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public SellerInfo getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(SellerInfo sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public List<SellerLang> getSellerLang() {
        return sellerLang;
    }

    public void setSellerLang(List<SellerLang> sellerLang) {
        this.sellerLang = sellerLang;
    }

}
