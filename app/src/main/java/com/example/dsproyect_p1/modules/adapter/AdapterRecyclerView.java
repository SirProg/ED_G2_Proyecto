package com.example.dsproyect_p1.modules.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsproyect_p1.R;

public class AdapterRecyclerView extends RecyclerView.Adapter {
    private String[] contact;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView contact;

        public ViewHolder(TextView textView){
            super(textView);
            this.contact = textView;
        }
    }

    public AdapterRecyclerView(String[] contact){
        this.contact = contact;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
