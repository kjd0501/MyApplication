package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

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
    private BluetoothSocket bs;
    private OutputStream out;
    private Socket sock;
    private DataOutputStream dout;
    private long lastTouchTime = 0;
    private long currentTouchTime = 0;


    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        bs=Device_NetworkList.socket;
//        sock=MainActivity.s;
//        dout=MainActivity.dout;
//        out=Device_NetworkList.output;
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

        clk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clockwise rotation 1 call
                String message="7";
                send(message);
            }
        });
        aclk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //counter clockwise rotation 1 call
                String message="8";
                send(message);
            }
        });
        clk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clockwise rotation 2 call
                String message="9";
                send(message);
            }
        });
        aclk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //counter clockwise rotation 2 call
                String message="10";
                send(message);
            }
        });
        clk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clockwise rotation 3 call
                String message="11";
                send(message);
            }
        });
        aclk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //counter clockwise rotation 3 call
                String message="12";
                send(message);
            }
        });
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
                String message="13";
                send(message);
            }
        });

        raiseEndEffector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //raise end effector call
                String message="14";
                send(message);
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
                    String message="1";
                    send(message);
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
                    String message="3";
                    send(message);
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
                    String message="5";
                    send(message);
                }
            }
        });

        bw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 1 call
                String message="2";
                send(message);
            }
        });

        bw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 2 call
                String message="4";
                send(message);
            }
        });

        bw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 3 call
                String message="6";
                send(message);
            }
        });

    }

    public void send(String message) {
        bs=Device_NetworkList.socket;
        sock=MainActivity.s;
        dout=MainActivity.dout;
        out=Device_NetworkList.output;
        byte[] toSend = message.getBytes();
        try {
            if(bs.isConnected()){
                out.write(toSend);
            }
            else{
                dout.writeUTF(message);
                dout.flush();
                //dout.close();
                Intent A1 = new Intent(this, MainActivity.class);
                startActivity(A1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

