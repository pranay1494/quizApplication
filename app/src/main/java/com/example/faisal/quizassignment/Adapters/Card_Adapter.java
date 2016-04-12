package com.example.faisal.quizassignment.Adapters;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.faisal.quizassignment.Adapters.*;
import com.example.faisal.quizassignment.Async_Tasks.*;
import com.example.faisal.quizassignment.Fragments.*;
import com.example.faisal.quizassignment.Interfaces.*;
import com.example.faisal.quizassignment.Models.*;
import com.example.faisal.quizassignment.Singleton.*;

/**
 * Created by anubhav on 18/2/16.
 */
public class Card_Adapter extends FragmentStatePagerAdapter {
    private String url="https://connectdevhiappt.hixapi.com/quiz-services/rest/fetch_questions";
//    private String jsonObject;
    private Test getTest;
   // private int category_id=1;
//    private String[] Question={"Spring Theorum?","Find Normal?","Specify Newton's 2nd Law?","Newton's Third Law"};
    public Card_Adapter(FragmentManager fm) {

        super(fm);


    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        SingletonData.getInstance().updateValueCount();
    }

    @Override
    public Fragment getItem(int position) {

Fragment fragment=new Layout_View();

        Bundle bundle=new Bundle();
        if(SingletonData.getInstance().getBundle()!=null)
        {

            Bundle b=SingletonData.getInstance().getBundle();

            bundle.putInt("position",b.getInt("Saved"));
            SingletonData.getInstance().setBundle(null);

        }
        else
        {
            bundle.putInt("position", SingletonData.getInstance().getField().getQuestions().get(position).getQuestionId());

        }




        fragment.setArguments(bundle);



        return fragment;
    }

    @Override
    public int getCount() {
//        try {
//            AsyncHandler asyncHandler=new AsyncHandler(url,jsonObject);
//            asyncHandler.execute();
//           getTest= asyncHandler.returnJsonresult();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


            return SingletonData.getInstance().getValueCount();


    }

    @Override
    public Parcelable saveState() {

        return super.saveState();

    }
}
