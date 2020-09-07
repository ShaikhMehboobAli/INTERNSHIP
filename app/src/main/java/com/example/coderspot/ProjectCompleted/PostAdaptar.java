package com.example.coderspot.ProjectCompleted;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coderspot.R;

import java.util.ArrayList;

public class PostAdaptar extends RecyclerView.Adapter<PostAdaptar.PostViewHolder> {

    ArrayList<PostHelperClass> post;

    public PostAdaptar(ArrayList<PostHelperClass> post) {
        this.post = post;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_completed_post,parent,false);
        PostViewHolder postViewHolder = new PostViewHolder(view);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        PostHelperClass postHelperClass = post.get(position);

        holder.projname.setText(postHelperClass.getProjectname());
        holder.proj.setText(postHelperClass.getTvproject());
        holder.projdis.setText(postHelperClass.getTvdis());
        holder.projdate.setText(postHelperClass.getTvdate());
        holder.projassign.setText(postHelperClass.getTvassign());
    }

    @Override
    public int getItemCount() {
        return post.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{

        TextView projname;
        TextView proj;
        TextView projdis;
        TextView projdate;
        TextView projassign;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            projname= itemView.findViewById(R.id.projectname);
            proj= itemView.findViewById(R.id.tvproject);
            projdis= itemView.findViewById(R.id.tvdis);
            projdate= itemView.findViewById(R.id.tvdate);
            projassign= itemView.findViewById(R.id.tvassign);


        }
    }

}


//    public Context mContext;
//    public List<PostHelperClass> mpost;
//
//    private FirebaseUser taskuser;
////    FirebaseUser taskuser = FirebaseAuth.getInstance().getCurrentUser();
////    DatabaseReference tref = FirebaseDatabase.getInstance().getReference("taskuser").child("1");
//
//    public PostAdaptar(Context mContext, List<PostHelperClass> mpost) {
//        this.mContext = mContext;
//        this.mpost = mpost;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.project_completed_post, viewGroup, false);
//        return new PostAdaptar.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        taskuser  = FirebaseAuth.getInstance().getCurrentUser();
//        PostHelperClass p =mpost.get(position);
//
////        if (p.getProjectname().equals("")){
////            holder.projectname.setVisibility(View.GONE);
////        } else {
////            holder.projectname.setVisibility(View.VISIBLE);
////            holder.projectname.setText(p.getProjectname());
////        }
////
//        projectinfo(holder.projectname, holder.tvproject, holder.tvdis, holder.tvdate, holder.tvassign);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mpost.size();
//    }
//
//public class ViewHolder extends RecyclerView.ViewHolder{
//
//    public ImageView imname,imdate,imdisc,imassign,imicon;
//    public TextView t1,tvproject,projectname,t2,tvdis,t3,tvdate,t4,tvassign;
//
//    public ViewHolder(@NonNull View itemView) {
//        super(itemView);
//        imname = itemView.findViewById(R.id.imname);
//        imdate = itemView.findViewById(R.id.imdate);
//        imdisc = itemView.findViewById(R.id.imdisc);
//        imassign = itemView.findViewById(R.id.imassign);
//        imicon = itemView.findViewById(R.id.imicon);
//        t1 = itemView.findViewById(R.id.t1);
//        tvproject = itemView.findViewById(R.id.tvproject);
//        projectname = itemView.findViewById(R.id.projectname);
//        t2 = itemView.findViewById(R.id.t2);
//        tvdis = itemView.findViewById(R.id.tvdis);
//        t3 = itemView.findViewById(R.id.t3);
//        tvdate = itemView.findViewById(R.id.tvdate);
//        t4 = itemView.findViewById(R.id.t4);
//        tvassign = itemView.findViewById(R.id.tvassign);
//
//
//    }
//}
//
//    private void projectinfo (TextView projectname, TextView tvproject, TextView tvdis, TextView tvdate, TextView tvassign){
////        mAuth = FirebaseAuth.getInstance();
//
//        DatabaseReference tref = FirebaseDatabase.getInstance().getReference("taskuser").child("1");
//        tref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String value0 = snapshot.child("project_name").getValue().toString();
//
//                String value = snapshot.child("project_disp").getValue().toString();
//                String value1 = snapshot.child("task_date").getValue().toString();
//                String value2 = snapshot.child("task_by").getValue().toString();
//                projectname.setText(value0);
//                tvproject.setText(value0);
//                tvdis.setText(value);
//                tvdate.setText(value1);
//                tvassign.setText(value2);
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
