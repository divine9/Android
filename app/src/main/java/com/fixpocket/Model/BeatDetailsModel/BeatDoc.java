
package com.fixpocket.Model.BeatDetailsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeatDoc {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("docTitle")
    @Expose
    private String docTitle;
    @SerializedName("docType")
    @Expose
    private String docType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

}
