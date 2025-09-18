package com.example.mod4_act1.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mod4_act1.R;
import com.example.mod4_act1.models.PetModel;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>{
    ArrayList<PetModel> pets;
    Activity activity;

    public PetAdapter(ArrayList<PetModel> pets, Activity activity){
        this.pets = pets;
        this.activity = activity;
    }

    @NonNull
    @Override
    public  PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View petsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.petview_card,parent,false);
        return new PetViewHolder(petsView);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder petViewHolder, int position){
        PetModel pet = pets.get(position);
        petViewHolder.petImage.setImageResource(pet.getPetPicture());
        petViewHolder.petName.setText(pet.getPetName());
        petViewHolder.petRatingNumber.setText(String.valueOf(pet.getPetLikes()));

        if(pet.getFavorite()){
            petViewHolder.petLike.setImageResource(R.drawable.liked_bone);
        }else{
            petViewHolder.petLike.setImageResource(R.drawable.like_bone);
        }

        petViewHolder.petLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ratingNumber = petViewHolder.petRatingNumber.getText().toString();
                int quantity = Integer.parseInt(ratingNumber);
                pet.setFavorite(!pet.getFavorite());
                if(pet.getFavorite()){
                    quantity+=1;
                    petViewHolder.petLike.setImageResource(R.drawable.liked_bone);
                    petViewHolder.petRatingNumber.setText(String.valueOf(quantity));
                }else{
                    quantity-=1;
                    petViewHolder.petLike.setImageResource(R.drawable.like_bone);
                    petViewHolder.petRatingNumber.setText(String.valueOf(quantity));
                }
            }
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos que continene el RecyclerView
        return pets.size();
    }


    public static class PetViewHolder extends RecyclerView.ViewHolder{
        private ImageView petImage,petLike;
        private TextView petName, petRatingNumber;

        public PetViewHolder(View itemView){
            super(itemView);
            petImage = (ImageView) itemView.findViewById(R.id.imgPic);
            petLike = (ImageView) itemView.findViewById(R.id.icLikeBone);
            petName = (TextView) itemView.findViewById(R.id.petName);
            petRatingNumber = (TextView) itemView.findViewById(R.id.petRatingNumber);
        }
    }

}

