package com.example.mod4_act1.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mod4_act1.R;
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
        View picView = LayoutInflater.from(parent.getContext()).inflate(R.layout.minipet_card,parent,false);
        return new PicsViewHolder(picView);
    }

    @Override
    public void onBindViewHolder(@NonNull PicsViewHolder holder, int position) {
        PicsModel pic = pics.get(position);
        holder.petPic.setImageResource(pic.getPics());
        holder.petLikes.setText(String.valueOf(pic.getPicLikes()));
    }

    @Override
    public int getItemCount() {
        return pics.size();
    }

    public static class PicsViewHolder extends RecyclerView.ViewHolder{
        private ImageView petPic;
        private TextView petLikes;

        public PicsViewHolder(View itemView){
            super(itemView);
            petPic = (ImageView) itemView.findViewById(R.id.imgMiniPet);
            petLikes = (TextView) itemView.findViewById(R.id.miniPetLikes);

        }
    }
}
