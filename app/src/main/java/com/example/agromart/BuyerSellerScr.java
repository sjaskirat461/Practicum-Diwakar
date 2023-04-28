package com.example.agromart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class BuyerSellerScr extends AppCompatActivity {

    ImageView buyerImg, sellerImg;
    ImageView profileBtn, logOutBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_seller_scr);

        buyerImg = findViewById(R.id.buyerImg);
        sellerImg = findViewById(R.id.sellerImg);
        profileBtn = findViewById(R.id.profileBtn);
        logOutBtn = findViewById(R.id.logOutBtn);

        buyerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyerSellerScr.this, DashboardBuyer.class));
            }
        });

        sellerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyerSellerScr.this, CategoryScr.class));
            }
        });

        Intent intent = getIntent();
        String phone = intent.getStringExtra("Phone");

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNew = new Intent(BuyerSellerScr.this, ProfileScr.class);
                intentNew.putExtra("Phone", phone + "");
                startActivity(intentNew);
            }
        });

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyerSellerScr.this, LoginScr.class));
                Toast.makeText(BuyerSellerScr.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}