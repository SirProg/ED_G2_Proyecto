package com.example.dsproyect_p1.modules.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsproyect_p1.R;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>{
    Context context;
    String[] mDataSet;

    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_contact, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set the name of contact in the TextView according to the position
        holder.nameContact.setText(mDataSet[position]);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameContact;
        private ImageView imagenContact;
        private LinearLayout linearLayout;

        public ViewHolder(View view){
            super(view);
            // Initializes the TextView according to the id
            this.nameContact = itemView.findViewById(R.id.tvNombreContact);
            this.imagenContact = itemView.findViewById(R.id.ivContact);
            this.linearLayout = itemView.findViewById(R.id.linearLayoutContact);

        }

        public TextView getTextView(){
            return nameContact;
        }
    }

    public AdapterRecyclerView(Context context, String[] contact){
        this.mDataSet = contact;
        this.context = context;
    }




}
