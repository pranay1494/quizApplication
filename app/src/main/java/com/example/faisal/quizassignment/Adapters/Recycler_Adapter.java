package com.example.faisal.quizassignment.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faisal.quizassignment.Adapters.*;
import com.example.faisal.quizassignment.Async_Tasks.*;
import com.example.faisal.quizassignment.Fragments.*;
import com.example.faisal.quizassignment.Interfaces.*;
import com.example.faisal.quizassignment.Models.*;
import com.example.faisal.quizassignment.R;
import com.example.faisal.quizassignment.Singleton.*;
import com.squareup.picasso.Picasso;
/**
 * RECYCLER VIEW ONLY REMEMBERS OR INFLATE THE NEXT(+1) AND PREV(-1) VIEWS.
 * Try not to overcrowd your MODEL class, as it will create a issue.
 */
/**
 * Created by anubhav on 19/2/16.
 */
public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.CustomHolder> implements View.OnClickListener{
  private  CustomHolder customHolder;
//  private int[] resources={R.drawable.rsz_img1,R.drawable.rsz_img2,R.drawable.img3,R.drawable.rsz_img4};
//    private String[] Option={"A","B","C","D"};
 private Test test;
    private int quesPosition;
    private Context context;
   // private static CompoundButton compoundButton;
    private RadioButton radioButton1=null;
    private static int lastCheckedPos = 0;
    private boolean read=false;
    private View view;
    private RecyclerView recyclerView;
   // private RadioButton read_pos;
  int pos;


    //Constructor
    public Recycler_Adapter(Test test, int quesPos,boolean read, RecyclerView recyclerView1) {
       this.test=test;
        this.quesPosition=quesPos;
        this.read=read;
        this.recyclerView=recyclerView1;
//        test.getQuestions().get(quesPosition).setIsRead(true);

    }


    @Override
    public Recycler_Adapter.CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);

            customHolder = new CustomHolder(view);
        view.setClickable(true);
        view.setFocusableInTouchMode(true);


        return customHolder;
    }

//    @Override
//    public void onViewAttachedToWindow(CustomHolder holder) {
//       super.onViewAttachedToWindow(holder);
//    if(SingletonData.getInstance().getBundle()!=null&&test.getQuestions().get(quesPosition).getIsRead())
//    {
//        Bundle bundle = new Bundle();
//        Parcelable parcelable=bundle.getParcelable("Recycle-op" + quesPosition);
//        recyclerView.getLayoutManager().onRestoreInstanceState(parcelable);
//
//        // customHolder=(CustomHolder)recyclerView.findViewHolderForAdapterPosition(quesPosition) ;
//        radioButton1=(RadioButton)  customHolder.radioButton.findViewWithTag(test.getQuestions().get(quesPosition).getOption_prev_position());
//
//        radioButton1.setChecked(true);
//
//
//    }
//    }

    @Override
    public void onBindViewHolder(final CustomHolder holder, final int position) {
        Picasso.with(context).load(test.getQuestions().get(quesPosition).getOption().get(position).getImageUrl()).into(holder.imageView);
        holder.textView.setText(test.getQuestions().get(quesPosition).getOption().get(position).getTitle().toString());
        holder.radioButton.setTag(new Integer(position));
        holder.radioButton.setEnabled(true);
        holder.radioButton.setChecked(false);
        pos =position;
        if(position==test.getQuestions().get(quesPosition).getOption_prev_position())
        {
        if(read)
        {



//Check is required to prevent recycling.
//            if(test.getQuestions().get(quesPosition).getDetached_view()) {




                radioButton1 = holder.radioButton;
                radioButton1.setChecked(true);
//                test.getQuestions().get(quesPosition).setDetached_view(false);
            //}


            }
        }

if(test.getQuestions().get(quesPosition).getOption().get(position).getSelected())
{
    radioButton1 = holder.radioButton;
    radioButton1.setChecked(true);
}
/*
       if(SingletonData.getInstance().getBundle()!=null)
        if(SingletonData.getInstance().getBundle().containsKey("Recycle-op" + quesPosition)&&test.getQuestions().get(quesPosition).getIsRead())
        {

            Bundle bundle = new Bundle();
            Parcelable parcelable=bundle.getParcelable("Recycle-op" + position);
           CustomHolder ch=(CustomHolder) recyclerView.getRecycledViewPool().getRecycledView(quesPosition);
            radioButton1=ch.radioButton;
            radioButton1.setChecked(true);
            recyclerView.getLayoutManager().onRestoreInstanceState(parcelable);

            // customHolder=(CustomHolder)recyclerView.findViewHolderForAdapterPosition(quesPosition) ;
//            radioButton1=(RadioButton)  holder.radioButton.findViewWithTag(test.getQuestions().get(quesPosition).getOption_prev_position());
//
//            radioButton1.setChecked(true);


        }

*/


        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            //Apply this for single choice only
            @Override
            public void onClick(View v) {
                RadioButton radioButton = (RadioButton) v;
                int clickedpos = ((Integer) radioButton.getTag()).intValue();
                if (radioButton.isChecked()) {
                    if (radioButton1 != null) {
                        radioButton1.setChecked(false);
                        test.getQuestions().get(quesPosition).getOption().get(lastCheckedPos).setSelected(false);
                    }

                    radioButton1 = radioButton;

                    lastCheckedPos = clickedpos;
                   // test.getQuestions().get(quesPosition).setOption_prev_pos(radioButton1);
                    test.getQuestions().get(quesPosition).setOption_prev_position(lastCheckedPos);
                  test.getQuestions().get(quesPosition).setIsRead(true);


                } else {
                    radioButton1.setChecked(false);
                    radioButton1 = null;

                }
                test.getQuestions().get(quesPosition).getOption().get(lastCheckedPos).setSelected(radioButton.isChecked());
              //  radioButton1.notifyAll();

            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialog_layout);
                dialog.setTitle("option");
                ImageView img = (ImageView) dialog.findViewById(R.id.dialogimg);
                TextView txt= (TextView) dialog.findViewById(R.id.dialogtxt);
                  Picasso.with(v.getContext()).load(test.getQuestions().get(quesPosition).getOption().get(position).getImageUrl()).into(img);
                txt.setText(test.getQuestions().get(quesPosition).getOption().get(pos).getTitle().toString());
                dialog.show();
            }
        });
        //holder.textView.setOnClickListener(this);
      //  holder.imageView.setOnClickListener(this);

    }


    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
//
//        recyclerView.getRecycledViewPool().clear();
//        test.getQuestions().get(quesPosition).setDetached_view(true);

//        Bundle bundle = new Bundle();
//        Parcelable parcelable = recyclerView.getLayoutManager().onSaveInstanceState();
//        if(radioButton1!=null)
//       recyclerView.addView(radioButton1, lastCheckedPos);
//        bundle.putParcelable("Recycle-op" + quesPosition, parcelable);
//        SingletonData.getInstance().setBundle(bundle);


    }

//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        if(SingletonData.getInstance().getBundle()!=null&&test.getQuestions().get(quesPosition).getIsRead())
//        {
//            Bundle bundle = new Bundle();
//                Parcelable parcelable=bundle.getParcelable("Recycle-op" + quesPosition);
//                recyclerView.getLayoutManager().onRestoreInstanceState(parcelable);
//
//           // customHolder=(CustomHolder)recyclerView.findViewHolderForAdapterPosition(quesPosition) ;
//          RadioButton radioButton=(RadioButton)  customHolder.radioButton.findViewWithTag(test.getQuestions().get(quesPosition).getOption_prev_position());
//
//          radioButton.setChecked(true);
//
//
//        }
//    }
//
    @Override
    public void onViewRecycled(CustomHolder holder) {
        super.onViewRecycled(holder);
//        holder=(CustomHolder) recyclerView.getRecycledViewPool().getRecycledView();
//holder.radioButton.setChecked(false);   //CHANGED


    }

    @Override
    public int getItemCount() {


        return test.getQuestions().get(quesPosition).getOption().size();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.card_options_imageview||v.getId()==R.id.card_options_textview)
        {
           // Log.d("pos",pos.toString() );

            Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.dialog_layout);
            dialog.setTitle("option");
            ImageView img = (ImageView) v.findViewById(R.id.dialogimg);
            TextView txt= (TextView) v.findViewById(R.id.dialogtxt);
          //  Picasso.with(v.getContext()).load(test.getQuestions().get(quesPosition).getOption().get(pos).getImageUrl()).into(img);
            txt.setText(test.getQuestions().get(quesPosition).getOption().get(pos).getTitle().toString());
            dialog.show();
        }
    }

    static class CustomHolder extends RecyclerView.ViewHolder{
     ImageView imageView;
     RadioButton radioButton;
     TextView textView;
//        Option pos;
//      static CustomHolder customHolder;

        public CustomHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.card_options_imageview);
            radioButton=(RadioButton)itemView.findViewById(R.id.radiobtn);
            textView=(TextView)itemView.findViewById(R.id.card_options_textview);
            //Setting of views is done here
        }


//        public void setPos(Option option)
//        {
//            this.pos=option;
//        }
//        public  Option getPos()
//        {
//            return pos;
//        }
//        public void setHolder(CustomHolder option_pos)
//        {
//            customHolder=option_pos;
//        }
//
//        public CustomHolder getHolder()
//        {
//            return customHolder;
//        }

    }



}
