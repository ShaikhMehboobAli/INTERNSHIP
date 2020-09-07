package com.example.coderspot.ProjectOnGoing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coderspot.R;

import java.util.ArrayList;

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.OngoingViewHolder> {

    ArrayList<OngoingHelperClass> ongoing;

    public OngoingAdapter(ArrayList<OngoingHelperClass> ongoing) {
        this.ongoing = ongoing;
    }

    @NonNull
    @Override
    public OngoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ongoing_recyclerview,parent,false);
        OngoingViewHolder ongoingViewHolder = new OngoingViewHolder(view);
        return ongoingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingViewHolder holder, int position) {

        OngoingHelperClass ongoingHelperClass = ongoing.get(position);

        holder.projname.setText(ongoingHelperClass.getProjectname());
        holder.proj.setText(ongoingHelperClass.getTvproject());
        holder.projdis.setText(ongoingHelperClass.getTvdis());
        holder.projdate.setText(ongoingHelperClass.getTvdate());
        holder.projassign.setText(ongoingHelperClass.getTvassign());

    }


    @Override
    public int getItemCount() {
        return ongoing.size();
    }

    public static class OngoingViewHolder extends RecyclerView.ViewHolder{

        TextView projname;
        TextView proj;
        TextView projdis;
        TextView projdate;
        TextView projassign;

        public OngoingViewHolder(@NonNull View itemView) {
            super(itemView);

            projname= itemView.findViewById(R.id.projectname);
            proj= itemView.findViewById(R.id.tvproject);
            projdis= itemView.findViewById(R.id.tvdis);
            projdate= itemView.findViewById(R.id.tvdate);
            projassign= itemView.findViewById(R.id.tvassign);

        }
    }
}


