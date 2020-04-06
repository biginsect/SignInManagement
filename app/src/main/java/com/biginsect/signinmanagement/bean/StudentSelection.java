package com.biginsect.signinmanagement.bean;

/**
 * @author biginsect
 * @date 2020/4/4
 */
public class StudentSelection {

    public StudentSelection(int imageId, String text){
        this.imageId = imageId;
        this.selectionText = text;
    }

    private int imageId;

    private String selectionText;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getSelectionText() {
        return selectionText;
    }

    public void setSelectionText(String selectionText) {
        this.selectionText = selectionText;
    }
}
