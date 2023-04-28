package com.example.agromart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        YoYo.with(Techniques.Flash)
                .duration(1000)
                .repeat(3)
                .playOn(findViewById(R.id.imageView));

        Thread thread = new Thread() {

            public void run () {
                try {
                    sleep(3000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent (MainActivity.this, LoginScr.class);
                    startActivity(intent);
                }
            }

        };
        thread.start();
    }
}