package com.example.coderspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.coderspot.common.Activity_registrtn_and_login_panel;
import com.example.coderspot.common.OnBording;

public class MainActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 1500;




    //animations code
    Animation topanim, bottomanim, middleanim;

    SharedPreferences onBording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //splash screen

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                onBording = getSharedPreferences("onBording", MODE_PRIVATE);

                boolean isFirstTime = onBording.getBoolean("firstTime", true);

                if (isFirstTime) {

                    SharedPreferences.Editor editor = onBording.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), OnBording.class);
                    startActivity(intent);
                } else {

                    Intent intent = new Intent(getApplicationContext(), Activity_registrtn_and_login_panel.class);
                    startActivity(intent);

                }
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}



