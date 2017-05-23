
package com.fixpocket.Model.BeatListModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("subcat_info")
    @Expose
    private SubcatInfo subcatInfo;
    @SerializedName("beats")
    @Expose
    private List<Beat> beats = null;

    public SubcatInfo getSubcatInfo() {
        return subcatInfo;
    }

    public void setSubcatInfo(SubcatInfo subcatInfo) {
        this.subcatInfo = subcatInfo;
    }

    public List<Beat> getBeats() {
        return beats;
    }

    public void setBeats(List<Beat> beats) {
        this.beats = beats;
    }

}
