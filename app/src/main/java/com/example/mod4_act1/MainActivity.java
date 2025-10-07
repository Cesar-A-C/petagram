package com.example.mod4_act1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mod4_act1.adapters.PageAdapter;
import com.example.mod4_act1.adapters.PetAdapter;
import com.example.mod4_act1.controllers.fragments.PerfilFragment;
import com.example.mod4_act1.controllers.fragments.PetFragment;
import com.example.mod4_act1.models.PetModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private MaterialToolbar toolbar;
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

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager2 = (ViewPager2) findViewById(R.id.viewPager);
        setUpViewPager();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id==R.id.favorite){
            Intent goToFavorites = new Intent(MainActivity.this, Favorites.class);
            startActivity(goToFavorites);
        }

        if(id==R.id.contact){
            Intent goToContact = new Intent(MainActivity.this, Contact.class);
            startActivity(goToContact);
        }

        if(id==R.id.about){

        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> addFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new PetFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void setUpViewPager() {
        viewPager2.setAdapter(new PageAdapter(this, addFragments()));

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) ->{
                    switch(position){
                        case 0:
                            tab.setText("Mascotas");
                            break;
                        case 1:
                            tab.setText("Perfil");
                            break;
                    }
                }
        ).attach();
    }
}