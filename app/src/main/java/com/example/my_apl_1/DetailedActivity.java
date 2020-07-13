package com.example.my_apl_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {

     public String id;
    private TextView desc;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        desc = findViewById(R.id.desc_detail);
        imageView = findViewById(R.id.image_detail);

        //get extras of adapter
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String descriptions = intent.getStringExtra("description");
        String imageUrls = intent.getStringExtra("imageUrls");

        desc.setText(descriptions);


        Picasso.with(DetailedActivity.this).load(imageUrls).into(imageView);
    }
}