package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    private Button drillMode;
    private Button suctionCups;
    private Button goToHomeOrientation;
    private Button raiseEndEffector;
    private Button fw1;
    private Button fw2;
    private Button fw3;
    private Button bw1;
    private Button bw2;
    private Button bw3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        drillMode = (Button) findViewById(R.id.drillMode);
        suctionCups = (Button) findViewById(R.id.suctionCups);
        goToHomeOrientation = (Button) findViewById(R.id.Home);
        raiseEndEffector = (Button) findViewById(R.id.raiseEE);
        fw1 = (Button) findViewById(R.id.fw1);
        fw2 = (Button) findViewById(R.id.fw2);
        fw3 = (Button) findViewById(R.id.fw3);
        bw1 = (Button) findViewById(R.id.bw1);
        bw2 = (Button) findViewById(R.id.bw2);
        bw3 = (Button) findViewById(R.id.bw3);

        drillMode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //drilling activity
            }
        });

        suctionCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);

                // start the activity connect to the specified class
                startActivity(intent);
            }
        });

        goToHomeOrientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //home orientation call
            }
        });

        raiseEndEffector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //raise end effector call
            }
        });

        fw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 1 call
            }
        });

        fw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 2 call
            }
        });

        fw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 3 call
            }
        });

        bw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 1 call
            }
        });

        bw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 2 call
            }
        });

        bw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 3 call
            }
        });
    }
}

