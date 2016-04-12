package tabpackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.faisal.quizassignment.R;

/**
 * Created by root on 22/2/16.
 */
public class TabFragment extends android.support.v4.app.Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static ViewPager ImgviewPager;

    public static int int_items = 3 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x =  inflater.inflate(R.layout.activity_main,null);

        tabLayout = (TabLayout) x.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) x.findViewById(R.id.pager);
        ImgviewPager = (ViewPager) x.findViewById(R.id.pager1);

       // final RelativeLayout bkg = (RelativeLayout) x.findViewById(R.id.imageView);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        ImgviewPager.setAdapter(new ImageAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
//                tabLayout.setupWithViewPager(ImgviewPager);
            }
        });

        ImgviewPager.setCurrentItem(0);
        int a = viewPager.getCurrentItem();
        //ImgviewPager.setBackgroundResource(imgs[a]);

        ImgviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
                tabLayout.setScrollPosition(position, 0, true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ImgviewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new TabFrag1();
                case 1 : return new TabFrag2();
                case 2 : return new TabFrag3();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Batman";
                case 1 :
                    return "Superman";
                case 2 :
                    return "Flash";
            }
            return null;
        }
    }


    class ImageAdapter extends FragmentPagerAdapter {

        public ImageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            return TabFrag4.newInstance(position, int_items);
        }

        @Override
        public int getCount() {
            return int_items;

        }
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

}
