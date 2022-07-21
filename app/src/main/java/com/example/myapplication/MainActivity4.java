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
        bs=Device_NetworkList.socket;
        out=Device_NetworkList.output;
        sock=MainActivity.s;
        dout=MainActivity.dout;
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

        largeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //large up call
                String message="28";
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

        fineDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fine down call
                String message="29";
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

        fineUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fine up call
                String message="30";
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

        extraFineDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //extra fine down call
                String message="31";
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

        extraFineUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //extra fine up call
                String message="32";
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

        fw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 1 action
                String message="21";
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

        fw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 2 action
                String message="23";
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

        fw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //forward 3 action
                String message="25";
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

        bw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 1 call
                String message="22";
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

        bw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 2 call
                String message="24";
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

        bw3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //backward 3 call
                String message="26";
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

        circular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //circular call
                String message="33";
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

        rectangular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //rectangular call
                String message="34";
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