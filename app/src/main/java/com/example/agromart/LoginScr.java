package com.example.agromart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginScr extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://agromart-27dc2-default-rtdb.firebaseio.com/");
    EditText loginUserEdt, loginPhoneEdt, loginPassEdt;
    Button logInBtn;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_scr);

        loginUserEdt = findViewById(R.id.loginUserEdt);
        loginPhoneEdt = findViewById(R.id.loginPhoneEdt);
        loginPassEdt = findViewById(R.id.loginPassEdt);
        logInBtn = findViewById(R.id.logInBtn);
        signUp = findViewById(R.id.signUp);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = loginUserEdt.getText().toString().trim();
                String phone = loginPhoneEdt.getText().toString().trim();
                String password = loginPassEdt.getText().toString().trim();

                if (username.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginScr.this, "Please Fill the Empty Fields with required Data", Toast.LENGTH_SHORT).show();
                }
                else if (phone.length() < 10) {
                    Toast.makeText(LoginScr.this, "Phone number should consist of 10 digits", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phone)) {
                                String getUserName  = snapshot.child(phone).child("UserName").getValue(String.class);
                                String getPassword  = snapshot.child(phone).child("Password").getValue(String.class);
                                if (getUserName.equals(username) && getPassword.equals(password)) {
                                    Toast.makeText(LoginScr.this, "Successfully Logged in !!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginScr.this, BuyerSellerScr.class);
                                    intent.putExtra("Phone", phone + "");
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(LoginScr.this, "Wrong UserName or Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(LoginScr.this, "Phone Number not found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScr.this, SignUpScr.class));
            }
        });

    }
}