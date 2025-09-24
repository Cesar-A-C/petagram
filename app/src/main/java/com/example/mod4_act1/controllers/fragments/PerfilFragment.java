package com.example.mod4_act1.controllers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mod4_act1.R;
import com.example.mod4_act1.adapters.PerfilViewAdapter;
import com.example.mod4_act1.models.PicsModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerfilFragment extends Fragment {

    private RecyclerView rvPics;
    private PerfilViewAdapter adapter;
    private ArrayList<PicsModel> photos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View picsView = inflater.inflate(R.layout.fragment_perfil,container,false);

        rvPics = (RecyclerView) picsView.findViewById(R.id.rvPics);
        photos = new ArrayList<>();
        photos.add(new PicsModel(R.drawable.cat,1));
        photos.add(new PicsModel(R.drawable.cat,2));
        photos.add(new PicsModel(R.drawable.cat,3));
        photos.add(new PicsModel(R.drawable.cat,4));
        photos.add(new PicsModel(R.drawable.cat,5));
        photos.add(new PicsModel(R.drawable.cat,6));
        photos.add(new PicsModel(R.drawable.cat,7));
        photos.add(new PicsModel(R.drawable.cat,8));
        photos.add(new PicsModel(R.drawable.cat,9));
        photos.add(new PicsModel(R.drawable.cat,10));
        photos.add(new PicsModel(R.drawable.cat,11));
        photos.add(new PicsModel(R.drawable.cat,12));
        photos.add(new PicsModel(R.drawable.cat,13));
        photos.add(new PicsModel(R.drawable.cat,14));
        photos.add(new PicsModel(R.drawable.cat,15));



        adapter = new PerfilViewAdapter(photos,getActivity());
        rvPics.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rvPics.setLayoutManager(gridLayoutManager);

        return picsView;
    }
}
