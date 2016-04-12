package com.example.faisal.quizassignment.Singleton;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.faisal.quizassignment.Adapters.*;
import com.example.faisal.quizassignment.Async_Tasks.*;
import com.example.faisal.quizassignment.Fragments.*;
import com.example.faisal.quizassignment.Interfaces.*;
import com.example.faisal.quizassignment.Models.*;

/**
 * Created by anubhav on 24/2/16.
 */
public class SingletonData {
    private static Test test;
    private static SingletonData singletonData;
    private static int sizeMove;
    private static int duration;
    private static int value=0;
    private static Bundle bundle;
    private static View view;
    private static boolean counter=true; //Helped in saving option states.

    private SingletonData()
    {

    }
    public synchronized static SingletonData getInstance()
    {
        if(singletonData==null) {
            singletonData = new SingletonData();

        }
            return singletonData;
    }
    public void setField(Test t)
    {
        test=t;
        sizeMove= t.getQuestions().size();
        duration=t.getDuration();
        counter=false;
    }

    public static boolean isCounter() {
        return counter;
    }

    public void setCounter(boolean counter) {
        this.counter = counter;
    }

    public Test getField()
    {
        return test;
    }

    public void updateValueCount()
    {
        --sizeMove;
    }
    public int getValueCount()
    {
        return sizeMove;
    }


    public void updateValue()
    {
        value++;
    }
    public int getValue()
    {
        return value;
    }
    public int resetValue()
    {
        return value=0;
    }

    public int getTimer()
    {
        return duration;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        SingletonData.bundle = bundle;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        SingletonData.view = view;
    }

    public void resetAll()
    {
        singletonData=null;
        test=null;



    }
}
