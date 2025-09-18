package com.example.mod4_act1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    ArrayList<PetModel> pets;
    ArrayList<String> petsNames;
    ImageView favButton;
    PetAdapter adapter;

    private RecyclerView listPets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


        listPets = (RecyclerView) findViewById(R.id.rvPets);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listPets.setLayoutManager(llm);

        inicializePetsList();
        inicializeAdapter();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem favItem = menu.findItem(R.id.favorite);
        favItem.setOnMenuItemClickListener(item -> {
            Intent goToFavorites = new Intent(MainActivity.this, Favorites.class);
            startActivity(goToFavorites);
            return true;
        });

        return true;
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
        adapter = new PetAdapter(pets,this);
        listPets.setAdapter(adapter);
    }


}