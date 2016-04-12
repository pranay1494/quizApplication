package com.example.faisal.quizassignment;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faisal.quizassignment.Adapters.*;
import com.example.faisal.quizassignment.Async_Tasks.*;
import com.example.faisal.quizassignment.Fragments.*;
import com.example.faisal.quizassignment.Interfaces.*;
import com.example.faisal.quizassignment.Models.*;
import com.example.faisal.quizassignment.Singleton.SingletonData;

import org.json.JSONException;
import org.json.JSONObject;

import navigation.NavActivity;

public class MainActivitya extends AppCompatActivity {
static int pos=0;
    private ViewPager viewPager;
//static int key;
    TextView timerText;
//    CountDownTimer countDownTimer;
//    private long millisremaining;
//    static boolean keybool;

    private JSONObject jsonObject;
   private Bundle bundle=null;
    private int category_id=1;
    private String url="https://connectdevhiappt.hixapi.com/quiz-services/rest/fetch_questions";
//   private int time;
    private boolean setItem=false;
    Bundle bundle1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maina);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        Intent intent=getIntent();
        Bundle b=intent.getExtras();
        category_id=b.getInt("category_id");
        bundle=savedInstanceState;

        actionBar.setLogo(R.drawable.ic_action_questions_48);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setShowHideAnimationEnabled(true);

        try {
            jsonObject=new JSONObject();
            jsonObject.put("categoryId",category_id);
        } catch (JSONException e) {
            Log.e(e.getMessage(), "ERRRRRRRRR....onCreate: ");
        }


        viewPager=(ViewPager)findViewById(R.id.view_pager);



    }

    @Override
    protected void onStart() {
        super.onStart();



            AsyncHandler asyncHandler = new AsyncHandler(url, this, String.valueOf(jsonObject), new TestInterface() {
                @Override
                public void getTestList(String result) {
                    if(SingletonData.getInstance().isCounter())    //  S E E - F R O M   H E R E
                    startTimer(SingletonData.getInstance().getTimer() * 1000, 1000);

                    onPopulateAdapter();
                }
            });

            asyncHandler.execute();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(SingletonData.getInstance().getBundle()!=null) {
             bundle1 = SingletonData.getInstance().getBundle();
setItem=true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Saved", viewPager.getCurrentItem());
        SingletonData.getInstance().setBundle(outState);

    }

    protected void onPopulateAdapter() {
        super.onPostResume();
        if(setItem)
        {
            viewPager.setCurrentItem(bundle1.getInt("Saved"),true);
            setItem=false;
        }
        else {
            Card_Adapter card_adapter = new Card_Adapter(getSupportFragmentManager());
            viewPager.setAdapter(card_adapter);
        }



        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                MainActivitya.pos = position;
                //  Toast.makeText(MainActivitya.this, "Question: " + Integer.toString(position + 1), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        MenuItem timerItem = menu.findItem(R.id.countdown);
        timerText = (TextView) MenuItemCompat.getActionView(timerItem);
        timerText.setTextColor(Color.WHITE);
        timerText.setTextSize(18);



        return true;
    }
public void startTimer(long millisfuture,long millisinterval)
{
    CountDownTimer timer = new CountDownTimer(millisfuture, millisinterval) {

        @Override
        public void onFinish() {
            //TODO Whatever's meant to happen when it finishes
            timerText.setText("Time Over Ho Gya..");
        }

        @Override
        public void onTick(long millisecondsLeft) {
         //   int secondsLeft = (int) Math.round((millisecondsLeft / (double) 1000));
            timerText.setText("Remaining: "+Long.toString(millisecondsLeft/1000));
        }
    };
timer.start();
}
/*  Conversion from String to Time Format
    private String secondsToString(int improperSeconds) {

        //Seconds must be fewer than are in a day

        Time secConverter = new Time();

        secConverter.hour = 0;
        secConverter.minute = 0;
        secConverter.second = 0;

        secConverter.second = improperSeconds;
        secConverter.normalize(true);

        String hours = String.valueOf(secConverter.hour);
        String minutes = String.valueOf(secConverter.minute);
        String seconds = String.valueOf(secConverter.second);

        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        if (hours.length() < 2) {
            hours = "0" + hours;
        }

        String timeString = hours + ":" + minutes + ":" + seconds;
        return timeString;
    }

*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
if(item.getItemId()==R.id.actionbar_next)
{
    viewPager.setCurrentItem(pos+1,true);
}
        else if(item.getItemId()==R.id.actionbar_prev)
{
    viewPager.setCurrentItem(pos-1,true);
}
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("ActivityPos",pos);
//    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent intent=new Intent(MainActivitya.this, NavActivity.class);
            for(int i=0;i<SingletonData.getInstance().getField().getQuestions().size();i++)
            {
                SingletonData.getInstance().getField().getQuestions().get(i).setIsRead(false);
            }
            SingletonData.getInstance().resetAll();
            startActivity(intent);

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SingletonData.getInstance().setCounter(true);
    }
}
