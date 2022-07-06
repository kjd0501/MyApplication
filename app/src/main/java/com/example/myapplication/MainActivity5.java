package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity5 extends AppCompatActivity {

    public String rotateTextLabel = "FW1";
    private Button central;
    private Button clk;
    private Button aclk;
    private Button suctionCups;
    private Button drillMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        central = (Button) findViewById(R.id.central);
        clk = (Button) findViewById(R.id.clk);
        aclk = (Button) findViewById(R.id.aclk);
        suctionCups = (Button) findViewById(R.id.suctionCups);
        drillMode = (Button) findViewById(R.id.drillMode);

        central.setText(rotateTextLabel);

        drillMode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //drilling activity
            }
        });

        suctionCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act3 = new Intent(MainActivity5.this, MainActivity3.class);

                // start the activity connect to the specified class
                startActivity(act3);
            }
        });

        clk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clockwise rotation
            }
        });

        aclk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //anticlockwise rotation
            }
        });
    }
}