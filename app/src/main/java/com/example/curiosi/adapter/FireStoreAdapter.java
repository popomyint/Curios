package com.example.curiosi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.curiosi.R;
import com.example.curiosi.model.DataModel;

import java.util.List;

public class FireStoreAdapter extends RecyclerView.Adapter<FireStoreAdapter.CustomViewHolder> {

    List<DataModel> dataList;


    public FireStoreAdapter(List datasList){
        this.dataList = datasList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_content, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        DataModel data = dataList.get(position);
        holder.did.setText(String.valueOf(data.did));
        holder.title.setText(data.title);
        holder.desc.setText(data.desc);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        TextView did;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            did = itemView.findViewById(R.id.did);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}