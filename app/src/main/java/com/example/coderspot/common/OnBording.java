package com.example.coderspot.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coderspot.HelperClass.SliderAdaptar;
import com.example.coderspot.Login;
import com.example.coderspot.R;

public class OnBording extends AppCompatActivity {

    //variables
    ViewPager viewPager;
    LinearLayout dotsLayouuut;
    SliderAdaptar sliderAdaptar;
    TextView[] dots;
    Button letsGetStarted;
    Animation animation;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_bording);

        //hookes
        viewPager = findViewById(R.id.slider);
        dotsLayouuut = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_bt);

        //call adapater
        sliderAdaptar = new SliderAdaptar(this);
        viewPager.setAdapter(sliderAdaptar);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }


    public void skip(View view) {

        startActivity(new Intent(this, Activity_registrtn_and_login_panel.class));
        finish();

    }

    public void next(View view) {

        viewPager.setCurrentItem(currentPosition + 1);

    }


    private void addDots(int position) {

        dots = new TextView[4];
        dotsLayouuut.removeAllViews();

        for (int i = 0; i < dots.length; i++) {

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dotsLayouuut.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.grad_start));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);

            currentPosition = position;

            if (position == 0) {

                letsGetStarted.setVisibility(View.INVISIBLE);

            } else if (position == 1) {
                letsGetStarted.setVisibility(View.INVISIBLE);

            } else if (position == 2) {
                letsGetStarted.setVisibility(View.INVISIBLE);

            } else {

                animation = AnimationUtils.loadAnimation(OnBording.this, R.anim.onbording_lets_get_stard_btn);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}