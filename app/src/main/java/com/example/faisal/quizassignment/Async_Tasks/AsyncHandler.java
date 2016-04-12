package com.example.faisal.quizassignment.Async_Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.example.faisal.quizassignment.Adapters.*;
import com.example.faisal.quizassignment.Async_Tasks.*;
import com.example.faisal.quizassignment.Fragments.*;
import com.example.faisal.quizassignment.Interfaces.*;
import com.example.faisal.quizassignment.Models.*;
import com.example.faisal.quizassignment.Singleton.SingletonData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by anubhav on 23/2/16.
 */
public class AsyncHandler extends AsyncTask<Void,Void, String> {

    private Test returnJsonList;
    private String url;
    private String requestJson;
    public TestInterface testInterface;
    private String response = "";
    private Context context;
    private ProgressDialog progressDialog;

    public AsyncHandler(String url,Context context, String requestJson, TestInterface testInterface) {

        this.url = url;
        this.requestJson = requestJson;
        this.testInterface=testInterface;
        this.context=context;
    }
//test

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading Your Test.....");
        progressDialog.setTitle("Test Loader");
        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();
      
    }

    @Override
    protected String doInBackground(Void... params) {

      URL urla;

        try {
            urla = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) urla.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(requestJson);

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }



    @Override
    protected void onPostExecute(String result) {
        Gson gson=new Gson();
        Test test=gson.fromJson(response,Test.class);
        returnJsonList=test;
        if(SingletonData.getInstance().isCounter())
        SingletonData.getInstance().setField(returnJsonList);
//        testInterface.getTestList(returnJsonList);

    testInterface.getTestList("done");
        progressDialog.hide();
    }

    public Test returnJsonresult()
    {
        return returnJsonList;
    }

}

