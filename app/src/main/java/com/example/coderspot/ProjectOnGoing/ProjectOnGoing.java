package com.example.coderspot.ProjectOnGoing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.coderspot.ProjectCompleted.PostAdaptar;
import com.example.coderspot.ProjectCompleted.PostHelperClass;
import com.example.coderspot.R;

import java.util.ArrayList;

public class ProjectOnGoing extends AppCompatActivity {

    RecyclerView ongoingRecycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_project_on_going);

        ongoingRecycler = findViewById(R.id.ongoingrecycle);
        ongoingRecycler();
    }

    private void ongoingRecycler() {

        ongoingRecycler.setHasFixedSize(true);
        ongoingRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<OngoingHelperClass> ongoing = new ArrayList<>();
        ongoing.add(new OngoingHelperClass("Flipkart","Flipkart","you have to do bla bla bla ","20/10/12","Aman"));
        ongoing.add(new OngoingHelperClass("coderSot","coderSot","you have to do bla bla bla ","25/02/28","Abhi"));
        ongoing.add(new OngoingHelperClass("Micromax","Micromax","you have to do bla bla bla ","25/02/28","aman"));
        ongoing.add(new OngoingHelperClass("Blackberry","Blackberry","you have to do bla bla bla ","25/02/28","aman"));
        adapter = new OngoingAdapter(ongoing);
        ongoingRecycler.setAdapter(adapter);
    }
}