package com.example.agromart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SignUpScr extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://agromart-27dc2-default-rtdb.firebaseio.com/");
    EditText signUserEdt, signDobEdt, signAgeEdt, signGenderEdt, signPhoneEdt, signAddressEdt, signPassEdt;
    Button signUpBtn;
    TextView logIn;
    Spinner genderSpinner;
    TextInputLayout signDobEdtCont;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_scr);

        signUserEdt = findViewById(R.id.signUserEdt);
        signDobEdt = findViewById(R.id.signDobEdt);
        signAgeEdt = findViewById(R.id.signAgeEdt);
        signPhoneEdt = findViewById(R.id.signPhoneEdt);
        signAddressEdt = findViewById(R.id.signAddressEdt);
        signPassEdt = findViewById(R.id.signPassEdt);
        signUpBtn = findViewById(R.id.signUpBtn);
        logIn = findViewById(R.id.logIn);
        genderSpinner = findViewById(R.id.genderSpinner);
        signDobEdtCont = findViewById(R.id.signDobEdtCont);

        signDobEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        SignUpScr.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                signDobEdt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();

            }
        });

        signAgeEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sampleDob = signDobEdt.getText().toString();
                if (!sampleDob.isEmpty()) {

                    int d = 0;
                    int m = 0;
                    int y = 0;
                    int allot = 1;
                    String s = "";

                    for (int i=0;i<sampleDob.length();i++) {
                        if (sampleDob.charAt(i) == '/') {
                            if (allot == 1) {
                                d = Integer.parseInt(s);
                                s = "";
                            }
                            else if (allot == 2) {
                                m = Integer.parseInt(s);
                                s = "";
                            }
                            allot++;
                        }
                        else {
                            s += sampleDob.charAt(i);
                        }
                    }
                    s = "";
                    for (int i=sampleDob.length()-1;i>=0;i--) {
                        if (sampleDob.charAt(i) == '/') {
                            y = Integer.parseInt(s);
                            break;
                        }
                        s = sampleDob.charAt(i) + s;
                    }

                    Calendar dobCalendar = Calendar.getInstance();
                    dobCalendar.set(Calendar.YEAR, y);
                    dobCalendar.set(Calendar.MONTH, m);
                    dobCalendar.set(Calendar.DATE, d);
                    Integer ageInteger = 0;
                    Calendar today = Calendar.getInstance();
                    ageInteger = today.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR);
                    if (today.get(Calendar.MONTH) == dobCalendar.get(Calendar.MONTH)) {
                        if (today.get(Calendar.DAY_OF_MONTH) < dobCalendar.get(Calendar.DAY_OF_MONTH)) {
                            ageInteger = ageInteger - 1;
                        }
                    } else if (today.get(Calendar.MONTH) < dobCalendar.get(Calendar.MONTH)) {
                        ageInteger = ageInteger - 1;
                    }

                    signAgeEdt.setText(ageInteger.toString());
                    signAgeEdt.setFocusable(false);
                    signAgeEdt.setFocusableInTouchMode(false);
                    signAgeEdt.setCursorVisible(false);

                }
            }
        });

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String gender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = signUserEdt.getText().toString().trim();
                String dob = signDobEdt.getText().toString().trim();
                String age = signAgeEdt.getText().toString().trim();
                String phone = signPhoneEdt.getText().toString().trim();
                String address = signAddressEdt.getText().toString().trim();
                String password = signPassEdt.getText().toString().trim();
                String gender = genderSpinner.getItemAtPosition(genderSpinner.getSelectedItemPosition()).toString();

                if (userName.isEmpty() || dob.isEmpty() || age.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpScr.this, "Please fill the Empty fields with required Data", Toast.LENGTH_SHORT).show();
                }
                else if (gender.equals("Select Gender")) {
                    Toast.makeText(SignUpScr.this, "Please Select a Gender", Toast.LENGTH_SHORT).show();
                }
                else if (phone.length() < 10) {
                    Toast.makeText(SignUpScr.this, "Phone number should consist of 10 digits", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phone)) {
                                Toast.makeText(SignUpScr.this, "Phone Number already exists!\nRegister a new Phone Number", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                databaseReference.child("users").child(phone).child("UserName").setValue(userName);
                                databaseReference.child("users").child(phone).child("DateOfBirth").setValue(dob);
                                databaseReference.child("users").child(phone).child("Age").setValue(age);
                                databaseReference.child("users").child(phone).child("Gender").setValue(gender);
                                databaseReference.child("users").child(phone).child("Address").setValue(address);
                                databaseReference.child("users").child(phone).child("Password").setValue(password);

//                                Toast.makeText(SignUpScr.this, "New User Added", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent (SignUpScr.this, AccountCreationSplash.class));
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

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}