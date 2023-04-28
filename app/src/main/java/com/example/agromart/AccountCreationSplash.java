package com.example.agromart;

import androidx.appcompat.app.AppCompatActivity;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import android.content.Intent;
import android.os.Bundle;

public class AccountCreationSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation_splash);

        YoYo.with(Techniques.Flash)
                .duration(1000)
                .repeat(1)
                .playOn(findViewById(R.id.verificationTick));

        Thread thread = new Thread() {

            public void run () {
                try {
                    sleep(2000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(AccountCreationSplash.this, LoginScr.class));
                }
            }

        };
        thread.start();

    }
}