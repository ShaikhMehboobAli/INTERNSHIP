package com.example.coderspot;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coderspot.ProjectCompleted.ProjectComplted;
import com.example.coderspot.ProjectNotStarted.ProjectNotStarted;
import com.example.coderspot.ProjectOnGoing.ProjectOnGoing;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fragment_task extends Fragment {
    TextView tvproject, tvdis, tvdate, tvassign, tvcompleted, tvnotstarted, tvname;
    private FirebaseAuth mAuth;
    FirebaseUser taskuser = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference tref = FirebaseDatabase.getInstance().getReference("taskuser").child("1");

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference fref = FirebaseDatabase.getInstance().getReference("user").child(user.getUid());


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_layout, container, false);
        return view;
    }

    public void ongoingtask(View view) {

        startActivity(new Intent(getContext(), ProjectComplted.class));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        TextView tvproject = view.findViewById(R.id.tvproject);
        TextView tvdis = view.findViewById(R.id.tvdis);
        TextView tvdate = view.findViewById(R.id.tvdate);
        TextView tvassign = view.findViewById(R.id.tvassign);
        TextView tvongoing = view.findViewById(R.id.tvongoing);
        TextView tvcompleted = view.findViewById(R.id.tvcompleted);
        TextView tvnotstarted = view.findViewById(R.id.tvnotstarted);
        TextView tvname = view.findViewById(R.id.tvname);
//        tvproject.setText("Project Name="+taskuser.getDisplayName().toString());

        fref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value1 = snapshot.child("fname").getValue(String.class);

                tvname.setText("Welcome " + value1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        tvongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(getActivity(), ProjectOnGoing.class);
                startActivity(i);
                tref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    String value0 = snapshot.child("project_name").getValue().toString();
//
//                    String value = snapshot.child("project_disp").getValue().toString();
//                    String value1 = snapshot.child("task_date").getValue().toString();
//                    String value2 = snapshot.child("task_by").getValue().toString();
//                    tvproject.setText(""+value0);
//                    tvdis.setText(""+value);
//                    tvdate.setText(""+value1);
//                    tvassign.setText(""+value2);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        tvnotstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), ProjectNotStarted.class);
                startActivity(i);
                tref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String value0 = snapshot.child("project_name").getValue().toString();
//
//                        String value = snapshot.child("project_disp").getValue().toString();
//                        String value1 = snapshot.child("task_date").getValue().toString();
//                        String value2 = snapshot.child("task_by").getValue().toString();
//                        tvproject.setText("Aman shaikh");
//                        tvdis.setText("nothing to display");
//                        tvdate.setText("1/12/12");
//                        tvassign.setText("aman");


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        tvcompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), ProjectComplted.class);
                startActivity(i);
                tref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String value0 = snapshot.child("project_name").getValue().toString();
//
//                        String value = snapshot.child("project_disp").getValue().toString();
//                        String value1 = snapshot.child("task_date").getValue().toString();
//                        String value2 = snapshot.child("task_by").getValue().toString();
//                        tvproject.setText("Abhishek");
//                        tvdis.setText("there may be something to display");
//                        tvdate.setText("22/06/20");
//                        tvassign.setText("abhishek CET");


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}
