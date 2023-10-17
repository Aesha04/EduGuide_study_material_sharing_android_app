package com.example.eduguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid =(GridLayout) findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardview = (CardView)mainGrid.getChildAt(i);

            final int finalI = i;
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI ==0){
                        Intent i = new Intent(MainActivity.this, Sem1Activity.class);
                        startActivity(i);
                    }
                    else if(finalI==1){
                        Intent i = new Intent(MainActivity.this, Sem2Activity.class);
                        startActivity(i);
                    }
                    else if(finalI==2){
                        Intent i = new Intent(MainActivity.this,Sem3Activity.class);
                        startActivity(i);
                    }
                    else if(finalI==3){
                        Intent i = new Intent(MainActivity.this,Sem4Activity.class);
                        startActivity(i);
                    }else if(finalI==4){
                        Intent i = new Intent(MainActivity.this,Sem5Activity.class);
                        startActivity(i);
                    }
                    else if(finalI==5){
                        Intent i = new Intent(MainActivity.this,Sem6Activity.class);
                        startActivity(i);
                    }
                    else if(finalI==6){
                        Intent i = new Intent(MainActivity.this,Sem7Activity.class);
                        startActivity(i);
                    }
                    else if(finalI==7){
                        Intent i = new Intent(MainActivity.this,Sem8Activity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "select activity", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}