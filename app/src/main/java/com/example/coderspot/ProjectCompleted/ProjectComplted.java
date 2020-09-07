package com.example.coderspot.ProjectCompleted;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.coderspot.R;

import java.util.ArrayList;

public class ProjectComplted extends AppCompatActivity {

    RecyclerView CompletedRecycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.project_completed);

        CompletedRecycler = findViewById(R.id.completedrecycle);
        CompltedRecycler();

    }

    private void CompltedRecycler() {

        CompletedRecycler.setHasFixedSize(true);
        CompletedRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<PostHelperClass> post = new ArrayList<>();
        post.add(new PostHelperClass("CoderSpot","CoderSpot","you have to do bla bla bla ","20/10/12","Aman"));
        post.add(new PostHelperClass("Amazon","Amazon","you have to do bla bla bla ","25/02/28","Abhi"));
        adapter = new PostAdaptar(post);
        CompletedRecycler.setAdapter(adapter);
    }
}

//    private List<PostHelperClass> postHelperClassLists;
//
//    private List<String> followingList;
//
//    public Ongoing(List<String> followingList) {
//        this.followingList = followingList;
//    }
//
//
//    @Override
//    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.project_completed, container, false);
//
//        RecyclerView recyclerView = view.findViewById(R.id.ongoingrecycle);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        postHelperClassLists = new ArrayList<>();
//        PostAdaptar postAdaptar = new PostAdaptar(getContext(), postHelperClassLists);
//        recyclerView.setAdapter(postAdaptar);
//        return view;
//
//    }
//
//    private void checkDetails(){
//        readDetailProject();
//    }
//
//
//
//    private void readDetailProject() {
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("taskuser");
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//                    PostHelperClass PostHelperClass =dataSnapshot.getValue(PostHelperClass.class);
//                    for (String id :followingList){
//                        if (PostHelperClass.getProjectname().equals(id)){
//                            postHelperClassLists.add(PostHelperClass);
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//}