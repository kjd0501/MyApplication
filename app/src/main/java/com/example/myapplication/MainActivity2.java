package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    private FloatingActionButton clk1;
    private FloatingActionButton aclk1;
    private FloatingActionButton clk2;
    private FloatingActionButton aclk2;
    private FloatingActionButton clk3;
    private FloatingActionButton aclk3;
    int rot;

    private long lastTouchTime = 0;
    private long currentTouchTime = 0;

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
        clk1 = (FloatingActionButton) findViewById(R.id.clk1);
        aclk1 = (FloatingActionButton) findViewById(R.id.aclk1);
        clk2 = (FloatingActionButton) findViewById(R.id.clk2);
        aclk2 = (FloatingActionButton) findViewById(R.id.aclk2);
        clk3 = (FloatingActionButton) findViewById(R.id.clk3);
        aclk3 = (FloatingActionButton) findViewById(R.id.aclk3);
        rot=0;
        clk1.setVisibility(View.GONE);
        aclk1.setVisibility(View.GONE);
        clk2.setVisibility(View.GONE);
        aclk2.setVisibility(View.GONE);
        clk3.setVisibility(View.GONE);
        aclk3.setVisibility(View.GONE);


        drillMode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //drilling activity
                // open MainActivity4
                Intent act4 = new Intent(MainActivity2.this, MainActivity4.class);
                startActivity(act4);
            }
        });

        suctionCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act3 = new Intent(MainActivity2.this, MainActivity3.class);

                // start the activity connect to the specified class
                startActivity(act3);
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
                lastTouchTime = currentTouchTime;
                currentTouchTime = System.currentTimeMillis();

                if (currentTouchTime - lastTouchTime < 250) {
                    if(rot<1) {
                        //Intent act5 = new Intent(MainActivity2.this, MainActivity5.class);
                        //start the activity connect to the specified class
                        //startActivity(act5);
                        rot=1;
                        clk1.setVisibility(View.VISIBLE);
                        aclk1.setVisibility(View.VISIBLE);
                        fw2.setVisibility(View.GONE);
                        fw3.setVisibility(View.GONE);
                        bw2.setVisibility(View.GONE);
                        bw3.setVisibility(View.GONE);
                        bw1.setVisibility(View.GONE);
                    }
                    else{
                        rot=0;
                        clk1.setVisibility(View.GONE);
                        aclk1.setVisibility(View.GONE);
                        fw2.setVisibility(View.VISIBLE);
                        fw3.setVisibility(View.VISIBLE);
                        bw2.setVisibility(View.VISIBLE);
                        bw3.setVisibility(View.VISIBLE);
                        bw1.setVisibility(View.VISIBLE);
                    }



                    lastTouchTime = 0;
                    currentTouchTime = 0;
                } else {
                    //forward 1 call
                }
            }
        });

        fw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastTouchTime = currentTouchTime;
                currentTouchTime = System.currentTimeMillis();

                if (currentTouchTime - lastTouchTime < 250) {
                    //Intent act5 = new Intent(MainActivity2.this, MainActivity5.class);
                    // start the activity connect to the specified class
                    //startActivity(act5);
                    if(rot<1) {
                        //Intent act5 = new Intent(MainActivity2.this, MainActivity5.class);
                        //start the activity connect to the specified class
                        //startActivity(act5);
                        rot=1;
                        clk2.setVisibility(View.VISIBLE);
                        aclk2.setVisibility(View.VISIBLE);
                        fw1.setVisibility(View.GONE);
                        fw3.setVisibility(View.GONE);
                        bw2.setVisibility(View.GONE);
                        bw3.setVisibility(View.GONE);
                        bw1.setVisibility(View.GONE);
                    }
                    else{
                        rot=0;
                        clk2.setVisibility(View.GONE);
                        aclk2.setVisibility(View.GONE);
                        fw1.setVisibility(View.VISIBLE);
                        fw3.setVisibility(View.VISIBLE);
                        bw2.setVisibility(View.VISIBLE);
                        bw3.setVisibility(View.VISIBLE);
                        bw1.setVisibility(View.VISIBLE);
                    }
                    lastTouchTime = 0;
                    currentTouchTime = 0;
                } else {
                    //forward 2 call
                }
            }
        });

        fw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastTouchTime = currentTouchTime;
                currentTouchTime = System.currentTimeMillis();

                if (currentTouchTime - lastTouchTime < 250) {
                    //Intent act5 = new Intent(MainActivity2.this, MainActivity5.class);
                    // start the activity connect to the specified class
                    //startActivity(act5);
                    if(rot<1) {
                        //Intent act5 = new Intent(MainActivity2.this, MainActivity5.class);
                        //start the activity connect to the specified class
                        //startActivity(act5);
                        rot=1;
                        clk3.setVisibility(View.VISIBLE);
                        aclk3.setVisibility(View.VISIBLE);
                        fw2.setVisibility(View.GONE);
                        fw1.setVisibility(View.GONE);
                        bw2.setVisibility(View.GONE);
                        bw3.setVisibility(View.GONE);
                        bw1.setVisibility(View.GONE);
                    }
                    else{
                        rot=0;
                        clk3.setVisibility(View.GONE);
                        aclk3.setVisibility(View.GONE);
                        fw2.setVisibility(View.VISIBLE);
                        fw1.setVisibility(View.VISIBLE);
                        bw2.setVisibility(View.VISIBLE);
                        bw3.setVisibility(View.VISIBLE);
                        bw1.setVisibility(View.VISIBLE);
                    }
                    lastTouchTime = 0;
                    currentTouchTime = 0;
                } else {
                    //forward 3 call
                }
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

