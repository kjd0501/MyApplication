package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity3 extends AppCompatActivity {

    private ImageButton suctionCup1;
    private ImageButton suctionCup2;
    private ImageButton suctionCup3;
    private ImageButton innerSuctionCups;
    private Button perimeterCups;
    private Button allSuctionCups;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        suctionCup1 = (ImageButton) findViewById(R.id.suctionCup1);
        suctionCup2 = (ImageButton) findViewById(R.id.suctionCup2);
        suctionCup3 = (ImageButton) findViewById(R.id.suctionCup3);
        innerSuctionCups = (ImageButton) findViewById(R.id.innerSuctionCups);
        perimeterCups = (Button) findViewById(R.id.perimeterCups);
        allSuctionCups = (Button) findViewById(R.id.allSuctionCups);

        suctionCup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //suction cup 1 action
            }
        });

        suctionCup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //suction cup 2 action
            }
        });

        suctionCup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //suction cup 3 action
            }
        });

        innerSuctionCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inner suction cups toggle
            }
        });

        perimeterCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //perimeter cups action
            }
        });

        allSuctionCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //all suction cups action
            }
        });
    }
}