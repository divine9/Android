
package com.fixpocket.Model.BeatDetailsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerLang {

    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("level")
    @Expose
    private String level;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
