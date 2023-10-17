package com.example.eduguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Sem1Activity extends AppCompatActivity {

    GridLayout mainGrid;
    FloatingActionButton fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem1);

        fb = findViewById(R.id.fab_sem1);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), uploadfile.class));
            }
        });

        mainGrid = findViewById(R.id.mainGridSem1);
        setSingleEvent(mainGrid);

    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardview = (CardView) mainGrid.getChildAt(i);

            final int finalI = i;
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0) {
                        Intent i = new Intent(Sem1Activity.this, sem1_clanguage.class);
                        startActivity(i);
                    } else if (finalI == 1) {
                        Intent i = new Intent(Sem1Activity.this, sem1_beee.class);
                        startActivity(i);
                    } else if (finalI == 2) {
//                        Intent i = new Intent(Sem1Activity.this, Sem3Activity.class);
//                        startActivity(i);
                    } else if (finalI == 3) {
//                        Intent i = new Intent(Sem1Activity.this, Sem4Activity.class);
//                        startActivity(i);
                    } else if (finalI == 4) {
//                        Intent i = new Intent(Sem1Activity.this, Sem5Activity.class);
//                        startActivity(i);
                    } else {
                        Toast.makeText(Sem1Activity.this, "select activity", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}