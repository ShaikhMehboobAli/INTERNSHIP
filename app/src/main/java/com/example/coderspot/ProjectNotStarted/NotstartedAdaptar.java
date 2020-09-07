package com.example.coderspot.ProjectNotStarted;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coderspot.R;

import java.util.ArrayList;

public class NotstartedAdaptar extends RecyclerView.Adapter<NotstartedAdaptar.NotstartedViewHolder> {

    ArrayList<NotstartedHelperClass> notstarted;

    public NotstartedAdaptar(ArrayList<NotstartedHelperClass> notstarted) {
        this.notstarted = notstarted;
    }

    @NonNull
    @Override
    public NotstartedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.not_started_recycler,parent,false);
        NotstartedViewHolder notstartedViewHolder = new NotstartedViewHolder(view);
        return notstartedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotstartedViewHolder holder, int position) {

        NotstartedHelperClass notstartedHelperClass = notstarted.get(position);

        holder.projname.setText(notstartedHelperClass.getProjectname());
        holder.proj.setText(notstartedHelperClass.getTvproject());
        holder.projdis.setText(notstartedHelperClass.getTvdis());
        holder.projdate.setText(notstartedHelperClass.getTvdate());
        holder.projassign.setText(notstartedHelperClass.getTvassign());

    }

    @Override
    public int getItemCount() {
        return notstarted.size();
    }

    public static class NotstartedViewHolder extends RecyclerView.ViewHolder{

        TextView projname;
        TextView proj;
        TextView projdis;
        TextView projdate;
        TextView projassign;


        public NotstartedViewHolder(@NonNull View itemView) {
            super(itemView);

            projname= itemView.findViewById(R.id.projectname);
            proj= itemView.findViewById(R.id.tvproject);
            projdis= itemView.findViewById(R.id.tvdis);
            projdate= itemView.findViewById(R.id.tvdate);
            projassign= itemView.findViewById(R.id.tvassign);

        }
    }

}
