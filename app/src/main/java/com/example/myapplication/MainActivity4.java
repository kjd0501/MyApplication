package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

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
    private BluetoothSocket bs;
    private OutputStream out;
    private long lastTouchTime = 0;
    private long currentTouchTime = 0;
    private Socket sock;
    private DataOutputStream dout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
//        bs=Device_NetworkList.socket;
//        out=Device_NetworkList.output;
//        sock=MainActivity.s;
//        dout=MainActivity.dout;
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
                String message="27";
                send(message);

            }
        });

        largeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //large up call
                String message="28";
                send(message);

            }
        });

        fineDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fine down call
                String message="29";
                send(message);

            }
        });

        fineUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fine up call
                String message="30";
                send(message);

            }
        });

        extraFineDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //extra fine down call
                String message="31";
                send(message);

            }
        });

        extraFineUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //extra fine up call
                String message="32";
                send(message);

            }
        });

        fw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 1 action
                String message="21";
                send(message);

            }
        });

        fw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 2 action
                String message="23";
                send(message);

            }
        });

        fw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 3 action
                String message="25";
                send(message);

            }
        });

        bw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 1 call
                String message="22";
                send(message);

            }
        });

        bw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 2 call
                String message="24";
                send(message);

            }
        });

        bw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 3 call
                String message="26";
                send(message);

            }
        });

        circular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //circular call
                String message="33";
                send(message);

            }
        });

        rectangular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rectangular call
                String message="34";
                send(message);
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