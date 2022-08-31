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

/**
 * This class is used to declare the methods for the page activity_main3.xml.
 * In this class, the commands sent for manipulation of the robot are defined.
 */
public class MainActivity3 extends AppCompatActivity {
    //declare the variables to be used for the buttons
    private ImageButton suctionCup1; //button used for toggling suction cup 1
    private ImageButton suctionCup2; //button used for toggling suction cup 2
    private ImageButton suctionCup3; //button used for toggling suction cup 3
    private ImageButton innerSuctionCups; //button used for toggling inner suction cups
    private Button perimeterCups; //button used for toggling all perimeter cups
    private Button allSuctionCups; //button used for toggling all the suction cups
    private BluetoothSocket bs; //bluetooth socket
    //variables declared for use of the bluetooth socket
    private OutputStream out;
    private Socket sock;
    private DataOutputStream dout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the page to activity_main3
        setContentView(R.layout.activity_main3);

        //initialize the buttons
        suctionCup1 = (ImageButton) findViewById(R.id.suctionCup1); //button used for toggling suction cup 1
        suctionCup2 = (ImageButton) findViewById(R.id.suctionCup2); //button used for toggling suction cup 2
        suctionCup3 = (ImageButton) findViewById(R.id.suctionCup3); //button used for toggling suction cup 3
        innerSuctionCups = (ImageButton) findViewById(R.id.innerSuctionCups); //button used for toggling inner suction cups
        perimeterCups = (Button) findViewById(R.id.perimeterCups); //button used for toggling all perimeter cups
        allSuctionCups = (Button) findViewById(R.id.allSuctionCups); //button used for toggling all the suction cups

        //when the suctionCup1 button is tapped
        suctionCup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //suction cup 1 action command
                String message="15";
                send(message);

            }
        });

        //when the suctionCup2 button is tapped
        suctionCup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //suction cup 2 action command
                String message="16";
                send(message);

            }
        });

        //when the suctionCup3 button is tapped
        suctionCup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //suction cup 3 action command
                String message="17";
                send(message);

            }
        });

        //when the innerSuctionCups button is tapped
        innerSuctionCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inner suction cups toggle command
                String message="18";
                send(message);

            }
        });

        //when the perimeterCups button is tapped
        perimeterCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //perimeter cups on/inner cups off action command
                String message="20";
                send(message);

            }
        });

        //when the allSuctionCups button is tapped
        allSuctionCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //all suction cups off action command
                String message="19";
                send(message);
            }
        });
    }

    /**
     * This method is used to send the command to the robot via the bluetooth socket.
     * @param message is the command to be sent
     */
    public void send(String message) {
        //variables defined to use bluetooth socket
        bs=Device_NetworkList.socket;
        sock=MainActivity.s;
        dout=MainActivity.dout;
        out=Device_NetworkList.output;
        //convert message to bytes
        byte[] toSend = message.getBytes();
        try {
            //check if the bluetooth socket is connected
            if(bs.isConnected()){
                //send the message using the socketg
                out.write(toSend);
            }
            else{
                dout.writeUTF(message);
                dout.flush();

                //transition to the main landing page to connect to the robot.
                Intent A1 = new Intent(this, MainActivity.class);
                startActivity(A1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}