package com.example.mod4_act1.controllers.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mod4_act1.R;
import com.example.mod4_act1.adapters.PetAdapter;
import com.example.mod4_act1.database.Database;
import com.example.mod4_act1.models.PetModel;

import java.util.ArrayList;

public class PetFragment extends Fragment {
    ArrayList<PetModel> pets;
    PetAdapter adapter;
    private RecyclerView rvPets;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View petView = inflater.inflate(R.layout.fragment_petsview,container,false);
        rvPets = (RecyclerView) petView.findViewById(R.id.rvPets);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvPets.setLayoutManager(llm);

        inicializePetsList();
        inicializeAdapter();

        return petView;
    }

        public void inicializePetsList(){
        Database database = Database.getInstance(getActivity());
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_pets",null);
        pets = new ArrayList<>();

        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndexOrThrow("id");
            int keyIndex = cursor.getColumnIndexOrThrow("unique_key");
            int nameIndex = cursor.getColumnIndexOrThrow("name");
            int imageIndex = cursor.getColumnIndexOrThrow("image");
            int likesIndex = cursor.getColumnIndexOrThrow("likes");

            do {
                int petId = cursor.getInt(idIndex);
                String petKey = cursor.getString(keyIndex);
                String petName = cursor.getString(nameIndex);
                int petImage = cursor.getInt(imageIndex);
                int petLikes = cursor.getInt(likesIndex);

                Cursor favCursor = db.rawQuery(
                        "SELECT 1 FROM tbl_favorites AS fav " +
                                "INNER JOIN tbl_pets AS pet ON pet.id = fav.id_pet " +
                                "WHERE pet.id = ?",
                        new String[]{String.valueOf(petId)}
                );

                boolean isFavorite = favCursor.moveToFirst();
                favCursor.close();
                int likes = isFavorite ? 1 : 0;


                pets.add(new PetModel(petKey, petName, petImage, isFavorite, likes));
            } while (cursor.moveToNext());
        }else{
            Toast.makeText(getActivity(), "No hay mascotas registradas", Toast.LENGTH_SHORT).show();
        }

            cursor.close();
            db.close();
        }

    public  void inicializeAdapter(){
        adapter = new PetAdapter(pets,getActivity());
        rvPets.setAdapter(adapter);
    }
}
