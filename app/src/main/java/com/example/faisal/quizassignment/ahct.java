package com.example.faisal.quizassignment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import model.CategoriesPojo;

/**
 * Created by Pranay Bansal on 26/3/16.
 */
public class ahct extends AppCompatActivity {
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            jsonObject = new JSONObject();
            jsonObject.put("imageVersion",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("key_name", 0);
        editor.commit();

        GetBanner getBanner = new GetBanner();
        getBanner.execute();
    }

    public class GetBanner extends AsyncTask<Void ,Void,String >
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            URL url;
            String response = "";

            try {
                url = new URL("asd");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setDoOutput(true);

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(jsonObject.toString());

                writer.flush();
                writer.close();
                os.close();
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        response+=line;
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            CategoriesPojo categoriesPojo= gson.fromJson(s, CategoriesPojo.class);
        }
    }
}
