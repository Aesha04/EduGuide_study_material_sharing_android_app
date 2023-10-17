package com.example.eduguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class Sem4Activity extends AppCompatActivity {

    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem4);

        mainGrid = (GridLayout) findViewById(R.id.mainGridSem4);
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
                        Intent i = new Intent(Sem4Activity.this, sem3_de.class);
                        startActivity(i);
                    } else if (finalI == 1) {
                        Intent i = new Intent(Sem4Activity.this, sem3_java.class);
                        startActivity(i);
                    } else if (finalI == 2) {
                        Intent i = new Intent(Sem4Activity.this, sem3_dcn.class);
                        startActivity(i);
                    } else if (finalI == 3) {
//                        Intent i = new Intent(Sem4Activity.this, Sem4Activity.class);
//                        startActivity(i);
                    } else if (finalI == 4) {
//                        Intent i = new Intent(Sem4Activity.this, Sem5Activity.class);
//                        startActivity(i);
                    } else {
                        Toast.makeText(Sem4Activity.this, "select activity", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}