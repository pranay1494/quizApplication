package com.example.faisal.quizassignment.Models;

import java.util.List;

/**
 * Created by anubhav on 23/2/16.
 */
public class Test {
    private int duration;
    private String header;
    private List<Question> questions;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
