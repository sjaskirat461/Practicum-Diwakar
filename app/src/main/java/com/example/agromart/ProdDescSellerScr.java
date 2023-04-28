package com.example.agromart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ProdDescSellerScr extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://agromart-27dc2-default-rtdb.firebaseio.com/");
    EditText typeEdt, nameEdt, prodIdEdt, quantityEdt, mfdEdt, expiryEdt, descEdt, priceEdt;
    Button prodDescSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_desc_seller_scr);

        typeEdt = findViewById(R.id.typeEdt);
        nameEdt = findViewById(R.id.nameEdt);
        prodIdEdt = findViewById(R.id.prodIdEdt);
        quantityEdt = findViewById(R.id.quantityEdt);
        mfdEdt = findViewById(R.id.mfdEdt);
        expiryEdt = findViewById(R.id.expiryEdt);
        descEdt = findViewById(R.id.descEdt);
        priceEdt = findViewById(R.id.priceEdt);

        prodDescSubmitBtn = findViewById(R.id.prodDescSubmitBtn);

        Intent intent = getIntent();
        String prodType = intent.getStringExtra("prodType");
        typeEdt.setText(prodType);
        typeEdt.setFocusable(false);
        typeEdt.setFocusableInTouchMode(false);

        if (prodType.equals("Tool") || prodType.equals("Protective Gear")) {
            expiryEdt.setVisibility(View.GONE);
        }

        mfdEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ProdDescSellerScr.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mfdEdt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        expiryEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ProdDescSellerScr.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                expiryEdt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        prodDescSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String type = typeEdt.getText().toString().trim();
                String name = nameEdt.getText().toString().trim();
                String prodId = prodIdEdt.getText().toString().trim();
                String quantity = quantityEdt.getText().toString().trim();
                String mfd = mfdEdt.getText().toString().trim();
                String expiry = expiryEdt.getText().toString().trim();
                String desc = descEdt.getText().toString().trim();
                String price = priceEdt.getText().toString().trim();

                if (type.isEmpty() || name.isEmpty() || prodId.isEmpty() || quantity.isEmpty() || mfd.isEmpty() || desc.isEmpty() || price.isEmpty() || ((prodType.equals("Seed") || prodType.equals("Fertilizer")) && expiry.isEmpty())) {
                    Toast.makeText(ProdDescSellerScr.this, "Please fill the Empty fields with required Data", Toast.LENGTH_SHORT).show();
                }
                else {

                    databaseReference.child(type + "").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(prodId)) {
                                Toast.makeText(ProdDescSellerScr.this, "The above Product ID already, exists, please check it !", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                databaseReference.child(type + "").child(prodId).child("Name").setValue(name);
                                databaseReference.child(type + "").child(prodId).child("Quantity").setValue(quantity);
                                databaseReference.child(type + "").child(prodId).child("MFD").setValue(mfd);
                                databaseReference.child(type + "").child(prodId).child("Expiry").setValue(expiry);
                                databaseReference.child(type + "").child(prodId).child("Description").setValue(desc);
                                databaseReference.child(type + "").child(prodId).child("Price").setValue(price);

                                Toast.makeText(ProdDescSellerScr.this, "New Product Added to Mart", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });

    }
}