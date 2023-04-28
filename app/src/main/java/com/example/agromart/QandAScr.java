package com.example.agromart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Iterator;

public class QandAScr extends AppCompatActivity {

    TextView quesTxt, ansTxt;
    Button nextBtn, prevBtn;
    int in = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qand_ascr);

        HashMap<String, String> mapi = QuestionAnswers.getMap();

        quesTxt = findViewById(R.id.quesTxt);
        ansTxt = findViewById(R.id.ansTxt);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);


        String ques = (String) mapi.keySet().toArray()[in];
        String ans = (String) mapi.values().toArray()[in];

        quesTxt.setText(ques);
        ansTxt.setText(ans);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in++;
                in %= 26;
                quesTxt.setText((String) mapi.keySet().toArray()[in]);
                ansTxt.setText((String) mapi.values().toArray()[in]);
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (in > 0) {
                    in--;
                    in %= 26;
                    quesTxt.setText((String) mapi.keySet().toArray()[in]);
                    ansTxt.setText((String) mapi.values().toArray()[in]);
                }
            }
        });
    }
}