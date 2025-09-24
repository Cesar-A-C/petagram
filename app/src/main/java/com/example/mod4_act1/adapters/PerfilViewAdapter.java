package com.example.mod4_act1.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mod4_act1.models.PicsModel;

import java.util.ArrayList;

public class PerfilViewAdapter extends RecyclerView.Adapter<PerfilViewAdapter.PicsViewHolder>{
    ArrayList<PicsModel> pics;

    Activity activity;

    public PerfilViewAdapter(ArrayList<PicsModel> pics, Activity activity) {
        this.pics = pics;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PicsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class PicsViewHolder extends RecyclerView.ViewHolder{
        private ImageView petPic;
        private TextView petLikes;

        public PicsViewHolder(View itemView){
            super(itemView);

        }
    }
}
