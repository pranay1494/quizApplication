package tabpackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.faisal.quizassignment.R;

/**
 * Created by root on 19/2/16.
 */
public class TabFrag4 extends android.support.v4.app.Fragment{

    public TabFrag4(){

    }


    public static TabFrag4 newInstance(int position, int count) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putInt("count", count);
        TabFrag4 f = new TabFrag4();
        f.setArguments(bundle);
        return f;
    }

   private int imgs[] = {R.drawable.bat,R.drawable.su,R.drawable.flash};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView =  inflater.inflate(R.layout.tabfrag4,container,false);

        ImageView imageBG = (ImageView) parentView.findViewById(R.id.imageBG);
        int position = getArguments().getInt("position");
        int count = getArguments().getInt("count");
        if(position >-1 && position < imgs.length){
            imageBG.setImageResource(imgs[position]);
        }

        return parentView;

    }
}
