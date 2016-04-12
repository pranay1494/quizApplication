package com.example.faisal.quizassignment.Models;

import android.widget.RadioButton;

/**
 * Created by anubhav on 23/2/16.
 */
public class Option {
    private String imageUrl;
    private boolean markedForReview;
    private int optionId;
    private boolean selected;
    private String title;

//    private RadioButton radioButton;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

//    public RadioButton getRadioButton() {
//        return radioButton;
//    }
//
//    public void setRadioButton(RadioButton radioButton) {
//        this.radioButton = radioButton;
//    }

    public Boolean getMarkedForReview() {
        return markedForReview;
    }

    public void setMarkedForReview(boolean markedForReview) {
        this.markedForReview = markedForReview;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
