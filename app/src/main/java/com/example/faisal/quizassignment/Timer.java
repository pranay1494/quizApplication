package com.example.faisal.quizassignment;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by anubhav on 21/2/16.
 */
public class Timer extends TextView {
Context context;

    public Timer(Context context) {
        super(context);
        this.context=context;
        reset();
    }
    public void reset()
    {
        setText("--:--");
        setTextColor(Color.WHITE);
    }
    public void setData(long millisfuture, long millisinterval)
    {
        setText("--:--");
        setTextColor(Color.WHITE);
        CountDownTimer countDownTimer=new CountDownTimer(millisfuture,millisinterval) {
            @Override
            public void onTick(long millisUntilFinished) {

                setText("Remaining: "+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

            }
        };
    }

}