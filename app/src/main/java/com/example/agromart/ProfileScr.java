package com.example.agromart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileScr extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://agromart-27dc2-default-rtdb.firebaseio.com/");
    TextView profileTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_scr);

        profileTxt = findViewById(R.id.profileTxt);

        Intent intent = getIntent();
        String phone = intent.getStringExtra("Phone");

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String userName  = snapshot.child(phone).child("UserName").getValue(String.class);
                String dob  = snapshot.child(phone).child("DateOfBirth").getValue(String.class);
                String age  = snapshot.child(phone).child("Age").getValue(String.class);
                String gender  = snapshot.child(phone).child("Gender").getValue(String.class);
                String address  = snapshot.child(phone).child("Address").getValue(String.class);
                String password  = snapshot.child(phone).child("Password").getValue(String.class);

                String tags[] = {"User Name", "Date of Birth", "Age", "Gender", "Phone Number", "Address", "Password"};
                String data[] = {userName, dob, age, gender, phone, address, password};

                for (int i=0;i<7;i++) {
                    String s = profileTxt.getText().toString();
                    s += ("\n" + tags[i] + ": " + data[i] + "\n");
                    profileTxt.setText(s);
                }

                String s = profileTxt.getText().toString();
                s += "\n";
                profileTxt.setText(s);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}