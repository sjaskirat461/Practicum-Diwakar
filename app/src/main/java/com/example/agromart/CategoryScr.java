package com.example.agromart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CategoryScr extends AppCompatActivity {

    CardView seedCard, fertilizerCard, toolCard, gearCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_scr);

        seedCard = findViewById(R.id.seedCard);
        fertilizerCard = findViewById(R.id.fertilizerCard);
        toolCard = findViewById(R.id.toolCard);
        gearCard = findViewById(R.id.gearCard);

        seedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryScr.this, ProdDescSellerScr.class);
                intent.putExtra("prodType", "Seed");
                startActivity(intent);
            }
        });

        fertilizerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryScr.this, ProdDescSellerScr.class);
                intent.putExtra("prodType", "Fertilizer");
                startActivity(intent);
            }
        });

        toolCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryScr.this, ProdDescSellerScr.class);
                intent.putExtra("prodType", "Tool");
                startActivity(intent);
            }
        });

        gearCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryScr.this, ProdDescSellerScr.class);
                intent.putExtra("prodType", "Protective Gear");
                startActivity(intent);
            }
        });

    }
}