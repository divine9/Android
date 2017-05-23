package com.fixpocket.Model;

/**
 * Created by divine on 27/4/17.
 */

public class LandPageModel {

    String Title;
    String SubTitle;
    int image;

    public LandPageModel(String Title,
                         String SubTitle,
                         int image) {

        this.Title = Title;
        this.SubTitle = SubTitle;
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
