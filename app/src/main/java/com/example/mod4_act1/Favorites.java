package com.example.mod4_act1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mod4_act1.adapters.PetAdapter;
import com.example.mod4_act1.models.PetModel;

import java.util.ArrayList;
import java.util.Objects;

public class Favorites extends AppCompatActivity {
    ArrayList<PetModel> pets;
    private RecyclerView favoritePets;
    PetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorites);

        /*********************************************************************************/
        //CÓDIGO NECESARIO PARA UN TOOLBAR PERSONALIZADO CON FLECHA DE VOLVER
        /*********************************************************************************/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Flecha de volver
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setTint(Color.BLACK);
        }
        /*********************************************************************************/

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        favoritePets = (RecyclerView) findViewById(R.id.rvFavoritePets);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        favoritePets.setLayoutManager(llm);

        inicializePetsList();
        inicializeAdapter(pets);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void inicializePetsList(){
        pets = new ArrayList<>();

        pets.add(new PetModel("Karencio", R.drawable.cat,false,0));
        pets.add(new PetModel("Ramón", R.drawable.cat,false, 0));
        pets.add(new PetModel("Ignacio", R.drawable.cat,false,0));
        pets.add(new PetModel("Raúl", R.drawable.cat,false,0));
        pets.add(new PetModel("Felipe", R.drawable.cat,false,0));
    }

    public  void inicializeAdapter(ArrayList<PetModel> pets){
        adapter = new PetAdapter(pets,this);
        favoritePets.setAdapter(adapter);
    }


}