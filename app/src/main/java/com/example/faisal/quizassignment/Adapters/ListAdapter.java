package com.example.faisal.quizassignment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.faisal.quizassignment.R;

import com.example.faisal.quizassignment.Adapters.*;
import com.example.faisal.quizassignment.Async_Tasks.*;
import com.example.faisal.quizassignment.Fragments.*;
import com.example.faisal.quizassignment.Interfaces.*;
import com.example.faisal.quizassignment.Models.*;
import com.example.faisal.quizassignment.Singleton.*;


/**
 * Created by anubhav on 21/2/16.
 *
 * NOT RENDERING THE OVALS as on 20/02/2016
 *
 *
 *
 */
public class ListAdapter extends BaseAdapter {
    private Context context;
    private  int length;
    private Holder holder;

    public ListAdapter(Context context, int length) {
        this.context = context;
        this.length=length;
    }

    @Override
    public int getCount() {
        return length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // TRY TO DEBUG THIS THING... Not rendering the ovals..

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row= convertView;
        if(convertView==null) {
            holder=new Holder();
            row=layoutInflater.inflate(R.layout.footer_circles,parent,false);
            holder.v1=(View)row.findViewById(R.id.footer_circle);
            row.setTag(holder);


        }
        else

        {
            holder=(Holder)row.getTag(position);
        }
        return row;
    }
    static class Holder{
        View v1;
    }
}
