package com.example.mod4_act1.adapters;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mod4_act1.R;
import com.example.mod4_act1.database.Database;
import com.example.mod4_act1.models.PetModel;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private ArrayList<PetModel> pets;
    private Activity activity;

    public PetAdapter(ArrayList<PetModel> pets, Activity activity) {
        this.pets = pets;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View petsView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.petview_card, parent, false);
        return new PetViewHolder(petsView);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {

        PetModel pet = pets.get(position);
        holder.petImage.setImageResource(pet.getPetPicture());
        holder.petName.setText(pet.getPetName());
        holder.petRatingNumber.setText(String.valueOf(pet.getPetLikes()));

        holder.petLike.setImageResource(pet.getFavorite() ? R.drawable.liked_bone : R.drawable.like_bone);

        holder.petLike.setOnClickListener(v -> {
            Database database = Database.getInstance(activity);
            SQLiteDatabase db = database.getWritableDatabase();

            int petId = getPetIdByUniqueKey(db, pet.getId());
            if (petId == -1) {
                Toast.makeText(activity, "Error: mascota no encontrada", Toast.LENGTH_SHORT).show();
                db.close();
                return;
            }

            int quantity = pet.getPetLikes();

            if (!pet.getFavorite()) {
                quantity++;
                pet.setFavorite(true);
                holder.petLike.setImageResource(R.drawable.liked_bone);
                holder.petRatingNumber.setText(String.valueOf(quantity));

                db.execSQL("INSERT OR IGNORE INTO tbl_favorites (id_pet) VALUES (?)", new Object[]{petId});

                Toast.makeText(activity, "Mascota agregada a favoritos", Toast.LENGTH_SHORT).show();
            } else {
                quantity--;
                pet.setFavorite(false);
                holder.petLike.setImageResource(R.drawable.like_bone);
                holder.petRatingNumber.setText(String.valueOf(quantity));

                db.delete("tbl_favorites", "id_pet = ?", new String[]{String.valueOf(petId)});

                Toast.makeText(activity, "Mascota eliminada de favoritos", Toast.LENGTH_SHORT).show();
            }

            pet.setPetLikes(quantity);

            db.close();
        });
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    // Método helper para obtener id de tbl_pets a partir de unique_key
    private int getPetIdByUniqueKey(SQLiteDatabase db, String uniqueKey) {
        Cursor cursor = db.rawQuery("SELECT id FROM tbl_pets WHERE unique_key = ?", new String[]{uniqueKey});
        int petId = -1;
        if (cursor.moveToFirst()) {
            petId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        }
        cursor.close();
        return petId;
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        private ImageView petImage, petLike;
        private TextView petName, petRatingNumber;

        public PetViewHolder(View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.imgPic);
            petLike = itemView.findViewById(R.id.icLikeBone);
            petName = itemView.findViewById(R.id.petName);
            petRatingNumber = itemView.findViewById(R.id.petRatingNumber);
        }
    }
}
