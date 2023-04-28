package com.example.agromart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardBuyer extends AppCompatActivity {

    CardView qandaCard, storeCard, seasonCard, cartCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_buyer);

        qandaCard = findViewById(R.id.qandaCard);
        storeCard = findViewById(R.id.storeCard);
        seasonCard = findViewById(R.id.seasonCard);
        cartCart = findViewById(R.id.cartCard);

        qandaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardBuyer.this, QandAScr.class));
            }
        });

        storeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardBuyer.this, Shop.class));
            }
        });

    }
}