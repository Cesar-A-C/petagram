package com.example.mod4_act1.controllers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mod4_act1.R;
import com.example.mod4_act1.adapters.PetAdapter;
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
        pets = new ArrayList<>();

        pets.add(new PetModel("Ramón", R.drawable.cat,false, 0));
        pets.add(new PetModel("Ignacio", R.drawable.cat,false,0));
        pets.add(new PetModel("César", R.drawable.cat,false,0));
        pets.add(new PetModel("Raúl", R.drawable.cat,false,0));
        pets.add(new PetModel("Felipe", R.drawable.cat,false,0));
        pets.add(new PetModel("Karencio", R.drawable.cat,false,0));
    }

    public  void inicializeAdapter(){
        adapter = new PetAdapter(pets,getActivity());
        rvPets.setAdapter(adapter);
    }
}
