package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity4 extends AppCompatActivity {

    private ImageButton largeDown;
    private ImageButton largeUp;
    private ImageButton fineDown;
    private ImageButton fineUp;
    private ImageButton extraFineDown;
    private ImageButton extraFineUp;
    private Button fw1;
    private Button fw2;
    private Button fw3;
    private Button bw1;
    private Button bw2;
    private Button bw3;
    private Button circular;
    private Button rectangular;
    private Button addNew;

    private long lastTouchTime = 0;
    private long currentTouchTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        largeDown = (ImageButton) findViewById(R.id.largeDown);
        largeUp = (ImageButton) findViewById(R.id.largeup);
        fineDown = (ImageButton) findViewById(R.id.fineDown);
        fineUp = (ImageButton) findViewById(R.id.fineUp);
        extraFineDown = (ImageButton) findViewById(R.id.extraFineDown);
        extraFineUp = (ImageButton) findViewById(R.id.extraFineUp);
        fw1 = (Button) findViewById(R.id.fw1);
        fw2 = (Button) findViewById(R.id.fw2);
        fw3 = (Button) findViewById(R.id.fw3);
        bw1 = (Button) findViewById(R.id.bw1);
        bw2 = (Button) findViewById(R.id.bw2);
        bw3 = (Button) findViewById(R.id.bw3);
        circular = (Button) findViewById(R.id.circular);
        rectangular = (Button) findViewById(R.id.rectangular);
        addNew = (Button) findViewById(R.id.addNew);

        largeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //large down call
            }
        });

        largeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //large up call
            }
        });

        fineDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fine down call
            }
        });

        fineUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fine up call
            }
        });

        extraFineDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //extra fine down call
            }
        });

        extraFineUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //extra fine up call
            }
        });

        fw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 1 action
            }
        });

        fw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 2 action
            }
        });

        fw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 3 action
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

        circular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //circular call
            }
        });

        rectangular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rectangular call
            }
        });

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add new call
                //open MainActivity6

                Intent act5 = new Intent(MainActivity4.this, MainActivity6.class);
                startActivity(act5);

            }
        });
    }
}