package com.example.mod4_act1;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.example.mod4_act1.database.Database;
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
        Database database = Database.getInstance(this);
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT pet.id, pet.unique_key, pet.name, pet.image, pet.likes " +
                        "FROM tbl_pets pet " +
                        "INNER JOIN tbl_favorites fav ON fav.id_pet = pet.id",
                null
        );


        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndexOrThrow("id");
            int keyIndex = cursor.getColumnIndexOrThrow("unique_key");
            int nameIndex = cursor.getColumnIndexOrThrow("name");
            int imageIndex = cursor.getColumnIndexOrThrow("image");
            int likesIndex = cursor.getColumnIndexOrThrow("likes");

            do {
                int petId = cursor.getInt(idIndex);
                String key = cursor.getString(keyIndex);
                String name = cursor.getString(nameIndex);
                int image = cursor.getInt(imageIndex);
                int likes = cursor.getInt(likesIndex);

                pets.add(new PetModel(key, name, image, true, 1));
            } while(cursor.moveToNext());
        } else {
            Toast.makeText(this, "No hay mascotas en favoritos", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }

    public  void inicializeAdapter(ArrayList<PetModel> pets){
        adapter = new PetAdapter(pets,this);
        favoritePets.setAdapter(adapter);
    }


}