package com.example.coderspot.ProjectNotStarted;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.coderspot.ProjectOnGoing.OngoingHelperClass;
import com.example.coderspot.R;

import java.util.ArrayList;

public class ProjectNotStarted extends AppCompatActivity {

    RecyclerView notStartedRecycle;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_project_not_started);

        notStartedRecycle = findViewById(R.id.notstartedrecycle);
        notStartedRecycle();
    }

    private void notStartedRecycle() {

        notStartedRecycle.setHasFixedSize(true);
        notStartedRecycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<NotstartedHelperClass> notstarted = new ArrayList<>();
        notstarted.add(new NotstartedHelperClass("Apple","Apple","you have to do bla bla bla ","20/10/12","Aman"));
        notstarted.add(new NotstartedHelperClass("TechMahindra","TechMahindra","you have to do bla bla bla ","25/02/28","Abhi"));
        notstarted.add(new NotstartedHelperClass("Samsung","Samsung","you have to do bla bla bla ","25/02/28","Abhi"));
        adapter = new NotstartedAdaptar(notstarted);
        notStartedRecycle.setAdapter(adapter);
    }
}