package com.analytics5paisa.courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.androidbrowserhelper.trusted.LauncherActivity;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private List<Integer> image = new ArrayList<>();
    private List<String> desc = new ArrayList<>();
    private ViewPager2 viewPager2;
    private ViewPagerAdapter adapter;
    private Button skip;
    private ImageButton next;
    private TabLayout tabLayout;
    private Handler slideHandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tabLayout=findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewpagerWelcome);
        skip=findViewById(R.id.skipwelcomeimage);
        next=findViewById(R.id.nextwelcomeimage);

        desc.add("Get Curated Financial Topics Everyday");
        desc.add("Learn One Finance Word Daily");
        desc.add("Engage Into Learning Courses Designed Only For You");
        desc.add("Join The Fastest Growing Investment Community Of Indian Youth's");

        image.add(R.drawable.welcome_1);
        image.add(R.drawable.welcome_2);
        image.add(R.drawable.welcome_3);
        image.add(R.drawable.welcome_4);

        adapter = new ViewPagerAdapter(image, desc, this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> {}).attach();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(runnable);
                slideHandler.postDelayed(runnable,2000);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://www.5paisa.com/finschool/login");
                startActivity(new Intent(WelcomeActivity.this, LauncherActivity.class).setData(uri));
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager2.getCurrentItem()!=3){
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
                }
                else{
                    Uri uri=Uri.parse("https://www.5paisa.com/finschool/login");
                    startActivity(new Intent(WelcomeActivity.this, LauncherActivity.class).setData(uri));
                    finish();
                }
            }
        });
    }
    Runnable runnable= new Runnable() {
        @Override
        public void run() {
            if(viewPager2.getCurrentItem()==desc.size()-1)
                viewPager2.setCurrentItem(0);
            else
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

}