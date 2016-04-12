package com.example.faisal.quizassignment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Pranay Bansal on 27/3/16.
 */
public class Filestorage extends AppCompatActivity implements View.OnClickListener{
    ImageView img;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filestorage);
        btn = (Button) findViewById(R.id.btn);
        img = (ImageView) findViewById(R.id.img);
        btn.setOnClickListener(this);

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            Toast.makeText(this,"Sd card present",Toast.LENGTH_SHORT).show();

            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File(sdCard.getAbsolutePath() + "/Ahct");
            if(!dir.isDirectory()&&!dir.exists())
            {
                Toast.makeText(this,"Making directory",Toast.LENGTH_SHORT).show();
                dir.mkdirs();
            }
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dbz);
            File file = new File(dir, "d.png");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            FileOutputStream f = null;
            //f = new FileOutputStream(file);

            try {
                f = new FileOutputStream(file);
                if (f != null) {
                    f.write(baos.toByteArray());
                    f.flush();
                    f.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            //No sd card
            Toast.makeText(this,"Sd card not present",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn)
        {
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File(sdCard.getAbsolutePath() + "/Ahct");
            String[] file = dir.list();
            for(int i=0;i<file.length;i++)
            {
                Log.d("path",dir.toString()+file[i]);
                Log.d("path",file[i]);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmap = BitmapFactory.decodeFile(dir.toString()+"/"+file[i], options);
                img.setImageBitmap(bitmap);
            }
        }
    }
}
