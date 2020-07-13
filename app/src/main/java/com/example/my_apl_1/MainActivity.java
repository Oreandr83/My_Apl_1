package com.example.my_apl_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.my_apl_1.fragments.FavFragment;
import com.example.my_apl_1.fragments.InfoFragment;
import com.example.my_apl_1.fragments.PhotosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.id_bott_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

        if(savedInstanceState == null){
            //fragment by default
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new PhotosFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.favorite:
                            fragment = new FavFragment();
                            break;
                        case R.id.photo:
                            fragment = new PhotosFragment();
                            break;
                        case R.id.info:
                            fragment = new InfoFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                    return true;
                }
            };



}