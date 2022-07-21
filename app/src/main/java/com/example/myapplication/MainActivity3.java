package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity3 extends AppCompatActivity {

    private ImageButton suctionCup1;
    private ImageButton suctionCup2;
    private ImageButton suctionCup3;
    private ImageButton innerSuctionCups;
    private Button perimeterCups;
    private Button allSuctionCups;
    private BluetoothSocket bs;
    private OutputStream out;
    private Socket sock;
    private DataOutputStream dout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bs=Device_NetworkList.socket;
        out=Device_NetworkList.output;
        sock=MainActivity.s;
        dout=MainActivity.dout;
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
                String message="15";
                byte[] toSend = message.getBytes();
                try {
                    if(bs.isConnected()){
                        out.write(toSend);
                    }
                    else{
                        dout.writeUTF(message);
                        dout.flush();
                        //dout.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        suctionCup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //suction cup 2 action
                String message="16";
                byte[] toSend = message.getBytes();
                try {
                    if(bs.isConnected()){
                        out.write(toSend);
                    }
                    else{
                        dout.writeUTF(message);
                        dout.flush();
                        //dout.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        suctionCup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //suction cup 3 action
                String message="17";
                byte[] toSend = message.getBytes();
                try {
                    if(bs.isConnected()){
                        out.write(toSend);
                    }
                    else{
                        dout.writeUTF(message);
                        dout.flush();
                        //dout.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        innerSuctionCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inner suction cups toggle
                String message="18";
                byte[] toSend = message.getBytes();
                try {
                    if(bs.isConnected()){
                        out.write(toSend);
                    }
                    else{
                        dout.writeUTF(message);
                        dout.flush();
                        //dout.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        perimeterCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //perimeter cups on/inner cups off action
                String message="20";
                byte[] toSend = message.getBytes();
                try {
                    if(bs.isConnected()){
                        out.write(toSend);
                    }
                    else{
                        dout.writeUTF(message);
                        dout.flush();
                        //dout.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        allSuctionCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //all suction cups off action
                String message="19";
                byte[] toSend = message.getBytes();
                try {
                    if(bs.isConnected()){
                        out.write(toSend);
                    }
                    else{
                        dout.writeUTF(message);
                        dout.flush();
                        //dout.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}