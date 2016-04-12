package com.example.faisal.quizassignment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by anubhav on 19/2/16.
 */
public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.CustomHolder> {
  private  CustomHolder customHolder;
  private int[] resources={R.drawable.rsz_img1,R.drawable.rsz_img2,R.drawable.img3,R.drawable.rsz_img4};
    private String[] options={"A","B","C","D"};


    @Override
    public Recycler_Adapter.CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        customHolder=new CustomHolder(view);
        return customHolder;
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {
        holder.imageView.setImageResource(resources[position]);
        holder.textView.setText(options[position]);
        holder.radioButton.setEnabled(true);
    }



    @Override
    public int getItemCount() {
        return options.length;
    }
    static class CustomHolder extends RecyclerView.ViewHolder{
     ImageView imageView;
        RadioButton radioButton;
        TextView textView;

        public CustomHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.card_options_imageview);
            radioButton=(RadioButton)itemView.findViewById(R.id.radiobtn);
            textView=(TextView)itemView.findViewById(R.id.card_options_textview);
            //Setting of views is done here
        }
    }
}
