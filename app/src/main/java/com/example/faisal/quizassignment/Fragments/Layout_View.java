package com.example.faisal.quizassignment.Fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faisal.quizassignment.R;

import com.example.faisal.quizassignment.Adapters.*;
import com.example.faisal.quizassignment.Async_Tasks.*;
import com.example.faisal.quizassignment.Fragments.*;
import com.example.faisal.quizassignment.Interfaces.*;
import com.example.faisal.quizassignment.Models.*;
import com.example.faisal.quizassignment.Singleton.*;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;


public class Layout_View extends android.support.v4.app.Fragment {
    private String url="https://connectdevhiappt.hixapi.com/quiz-services/rest/fetch_questions";
    private Test getTest;
    private int category_id=1;
    private Bundle bundle;
    private JSONObject jsonObject;
protected RecyclerView recyclerView;
private TextView question_textview;
    private FloatingActionButton floatingActionButton;
    private ImageView question_imageview;
//    private int[] resources={R.drawable.q1,R.drawable.q2,R.drawable.q3,R.drawable.q4};
//    private String[] Question={"Spring Theorum?","Find Normal?","Specify Newton's 2nd Law?","Newton's Third Law"};
    //private String[] copy_questions;
    private HorizontalScrollView horizontalScrollView;
    private ListView listView;
   private  boolean pos;
    private  int position;
   private Card_Adapter card_adapter;

//    @Override
//    public void setRetainInstance(boolean retain) {
//        super.setRetainInstance(retain);
//
//    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Fetch data here(Do data fetching here ex. frm URL's etc.)
        getData();

    }


//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if(savedInstanceState!=null) {
//            position = savedInstanceState.getInt("Position");
//            pos=true;
//        }
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle=getArguments();

        position = bundle.getInt("position");
        boolean val=getTest.getQuestions().get(position).getIsRead();
        boolean read=false;
        View view=null;

        view = inflater.inflate(R.layout.fragment_layout__view, container, false);
        question_textview = (TextView) view.findViewById(R.id.card_textview);
        question_imageview = (ImageView) view.findViewById(R.id.card_imageview1);
        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.horizontal_view);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating_btn);
        if(!val) {


       read=false;
        }
        else
        {


            read=true;
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        listView = (ListView) view.findViewById(R.id.listview_render);

           try {

            Picasso.with(getActivity()).load(getTest.getQuestions().get(position).getImageURL()).into(question_imageview);
            question_textview.setText(getTest.getQuestions().get(position).getTitle());
        }
        catch(NullPointerException e)
        {
            Log.e(e.getMessage(), "onCreateView:Layout_View.Java Error");
        }
      floatingActionButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });

        Recycler_Adapter recycler_adapter;
        /**
         * This line below saved my  ass. from radio mess (l..l)
         */

        recycler_adapter=new Recycler_Adapter(getTest,position,read,recyclerView);

        recyclerView.setAdapter(recycler_adapter);
//

        ListAdapter listAdapter=new ListAdapter(getActivity(),getTest.getQuestions().size());
        listView.setAdapter(listAdapter);


        view.setTag("Layout_View");

//setRetainInstance(true);   ----CHANGED
        return view;
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("Position",position);
//    }


    public void getData()
    {
        try {

            getTest= SingletonData.getInstance().getField();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        SingletonData.getInstance().setView(view);
//
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        SingletonData.getInstance().setView(getView());
//    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        SingletonData.getInstance().setView(getView());
//
//
//    }
}
