package com.example.coderspot.common;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.example.coderspot.AdminLogin;
import com.example.coderspot.Login;
import com.example.coderspot.R;
import com.example.coderspot.register;

public class Activity_registrtn_and_login_panel extends AppCompatActivity {



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_registrtn_and_login_panel);
        }

        public void login(View view){

            Intent intent = new Intent(getApplicationContext(), Login.class);

            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View,String>(findViewById(R.id.login),"transition_login");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Activity_registrtn_and_login_panel.this,pairs);
            startActivity(intent,options.toBundle());

        }

        public void register(View view){

            Intent intent = new Intent(getApplicationContext(), register.class);

            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View,String>(findViewById(R.id.registr),"transition_register");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Activity_registrtn_and_login_panel.this,pairs);
            startActivity(intent,options.toBundle());


        }

    public void adminLogin(View view) {

        Intent intent = new Intent(getApplicationContext(), AdminLogin.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.adminlogin),"transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Activity_registrtn_and_login_panel.this,pairs);
        startActivity(intent,options.toBundle());
    }
}