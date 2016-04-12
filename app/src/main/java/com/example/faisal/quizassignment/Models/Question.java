package com.example.faisal.quizassignment.Models;

import android.widget.RadioButton;

import java.util.List;

/**
 * Created by anubhav on 23/2/16.
 */
public class Question {
    private String imageURL;
    private boolean multiple;
    private List<Option> options;
    private int questionId;
    private String title;


    private boolean read1=false;

    private int option_prev_position;



    public int getOption_prev_position() {
        return option_prev_position;
    }

    public  void setOption_prev_position(int option_prev_position) {
       this.option_prev_position = option_prev_position;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public List<com.example.faisal.quizassignment.Models.Option> getOption() {
        return options;
    }

    public void setOption(List<com.example.faisal.quizassignment.Models.Option> option) {
        options = option;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsRead(boolean read)
    {
        read1=read;
    }
    public boolean getIsRead()
    {
        return read1;
    }



}
